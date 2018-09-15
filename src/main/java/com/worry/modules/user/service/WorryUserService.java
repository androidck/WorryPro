/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.worry.modules.user.entity.WorryUser;
import com.worry.modules.user.dao.WorryUserDao;

/**
 * 无忧支付用户表Service
 * @author 吴栋林
 * @version 2018-09-15
 */
@Service
@Transactional(readOnly=true)
public class WorryUserService extends CrudService<WorryUserDao, WorryUser> {
	
	/**
	 * 获取单条数据
	 * @param worryUser
	 * @return
	 */
	@Override
	public WorryUser get(WorryUser worryUser) {
		return super.get(worryUser);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param worryUser
	 * @return
	 */
	@Override
	public Page<WorryUser> findPage(Page<WorryUser> page, WorryUser worryUser) {
		return super.findPage(page, worryUser);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param worryUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WorryUser worryUser) {
		super.save(worryUser);
	}
	
	/**
	 * 更新状态
	 * @param worryUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WorryUser worryUser) {
		super.updateStatus(worryUser);
	}
	
	/**
	 * 删除数据
	 * @param worryUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WorryUser worryUser) {
		super.delete(worryUser);
	}
	
}