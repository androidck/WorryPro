/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.user.web;

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
import com.worry.modules.user.entity.WorryUser;
import com.worry.modules.user.service.WorryUserService;

/**
 * 无忧支付用户表Controller
 * @author 吴栋林
 * @version 2018-09-15
 */
@Controller
@RequestMapping(value = "${adminPath}/user/worryUser")
public class WorryUserController extends BaseController {

	@Autowired
	private WorryUserService worryUserService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WorryUser get(String id, boolean isNewRecord) {
		return worryUserService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:worryUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorryUser worryUser, Model model) {
		model.addAttribute("worryUser", worryUser);
		return "modules/user/worryUserList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:worryUser:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WorryUser> listData(WorryUser worryUser, HttpServletRequest request, HttpServletResponse response) {
		Page<WorryUser> page = worryUserService.findPage(new Page<WorryUser>(request, response), worryUser); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:worryUser:view")
	@RequestMapping(value = "form")
	public String form(WorryUser worryUser, Model model) {
		model.addAttribute("worryUser", worryUser);
		return "modules/user/worryUserForm";
	}

	/**
	 * 保存用户管理
	 */
	@RequiresPermissions("user:worryUser:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WorryUser worryUser) {
		worryUserService.save(worryUser);
		return renderResult(Global.TRUE, text("保存用户管理成功！"));
	}
	
	/**
	 * 停用用户管理
	 */
	@RequiresPermissions("user:worryUser:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(WorryUser worryUser) {
		worryUser.setStatus(WorryUser.STATUS_DISABLE);
		worryUserService.updateStatus(worryUser);
		return renderResult(Global.TRUE, text("停用用户管理成功"));
	}
	
	/**
	 * 启用用户管理
	 */
	@RequiresPermissions("user:worryUser:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(WorryUser worryUser) {
		worryUser.setStatus(WorryUser.STATUS_NORMAL);
		worryUserService.updateStatus(worryUser);
		return renderResult(Global.TRUE, text("启用用户管理成功"));
	}
	
}