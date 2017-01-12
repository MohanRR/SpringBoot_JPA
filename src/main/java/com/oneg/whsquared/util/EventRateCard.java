/**
 * 
 */
package com.oneg.whsquared.util;

import java.math.BigDecimal;

/**
 * @author arivu
 * 
 */
/**
 * @author arivu
 * 
 */
public class EventRateCard {

	private String name;

	private BigDecimal amount;

	private String currency;

	public EventRateCard() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param amount
	 * @param currency
	 */
	public EventRateCard(String name, BigDecimal amount, String currency) {
		super();
		this.name = name;
		this.amount = amount;
		this.currency = currency;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventRateCard [name=" + name + ", amount=" + amount + ", currency=" + currency + "]";
	}

}
