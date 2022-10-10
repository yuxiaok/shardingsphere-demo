package com.ly.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : TODO </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉06⽇</li>
 * <li>@author : 8201</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@Data
@TableName("t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 3648205865347681246L;

	@TableId(type = IdType.AUTO)
	private Long id;

	private String uname;
}