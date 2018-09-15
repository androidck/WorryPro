/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.trade.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.worry.modules.trade.entity.WorryQuickTrade;

/**
 * 快捷交易DAO接口
 * @author 吴栋林
 * @version 2018-09-15
 */
@MyBatisDao
public interface WorryQuickTradeDao extends CrudDao<WorryQuickTrade> {
	
}