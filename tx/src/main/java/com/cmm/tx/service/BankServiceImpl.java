package com.cmm.tx.service;

import java.math.BigDecimal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cmm.tx.aop.Check;
import com.cmm.tx.exception.NoMoneyException;
import com.cmm.tx.mapper.AccountMapper;
import com.cmm.tx.mapper.ExchangeMapper;
import com.cmm.tx.pojo.Account;
import com.cmm.tx.pojo.Exchange;

@Service
public class BankServiceImpl implements BankService,ApplicationContextAware{

	@Autowired
	AccountMapper accountmapper;
	
	@Autowired
	ExchangeMapper exchangemapper;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	public Account createAccount(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	//开启事务
	@Transactional(rollbackFor = NoMoneyException.class,isolation =Isolation.SERIALIZABLE)
	@Check
	@Override
	public Exchange exchange(int from, int to, BigDecimal money) throws NoMoneyException {
		
		Exchange exchange =new Exchange();
		exchange.setFrom(new Account(from));
		exchange.setTo(new Account(to));
		exchange.setTotal(money);
		
		//事务的传播行为
		//从容器获得一个新的实例（other）
		BankServiceImpl other=context.getBean(BankServiceImpl.class);
		//挂起当前事务，创建了新事物
		other.log(exchange);
		//查询余额
		BigDecimal balance=accountmapper.getBalance(from);
		if(balance.subtract(money).intValue()<0||money.intValue()<0) {
			//余额不足
			throw new NoMoneyException(from,money);
		}
		//转账
		accountmapper.updateBalance(from, money.negate());
		accountmapper.updateBalance(to, money);
		//更新交易记录状态
		exchangemapper.update(exchange.getId());
		//写交易记录
//		exchangemapper.insert(from,to,money);
		exchange=exchangemapper.load(exchange.getId());
		return exchange;
	}
	@Override
	public Exchange[] show() {
		Exchange[]exchanges= exchangemapper.show();
		return exchanges;
	}
	//事务的传播行为
	//挂起原事务，创建一个独立的新事物，外部事物回滚不影响他的执行
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void log(Exchange exchange) {
		//写入
		exchangemapper.save(exchange);
	}
	//侵入
	//基于接口的依赖注入
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
	}
}
