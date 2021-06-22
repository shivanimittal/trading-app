package com.trading.security.model;

import java.io.Serializable;


public class PositionBookKey implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 434370996315238522L;


	private String accountId;
	
	
	private String securityId;


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


	public PositionBookKey(String accountId, String securityId) {
		super();
		this.accountId = accountId;
		this.securityId = securityId;
	}


	public PositionBookKey() {
		super();
	}
	
	
	

}
