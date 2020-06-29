package com.cmm.tx.mapper;

import java.math.BigDecimal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cmm.tx.pojo.Account;

@Mapper
public interface AccountMapper {
	
	@Select("select id,name from account where id=#{id}")
	Account load(int id);
	
	/**
	 * 查询余额
	 * @param id
	 * @return
	 */
	@Select("select balance from account where id=#{id}")
	BigDecimal getBalance(int id);

	/**
	 * 更新余额
	 * @param id账户编号
	 * @param money 金额（负数为支出，正数为收入）
	 */
	@Update("update account set balance=balance+#{money} where id=#{id}")
	void updateBalance(int id,BigDecimal money);
	
}
