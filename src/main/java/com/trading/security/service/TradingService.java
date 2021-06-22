package com.trading.security.service;

import java.util.List;

import com.trading.security.model.Event;
import com.trading.security.model.PositionBook;


public interface TradingService {
	
	List<PositionBook> recordTradeEvents(List<Event> events);

}
