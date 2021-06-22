package com.trading.security.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.trading.security.model.Event;
import com.trading.security.model.PositionBook;
import com.trading.security.model.TradeAction;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradingServiceImplTest {
	
	@Autowired
	private TradingServiceImpl service;
	
	
	  @Test
	  @DisplayName("When trade events are passed then trade event is stored in memory and retrieved")
	  void testToValidateTradeEventsJourney() {		
		List<Event> eventList = new ArrayList<Event>();
		
		Event event = new Event();
		event.setAccountId("ACC1");
		event.setEventId((long)1);
		event.setSecurityId("SEC1");
		event.setTradeAction(TradeAction.BUY);
		event.setTradeQuantity(10);
		
		Event event1 = new Event();
		event1.setAccountId("ACC1");
		event1.setEventId((long)2);
		event1.setSecurityId("SEC1");
		event1.setTradeAction(TradeAction.SELL);
		event1.setTradeQuantity(10);	
		
		Event event3 = new Event();
		event3.setAccountId("ACC2");
		event3.setEventId((long)1);
		event3.setSecurityId("SEC1");
		event3.setTradeAction(TradeAction.BUY);
		event3.setTradeQuantity(10);
		
		Event event4 = new Event();
		event4.setAccountId("ACC2");
		event4.setEventId((long)1);
		event4.setSecurityId("SEC1");
		event4.setTradeAction(TradeAction.CANCEL);
		event4.setTradeQuantity(10);
		
		eventList.add(event);	
		eventList.add(event1);
		eventList.add(event3);
		eventList.add(event4);
		
	    List<PositionBook> positions = service.recordTradeEvents(eventList);
	    
	    assertThat(positions.size(),equalTo(2));
	    assertThat(positions.get(0).getTradeEvents().size(), equalTo(2));
	    assertThat(positions.get(1).getTradeEvents().size(), equalTo(2));
	    assertThat(positions.get(0).getQuantity(), equalTo(0));	
	    assertThat(positions.get(1).getQuantity(), equalTo(0));	    

	  }

}
