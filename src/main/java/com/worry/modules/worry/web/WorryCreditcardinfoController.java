/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.worry.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.worry.modules.worry.entity.WorryCreditcardinfo;
import com.worry.modules.worry.service.WorryCreditcardinfoService;

/**
 * 信用卡信息表Controller
 * @author 吴栋林
 * @version 2018-09-15
 */
@Controller
@RequestMapping(value = "${adminPath}/worry/worryCreditcardinfo")
public class WorryCreditcardinfoController extends BaseController {

	@Autowired
	private WorryCreditcardinfoService worryCreditcardinfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WorryCreditcardinfo get(String id, boolean isNewRecord) {
		return worryCreditcardinfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("worry:worryCreditcardinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorryCreditcardinfo worryCreditcardinfo, Model model) {
		model.addAttribute("worryCreditcardinfo", worryCreditcardinfo);
		return "modules/worry/worryCreditcardinfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("worry:worryCreditcardinfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WorryCreditcardinfo> listData(WorryCreditcardinfo worryCreditcardinfo, HttpServletRequest request, HttpServletResponse response) {
		Page<WorryCreditcardinfo> page = worryCreditcardinfoService.findPage(new Page<WorryCreditcardinfo>(request, response), worryCreditcardinfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("worry:worryCreditcardinfo:view")
	@RequestMapping(value = "form")
	public String form(WorryCreditcardinfo worryCreditcardinfo, Model model) {
		model.addAttribute("worryCreditcardinfo", worryCreditcardinfo);
		return "modules/worry/worryCreditcardinfoForm";
	}

	/**
	 * 保存信用卡信息表
	 */
	@RequiresPermissions("worry:worryCreditcardinfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WorryCreditcardinfo worryCreditcardinfo) {
		worryCreditcardinfoService.save(worryCreditcardinfo);
		return renderResult(Global.TRUE, text("保存信用卡信息表成功！"));
	}
	
	/**
	 * 删除信用卡信息表
	 */
	@RequiresPermissions("worry:worryCreditcardinfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WorryCreditcardinfo worryCreditcardinfo) {
		worryCreditcardinfoService.delete(worryCreditcardinfo);
		return renderResult(Global.TRUE, text("删除信用卡信息表成功！"));
	}
	
}