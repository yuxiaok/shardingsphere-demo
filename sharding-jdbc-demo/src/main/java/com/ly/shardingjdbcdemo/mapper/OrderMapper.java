package com.ly.shardingjdbcdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.shardingjdbcdemo.entity.Order;
import com.ly.shardingjdbcdemo.entity.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * Enum
 * AnnotationType
 * <ul style="margin:15px;">
 * <li>Description : TODO </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉06⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

	@Select("SELECT o.order_no orderNo,\n" +
			"SUM(i.price * i.count) amount\n" +
			"FROM t_order o LEFT JOIN t_order_item i ON o.order_no = i.order_no\n" +
			"GROUP BY o.order_no\n")
	List<OrderVO> selectList();
}