package com.ly.shardingjdbcdemo;

import com.ly.shardingjdbcdemo.entity.Order;
import com.ly.shardingjdbcdemo.entity.User;
import com.ly.shardingjdbcdemo.mapper.OrderMapper;
import com.ly.shardingjdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 垂直分片 </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉06⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VerticalShardingTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void testInsertUserAndOrder() {
		User user = new User();
		user.setUname("强哥");
		userMapper.insert(user);

		Order order = new Order();
		order.setOderNo("erewx");
		order.setUserId(user.getId());
		order.setAmount(BigDecimal.valueOf(12));
		orderMapper.insert(order);
	}

	@Test
	public void testSelect() {
		userMapper.selectById(2L);
		orderMapper.selectById(1L);
	}
}