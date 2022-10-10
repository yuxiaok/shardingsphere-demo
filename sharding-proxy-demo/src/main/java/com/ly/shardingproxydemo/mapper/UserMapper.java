package com.ly.shardingproxydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.shardingproxydemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>****************************************************************************
 * </p>
 * <p><b>Copyright © 2010-2022 shuncom team All Rights Reserved<b></p>
 * Enum
 * AnnotationType
 * <ul style="margin:15px;">
 * <li>Description : TODO </li>
 * <li>Version : 1.0.0</li>
 * <li>Creation : 2022年10⽉07⽇</li>
 * <li>@author : kai.yu</li>
 * </ul>
 * <p>****************************************************************************
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}