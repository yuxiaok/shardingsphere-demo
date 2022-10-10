package com.ly.shardingjdbcdemo;

import com.ly.shardingjdbcdemo.entity.User;
import com.ly.shardingjdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 读写分离 </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉06⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReadWriteSplitTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsert() {
		User user = new User();
		user.setUname("张三丰");
		userMapper.insert(user);
	}


	/**
	 * 事务测试
	 */
	@Transactional//开启事务之后，读写都会走主库，而且Springboot test会自动回滚数据
	@Test
	public void testTransactional() {
		User user = new User();
		user.setUname("铁锤");
		userMapper.insert(user);

		List<User> users = userMapper.selectList(null);
	}

	/**
	 * 负载均衡测试
	 */
	@Test
	public void testSelectAll() {
		List<User> users1 = userMapper.selectList(null);
		List<User> users2 = userMapper.selectList(null);
		List<User> users3 = userMapper.selectList(null);
		List<User> users4 = userMapper.selectList(null);
		users1.forEach(System.out::println);
	}
}