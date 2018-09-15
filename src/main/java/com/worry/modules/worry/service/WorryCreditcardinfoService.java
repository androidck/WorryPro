/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.worry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.worry.modules.worry.entity.WorryCreditcardinfo;
import com.worry.modules.worry.dao.WorryCreditcardinfoDao;

/**
 * 信用卡信息表Service
 * @author 吴栋林
 * @version 2018-09-15
 */
@Service
@Transactional(readOnly=true)
public class WorryCreditcardinfoService extends CrudService<WorryCreditcardinfoDao, WorryCreditcardinfo> {
	
	/**
	 * 获取单条数据
	 * @param worryCreditcardinfo
	 * @return
	 */
	@Override
	public WorryCreditcardinfo get(WorryCreditcardinfo worryCreditcardinfo) {
		return super.get(worryCreditcardinfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param worryCreditcardinfo
	 * @return
	 */
	@Override
	public Page<WorryCreditcardinfo> findPage(Page<WorryCreditcardinfo> page, WorryCreditcardinfo worryCreditcardinfo) {
		return super.findPage(page, worryCreditcardinfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param worryCreditcardinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WorryCreditcardinfo worryCreditcardinfo) {
		super.save(worryCreditcardinfo);
	}
	
	/**
	 * 更新状态
	 * @param worryCreditcardinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WorryCreditcardinfo worryCreditcardinfo) {
		super.updateStatus(worryCreditcardinfo);
	}
	
	/**
	 * 删除数据
	 * @param worryCreditcardinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WorryCreditcardinfo worryCreditcardinfo) {
		super.delete(worryCreditcardinfo);
	}
	
}