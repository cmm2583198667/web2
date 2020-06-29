package com.cmm.tx.exception;

import java.math.BigDecimal;

public class NoAccountException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoAccountException(int to) {
		//日志
		super(String.format("账号不存在：%d", to));
	}
	
}
