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
 * <li>Creation : 2022年10⽉06⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@Data
@TableName("t_order")//在使用shardingsphere之后，该名称就是逻辑表名
public class Order implements Serializable {

	private static final long serialVersionUID = -7741808465034846665L;

	//水平分表的情况下必须使用业务主键分布式id
	//使用shardingsphere-jdbc的分布式序列时，会自动使用shardingsphere-jdbc的分布式序列
	//否则会使用数据库自增主键生成策略（默认就是分布式序列生成策略）
	@TableId(type = IdType.AUTO)
//	@TableId(type = IdType.ASSIGN_ID)//分布式id生成策略（默认）（使用shardingsphere-jdbc生成分布式序列时，不能使用这个，这个优先级更高）
	private Long id;

	@TableField("order_no")
	private String oderNo;

	private Long userId;

	private BigDecimal amount;
}