/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.worry.modules.user.entity.WorryUser;

/**
 * 无忧支付用户表DAO接口
 * @author 吴栋林
 * @version 2018-09-15
 */
@MyBatisDao
public interface WorryUserDao extends CrudDao<WorryUser> {
	
}