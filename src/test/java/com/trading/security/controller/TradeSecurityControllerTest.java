package com.trading.security.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.security.model.Event;
import com.trading.security.model.PositionBook;
import com.trading.security.model.TradeAction;
import com.trading.security.service.TradingService;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@AutoConfigureMockMvc
public class TradeSecurityControllerTest {
	  @Autowired private MockMvc mockMvc;
	  @Autowired private ObjectMapper mapper;
	  
	  @Mock
	  private TradingService tradingService;
	  
	  @Test
	  @DisplayName("When trading events are passed then it is stored in memory database and retrieved after processing.")
	  public void testEventsCreatedAndProcessedCorrectly() throws Exception {
		  
	  List<Event> finalEvents = new ArrayList<Event>();
	  
	    Event event = new Event();
		event.setAccountId("ACC1");
		event.setEventId((long)1);
		event.setSecurityId("SEC1");
		event.setTradeAction(TradeAction.BUY);
		event.setTradeQuantity(100);
		
		Event event1 = new Event();
		event1.setAccountId("ACC1");
		event1.setEventId((long)2);
		event1.setSecurityId("SEC1");
		event1.setTradeAction(TradeAction.SELL);
		event1.setTradeQuantity(50);
		
		Event event2 = new Event();
		event2.setAccountId("ACC1");
		event2.setEventId((long)3);
		event2.setSecurityId("SEC1");
		event2.setTradeAction(TradeAction.BUY);
		event2.setTradeQuantity(150);
		
		Event event3 = new Event();
		event3.setAccountId("ACC1");
		event3.setEventId((long)1);
		event3.setSecurityId("SEC1");
		event3.setTradeAction(TradeAction.CANCEL);
		event3.setTradeQuantity(10);
		
		finalEvents.add(event);
		finalEvents.add(event1);
		finalEvents.add(event2);
		finalEvents.add(event3);		
		
	  Integer quantity = 
	        mapper
	            .readValue(
	                mockMvc
	                    .perform(
	                        post("/trades")
	                            .contentType(MediaType.APPLICATION_JSON)
	                            .content(mapper.writeValueAsString(finalEvents)))
	                    .andExpect(status().isOk())
	                    .andReturn()
	                    .getResponse()
	                    .getContentAsString(),
	                    new TypeReference<List<PositionBook>>() {})
	            .get(0).getQuantity();

	    
	    assertThat(quantity, equalTo(100));

	  	  }

}
