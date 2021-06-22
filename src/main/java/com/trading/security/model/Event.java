package com.trading.security.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/*
 * Class to hold event details.
 */

@Entity
@Table(name="event")
@IdClass(EventKey.class)
public class Event implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -801812948581405740L;

	@Id
	private Long eventId;
	
	@Id
	private String securityId;
	
	@Id
	private String accountId;
	
	@Id
	private TradeAction tradeAction;
	
	private Integer tradeQuantity;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumns({
	 * 
	 * @JoinColumn(name = "accountId"),
	 * 
	 * @JoinColumn(name = "securityId") }) private PositionBook positionBook;
	 */

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

	public Integer getTradeQuantity() {
		return tradeQuantity;
	}

	public void setTradeQuantity(Integer tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((securityId == null) ? 0 : securityId.hashCode());
		result = prime * result + ((tradeAction == null) ? 0 : tradeAction.hashCode());
		result = prime * result + ((tradeQuantity == null) ? 0 : tradeQuantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (securityId == null) {
			if (other.securityId != null)
				return false;
		} else if (!securityId.equals(other.securityId))
			return false;
		if (tradeAction != other.tradeAction)
			return false;
		if (tradeQuantity == null) {
			if (other.tradeQuantity != null)
				return false;
		} else if (!tradeQuantity.equals(other.tradeQuantity))
			return false;
		return true;
	}

	/*
	 * public PositionBook getPositionBook() { return positionBook; }
	 * 
	 * public void setPositionBook(PositionBook positionBook) { this.positionBook =
	 * positionBook; }
	 */
	
	
}
