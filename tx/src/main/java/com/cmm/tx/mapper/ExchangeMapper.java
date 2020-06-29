package com.cmm.tx.mapper;

import java.math.BigDecimal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cmm.tx.pojo.Account;
import com.cmm.tx.pojo.Exchange;
@Mapper
public interface ExchangeMapper {
	
	/**
	 * 交易成功
	 * @param id
	 */
	@Update("update exchange set done=1 where id=#{id}")
	void update(int id);
	
	@Insert("insert into exchange (`from`,`to`,`total`)values(#{from},#{to},#{money})")
	void insert(int from,int to,BigDecimal money);
	
	/**
	 * 
	 * @param exchange
	 */
	@Options(useGeneratedKeys = true,keyProperty = "id")
	@Insert("insert into exchange (`from`,`to`,`total`)values(#{from.id},#{to.id},#{total})")
	void save(Exchange exchange);
	
	@Select("select * from exchange where id=#{id}")
	@Results({
		@Result(column = "total",property = "total"),
		@Result(column = "from",property = "from" ,javaType=Account.class,
				one=@One(select = "com.cmm.tx.mapper.AccountMapper.load")),
		@Result(column = "to",property = "to",javaType = Account.class,
				one = @One(select = "com.cmm.tx.mapper.AccountMapper.load"))
	})
	Exchange load(int id);
	
	@Select("select * from exchange")
	@Results({
		@Result(column = "total",property = "total"),
		@Result(column = "from",property = "from" ,javaType=Account.class,
				one=@One(select = "com.cmm.tx.mapper.AccountMapper.load")),
		@Result(column = "to",property = "to",javaType = Account.class,
				one = @One(select = "com.cmm.tx.mapper.AccountMapper.load"))
	})
	Exchange[] show();
	
}
