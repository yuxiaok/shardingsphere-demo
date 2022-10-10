package com.ly.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 8461717757928177957L;

	@TableId(type = IdType.AUTO)
	private Long id;

	@TableField("order_no")
	private String orderNo;

	@TableField("user_id")
	private Long userId;

	private BigDecimal price;

	private Integer count;
}