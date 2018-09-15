/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.worry.modules.trade.entity.WorryQuickTrade;
import com.worry.modules.trade.dao.WorryQuickTradeDao;

/**
 * 快捷交易Service
 * @author 吴栋林
 * @version 2018-09-15
 */
@Service
@Transactional(readOnly=true)
public class WorryQuickTradeService extends CrudService<WorryQuickTradeDao, WorryQuickTrade> {
	
	/**
	 * 获取单条数据
	 * @param worryQuickTrade
	 * @return
	 */
	@Override
	public WorryQuickTrade get(WorryQuickTrade worryQuickTrade) {
		return super.get(worryQuickTrade);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param worryQuickTrade
	 * @return
	 */
	@Override
	public Page<WorryQuickTrade> findPage(Page<WorryQuickTrade> page, WorryQuickTrade worryQuickTrade) {
		return super.findPage(page, worryQuickTrade);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param worryQuickTrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WorryQuickTrade worryQuickTrade) {
		super.save(worryQuickTrade);
	}
	
	/**
	 * 更新状态
	 * @param worryQuickTrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WorryQuickTrade worryQuickTrade) {
		super.updateStatus(worryQuickTrade);
	}
	
	/**
	 * 删除数据
	 * @param worryQuickTrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WorryQuickTrade worryQuickTrade) {
		super.delete(worryQuickTrade);
	}
	
}