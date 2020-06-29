package com.cmm.tx.service;
/**
 * 业务逻辑的定义
 * @author 陈明明
 *
 */

import java.math.BigDecimal;

import com.cmm.tx.exception.NoMoneyException;
import com.cmm.tx.pojo.Account;
import com.cmm.tx.pojo.Exchange;

public interface BankService {
	
	/**
	 * 开户
	 * @param name 开户的名字
	 * @return 返回一个账户
	 */
	Account createAccount(String name) ;
	
	/**
	 * 转账
	 * @param from 转出的账户编号
	 * @param to	转入的账户编号
	 * @param money 转出的金额
	 * @return  交易记录
	 * @throws NoMoneyException 
	 */
	Exchange exchange(int from,int to,BigDecimal money) throws NoMoneyException;
	Exchange[]show();
}
