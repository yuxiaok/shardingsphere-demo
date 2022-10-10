package com.ly.shardingproxydemo;

import com.ly.shardingproxydemo.entity.User;
import com.ly.shardingproxydemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : TODO </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉07⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReadWriteTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void selectAll() {
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}
}