package com.trading.security.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import com.trading.security.model.Event;
import com.trading.security.model.PositionBook;
import com.trading.security.model.PositionBookKey;
import com.trading.security.model.TradeAction;
import com.trading.security.repository.EventRepository;
import com.trading.security.repository.PositionBookRepository;
import com.trading.security.service.TradingService;



@Service
public class TradingServiceImpl implements TradingService {
	
	@Autowired
	PositionBookRepository positionBookRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	
	@PersistenceContext
	private EntityManager entityManager;

	

	@Override
	public List<PositionBook> recordTradeEvents(List<Event> events) {
		
		List<PositionBook> positions = saveTradeEvents(events);
		
		positions.stream().forEach( position -> {
			calculateSecurityValue(position);			
		});
		
		return positions;
	}

	private List<PositionBook> saveTradeEvents(List<Event> events) {
		events.stream().forEach(e -> {
			if(null == positionBookRepository.findById(new PositionBookKey(e.getAccountId(), e.getSecurityId())).orElse(null)) {
				PositionBook positionBook = new PositionBook();
				positionBook.setAccountId(e.getAccountId());
				positionBook.setSecurityId(e.getSecurityId());
				positionBookRepository.save(positionBook);
			}
			eventRepository.save(e);
		});
		entityManager.clear();
		return positionBookRepository.findAll();
		
	}

	private void calculateSecurityValue(PositionBook position) {
		
		List<Long> buyEventIds = position.getTradeEvents()
				.stream()
				.filter(x -> TradeAction.BUY.toString().equalsIgnoreCase(x.getTradeAction().toString()))
				.map(x -> x.getEventId())
				.collect(Collectors.toList());
		
		List<Long> sellEventIds = position.getTradeEvents()
				.stream()
				.filter(x -> TradeAction.SELL.toString().equalsIgnoreCase(x.getTradeAction().toString()))
				.map(x -> x.getEventId())
				.collect(Collectors.toList());
		
		List<Long> cancelEventIds = position.getTradeEvents()
				.stream()
				.filter(x -> TradeAction.CANCEL.toString().equalsIgnoreCase(x.getTradeAction().toString()))
				.map(x -> x.getEventId())
				.collect(Collectors.toList());	
		
		buyEventIds.removeAll(cancelEventIds);
		sellEventIds.removeAll(cancelEventIds);
		
		long buyAmount = position.getTradeEvents()
				.stream()
				.filter(x -> buyEventIds.contains(x.getEventId()))
				.map(x -> x.getTradeQuantity())
				.reduce(0, (x, y) -> x + y);
		
		long sellAmount = position.getTradeEvents()
				.stream()
				.filter(x -> sellEventIds.contains(x.getEventId()))
				.map(x -> x.getTradeQuantity())
				.reduce(0, (x, y) -> x + y);
		
		position.setQuantity(NumberUtils.convertNumberToTargetClass((buyAmount-sellAmount), Integer.class));
	}

}
