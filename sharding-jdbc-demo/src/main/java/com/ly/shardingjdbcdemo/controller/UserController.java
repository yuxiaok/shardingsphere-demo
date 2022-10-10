package com.ly.shardingjdbcdemo.controller;

import com.ly.shardingjdbcdemo.entity.Order;
import com.ly.shardingjdbcdemo.entity.User;
import com.ly.shardingjdbcdemo.mapper.OrderMapper;
import com.ly.shardingjdbcdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : TODO </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉06⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	@GetMapping("/selectAll")
	public List<User> selectAll() {
		return userMapper.selectList(null);
	}

	//垂直分库
	@GetMapping("/insert")
	@Transactional	//可以保证分布式事务
	public void  insert() {
		User user = new User();
		user.setUname("强哥");
		userMapper.insert(user);

		Order order = new Order();
		order.setOderNo("erewx");
		order.setUserId(user.getId());
		order.setAmount(BigDecimal.valueOf(12));
		orderMapper.insert(order);
	}
}