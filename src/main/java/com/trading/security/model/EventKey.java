package com.trading.security.model;

import java.io.Serializable;

public class EventKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3602735202770107922L;


	private Long eventId;
	
	
	private String securityId;
	
	
	private String accountId;
	
	
	private TradeAction tradeAction;


	public Long getEventId() {
		return eventId;
	}


	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}


	public String getSecurityId() {
		return securityId;
	}


	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public TradeAction getTradeAction() {
		return tradeAction;
	}


	public void setTradeAction(TradeAction tradeAction) {
		this.tradeAction = tradeAction;
	}
	
	
	

}
