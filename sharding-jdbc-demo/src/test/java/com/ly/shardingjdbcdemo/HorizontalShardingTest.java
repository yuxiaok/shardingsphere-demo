package com.ly.shardingjdbcdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.shardingjdbcdemo.entity.Dict;
import com.ly.shardingjdbcdemo.entity.Order;
import com.ly.shardingjdbcdemo.entity.OrderItem;
import com.ly.shardingjdbcdemo.entity.OrderVO;
import com.ly.shardingjdbcdemo.mapper.DictMapper;
import com.ly.shardingjdbcdemo.mapper.OrderItemMapper;
import com.ly.shardingjdbcdemo.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 水平分片 </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉06⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class HorizontalShardingTest {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private DictMapper dictMapper;

	@Test
	public void testInsert() {
		Order order = new Order();
		order.setOderNo("ereew");
		order.setUserId(1L);
		order.setAmount(BigDecimal.valueOf(1));

		orderMapper.insert(order);
	}

	/**
	 * 测试分库:行表达式分片算法
	 */
	@Test
	public void testInsertDatabaseStrategyInline() {
		for (long i = 1; i <= 4; i++) {
			Order order = new Order();
			order.setOderNo("ereew");
			order.setUserId(i);
			order.setAmount(BigDecimal.valueOf(1));

			orderMapper.insert(order);
		}
	}


	/**
	 * 测试分库:取模分片算法
	 */
	@Test
	public void testInsertDatabaseStrategyMod() {
		for (long i = 5; i <= 8; i++) {
			Order order = new Order();
			order.setOderNo("ereew");
			order.setUserId(i);
			order.setAmount(BigDecimal.valueOf(1));

			orderMapper.insert(order);
		}
	}

	/**
	 * 测试分表:哈希取模分片算法
	 */
	@Test
	public void testInsertTableStrategyMod() {
		for (long i = 100; i <= 104; i++) {
			Order order = new Order();
			order.setOderNo("ewrwejo00" + i);
			order.setUserId(1L);
			order.setAmount(BigDecimal.valueOf(1));

			orderMapper.insert(order);
		}
	}


	@Test
	public void testInsertTableStrategyMod2() {
		Order order = new Order();
		order.setAmount(BigDecimal.valueOf(1));
		order.setUserId(2L);
		order.setOderNo("ewrwejo00123");
		orderMapper.insert(order);

		Order order1 = new Order();
		order1.setAmount(BigDecimal.valueOf(1));
		order1.setUserId(2L);
		order1.setOderNo("ewrwejo001dfg");
		orderMapper.insert(order1);

		Order order2 = new Order();
		order2.setAmount(BigDecimal.valueOf(1));
		order2.setUserId(2L);
		order2.setOderNo("ewrwejo00102rty");
		orderMapper.insert(order2);

		Order order3 = new Order();
		order3.setAmount(BigDecimal.valueOf(1));
		order3.setUserId(2L);
		order3.setOderNo("ewrwejo0010f3");
		orderMapper.insert(order3);

	}


	@Test
	public void testHashMod() {
		System.out.println("ewrwejo00123".hashCode() % 2);
		System.out.println("ewrwejo001dfg".hashCode() % 2);
		System.out.println("ewrwejo00102rty".hashCode() % 2);
		System.out.println("ewrwejo0010f3".hashCode() % 2);
	}


	@Test
	public void testInsert111() {
		for (long i = 1; i <= 4; i++) {
			Order order = new Order();
			order.setOderNo("" + i);
			order.setUserId(1L);
			order.setAmount(BigDecimal.valueOf(1));
			orderMapper.insert(order);
		}

		for (long i = 5; i <= 8; i++) {
			Order order = new Order();
			order.setOderNo("" + i);
			order.setUserId(2L);
			order.setAmount(BigDecimal.valueOf(1));
			orderMapper.insert(order);
		}
	}

	/**
	 * 水平分片：查询所有
	 */
	@Test
	public void testSelectAll() {
		List<Order> orders = orderMapper.selectList(null);
		orders.forEach(System.out::println);
	}

	@Test
	public void testSelectByUserId() {
		List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().eq("user_id", 1L));
		orders.forEach(System.out::println);

		//会跨库查询，会union all表
//		List<Order> orders1 = orderMapper.selectList(new QueryWrapper<Order>().ge("order_no", 1L).le("order_no", 4L));
//		orders1.forEach(System.out::println);
//
//		//不会跨库，会union all表
//		List<Order> orders2 = orderMapper.selectList(new QueryWrapper<Order>().eq("user_id", 1L).ge("order_no", 1L).le("order_no", 4L));
//		orders2.forEach(System.out::println);

		//非分片键查询
		//不会跨库，会union all表
		orderMapper.selectList(new QueryWrapper<Order>().eq("user_id", 1L).ge("amount",1L).le("amount",10L));

		//会跨库，会union all表
		orderMapper.selectList(new QueryWrapper<Order>().ge("amount",1L).le("amount",10L));
	}


	/**
	 * 测试关联表
	 */
	@Test
	public void testInsertOrderAndOrderItem() {
		for (long i = 1; i <= 4; i++) {
			Order order = new Order();
			order.setOderNo("" + i);
			order.setUserId(1L);
			orderMapper.insert(order);
			for (int j = 0; j < 3; j++) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderNo("" + i);
				orderItem.setUserId(1L);
				orderItem.setPrice(BigDecimal.valueOf(2L));
				orderItem.setCount(10);
				orderItemMapper.insert(orderItem);
			}
		}

		for (long i = 5; i <= 8; i++) {
			Order order = new Order();
			order.setOderNo("" + i);
			order.setUserId(2L);
			order.setAmount(BigDecimal.valueOf(1));
			orderMapper.insert(order);
			for (int j = 0; j < 3; j++) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrderNo("" + i);
				orderItem.setUserId(2L);
				orderItem.setPrice(BigDecimal.valueOf(2L));
				orderItem.setCount(10);
				orderItemMapper.insert(orderItem);
			}
		}
	}

	/**
	 * 如果不配置绑定表：测试的actual sql为8条，多表关联查询会出现笛卡尔积关联
	 * 如果配置绑定表：测试的actual sql为4条，多表关联查询不会出现笛卡尔积关联，关联查询效率将大大提升
	 * 绑定表：指分片规则一致的一组分片表，使用绑定表进行多表关联查询时，必须使用分片键进行关联，否则会出现笛卡尔积关联或跨库关联，从而影响查询效率。
	 */
	@Test
	public void testSelectOrderVo() {
		List<OrderVO> orderVOS = orderMapper.list();
		orderVOS.forEach(System.out::println);
	}

	/**
	 * 广播表：所有的分片数据源中都存在的表，表结构及其数据在每个数据库中均完全一致。适用于数据量不大且需要与海量数据的表进行关联查询的场景，例如：字典表。
	 * 广播具有以下特性：
	 * （1）插入、更新操作会实时在所有节点上执行，保持各个分片的数据一致性。
	 * （2）查询操作，只会从一个节点获取。
	 * （3）可以和任何一个表进行Join操作。
	 */
	@Test
	public void testInsertDict() {
		Dict dict = new Dict();
		dict.setDictType("typoe1");
		dictMapper.insert(dict);
	}

	/**
	 * 只会从一个数据源获取数据，随机负载均衡规则
	 */
	@Test
	public void testSelectBroadcast() {
		List<Dict> dicts = dictMapper.selectList(null);
		dicts.forEach(System.out::println);
	}
}