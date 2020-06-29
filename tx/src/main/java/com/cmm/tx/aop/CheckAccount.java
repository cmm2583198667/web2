package com.cmm.tx.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmm.tx.exception.NoAccountException;
import com.cmm.tx.mapper.AccountMapper;
import com.cmm.tx.pojo.Account;

@Component
@Aspect
public class CheckAccount {
	@Pointcut("@annotation(com.cmm.tx.aop.Check)")
	public void pc() {

	}
	@Autowired
	AccountMapper accountMapper;
	@Before("pc()")
	public void a(JoinPoint joinPoint) throws NoAccountException {
		Object[] args = joinPoint.getArgs();
		Account account=accountMapper.load((int) args[1]);
		if (account==null) {
			throw new NoAccountException((int) args[1]);
		}
		
	}
	@After("pc()")
	public void b() {

	}
//	@Around("pc()")
	public void c() {

	}
	@AfterThrowing("pc()")
	public void d() {
		System.out.println("账号不存在！！！！！！！！！");
	}
	@AfterReturning("pc()")
	public void e() {

	}
}
