package com.cmm.tx.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Exchange {
	private int id;
	private Account from;
	private Account to;
	private BigDecimal total;
	private Date time;
	private int done;
	
	
	public Exchange() {
		
	}
	public void setDone(int done) {
		this.done = done;
	}
	public int getDone() {
		return done;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getFrom() {
		return from;
	}
	public void setFrom(Account from) {
		this.from = from;
	}
	public Account getTo() {
		return to;
	}
	public void setTo(Account to) {
		this.to = to;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
