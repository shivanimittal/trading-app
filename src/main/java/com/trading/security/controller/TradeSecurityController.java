package com.trading.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trading.security.model.Event;
import com.trading.security.model.PositionBook;
import com.trading.security.service.TradingService;

@RestController
@RequestMapping("/trades")
public class TradeSecurityController {
	
	@Autowired
	private TradingService service;
	
	@PostMapping
	public List<PositionBook> saveAndShowTrades(@RequestBody List<Event> events){		
		return service.recordTradeEvents(events);		
	}

}
