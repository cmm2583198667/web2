package com.cmm.tx.exception;

import java.math.BigDecimal;

public class NoMoneyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMoneyException(int from, BigDecimal money) {
		//日志
		super(String.format("账户：%d,转账：%.2f 余额不足", from,money.doubleValue()));
	}
	
}
