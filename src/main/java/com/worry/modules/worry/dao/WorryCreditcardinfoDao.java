/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.worry.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.worry.modules.worry.entity.WorryCreditcardinfo;

/**
 * 信用卡信息表DAO接口
 * @author 吴栋林
 * @version 2018-09-15
 */
@MyBatisDao
public interface WorryCreditcardinfoDao extends CrudDao<WorryCreditcardinfo> {
	
}