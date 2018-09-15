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
import com.worry.modules.worry.entity.WorryPassageway;
import com.worry.modules.worry.service.WorryPassagewayService;

/**
 * 通道列表Controller
 * @author 吴栋林
 * @version 2018-09-15
 */
@Controller
@RequestMapping(value = "${adminPath}/worry/worryPassageway")
public class WorryPassagewayController extends BaseController {

	@Autowired
	private WorryPassagewayService worryPassagewayService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WorryPassageway get(String id, boolean isNewRecord) {
		return worryPassagewayService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("worry:worryPassageway:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorryPassageway worryPassageway, Model model) {
		model.addAttribute("worryPassageway", worryPassageway);
		return "modules/worry/worryPassagewayList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("worry:worryPassageway:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WorryPassageway> listData(WorryPassageway worryPassageway, HttpServletRequest request, HttpServletResponse response) {
		Page<WorryPassageway> page = worryPassagewayService.findPage(new Page<WorryPassageway>(request, response), worryPassageway); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("worry:worryPassageway:view")
	@RequestMapping(value = "form")
	public String form(WorryPassageway worryPassageway, Model model) {
		model.addAttribute("worryPassageway", worryPassageway);
		return "modules/worry/worryPassagewayForm";
	}

	/**
	 * 保存通道列表
	 */
	@RequiresPermissions("worry:worryPassageway:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WorryPassageway worryPassageway) {
		worryPassagewayService.save(worryPassageway);
		return renderResult(Global.TRUE, text("保存通道列表成功！"));
	}
	
	/**
	 * 删除通道列表
	 */
	@RequiresPermissions("worry:worryPassageway:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WorryPassageway worryPassageway) {
		worryPassagewayService.delete(worryPassageway);
		return renderResult(Global.TRUE, text("删除通道列表成功！"));
	}
	
}