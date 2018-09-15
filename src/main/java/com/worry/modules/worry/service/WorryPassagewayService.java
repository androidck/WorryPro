/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.worry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.worry.modules.worry.entity.WorryPassageway;
import com.worry.modules.worry.dao.WorryPassagewayDao;

/**
 * 通道列表Service
 * @author 吴栋林
 * @version 2018-09-15
 */
@Service
@Transactional(readOnly=true)
public class WorryPassagewayService extends CrudService<WorryPassagewayDao, WorryPassageway> {
	
	/**
	 * 获取单条数据
	 * @param worryPassageway
	 * @return
	 */
	@Override
	public WorryPassageway get(WorryPassageway worryPassageway) {
		return super.get(worryPassageway);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param worryPassageway
	 * @return
	 */
	@Override
	public Page<WorryPassageway> findPage(Page<WorryPassageway> page, WorryPassageway worryPassageway) {
		return super.findPage(page, worryPassageway);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param worryPassageway
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WorryPassageway worryPassageway) {
		super.save(worryPassageway);
	}
	
	/**
	 * 更新状态
	 * @param worryPassageway
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WorryPassageway worryPassageway) {
		super.updateStatus(worryPassageway);
	}
	
	/**
	 * 删除数据
	 * @param worryPassageway
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WorryPassageway worryPassageway) {
		super.delete(worryPassageway);
	}
	
}