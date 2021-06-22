package com.trading.security.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="positionbook")
@IdClass(PositionBookKey.class)
public class PositionBook implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4888902541734234680L;

	@Id
	private String accountId;
		
	@Id
	private String securityId;
	
	@Transient
	private Integer quantity;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "accountId", referencedColumnName = "accountId", insertable = false, updatable = false),
		@JoinColumn(name = "securityId", referencedColumnName = "securityId", insertable = false, updatable = false)		
	})
	private Set<Event> tradeEvents;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((securityId == null) ? 0 : securityId.hashCode());
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
		PositionBook other = (PositionBook) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (securityId == null) {
			if (other.securityId != null)
				return false;
		} else if (!securityId.equals(other.securityId))
			return false;
		return true;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Set<Event> getTradeEvents() {
		return tradeEvents;
	}

	public void setTradeEvents(Set<Event> tradeEvents) {
		this.tradeEvents = tradeEvents;
	}
	
	


}
