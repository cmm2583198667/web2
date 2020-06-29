package com.cmm.tx.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmm.tx.exception.NoAccountException;
import com.cmm.tx.exception.NoMoneyException;
import com.cmm.tx.pojo.Exchange;
import com.cmm.tx.service.BankService;

@RestController
public class HomeController {
	
	//控制器依赖于业务逻辑的定义（接口）
	@Autowired
	BankService bankService;
	@PostMapping("/tx")
	public Map<String, Object>  test(@RequestBody HashMap<String, String> info) {
		
		int from = Integer.parseInt(info.get("from"));
		int to = Integer.parseInt(info.get("to"));
		int m = Integer.parseInt(info.get("m"));
		Map<String, Object> data=new HashMap<>();
		try {
			Exchange exchange=bankService.exchange(from, to,BigDecimal.valueOf(m));
			data.put("msg", "ok");
			data.put("info", exchange);
		} catch (NoMoneyException e) {
//			e.printStackTrace();
			data.put("msg", e.getMessage());
		}
		return data;
	}
	@GetMapping("/show")
	public Exchange[] show() {
		
		Exchange[]exchanges=bankService.show();
		return exchanges;
	}
	@GetMapping("/tx/{from}/{to}/{money}")
	public Map<String,Object> tx(@PathVariable int from,
			@PathVariable int to,
			@PathVariable BigDecimal money) throws NoAccountException {
		Map<String, Object> data=new HashMap<>();
		try {
			Exchange exchange=bankService.exchange(from, to, money);
			data.put("msg", "ok");
			data.put("info", exchange);
		} catch (NoMoneyException e) {
			
//			e.printStackTrace();
			data.put("msg", e.getMessage());
		}
		return data;
	}
}
