/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.trade.web;

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
import com.worry.modules.trade.entity.WorryQuickTrade;
import com.worry.modules.trade.service.WorryQuickTradeService;

/**
 * 快捷交易Controller
 * @author 吴栋林
 * @version 2018-09-15
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/worryQuickTrade")
public class WorryQuickTradeController extends BaseController {

	@Autowired
	private WorryQuickTradeService worryQuickTradeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WorryQuickTrade get(String id, boolean isNewRecord) {
		return worryQuickTradeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("trade:worryQuickTrade:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorryQuickTrade worryQuickTrade, Model model) {
		model.addAttribute("worryQuickTrade", worryQuickTrade);
		return "modules/trade/worryQuickTradeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("trade:worryQuickTrade:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WorryQuickTrade> listData(WorryQuickTrade worryQuickTrade, HttpServletRequest request, HttpServletResponse response) {
		Page<WorryQuickTrade> page = worryQuickTradeService.findPage(new Page<WorryQuickTrade>(request, response), worryQuickTrade); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("trade:worryQuickTrade:view")
	@RequestMapping(value = "form")
	public String form(WorryQuickTrade worryQuickTrade, Model model) {
		model.addAttribute("worryQuickTrade", worryQuickTrade);
		return "modules/trade/worryQuickTradeForm";
	}

	/**
	 * 保存快捷交易
	 */
	@RequiresPermissions("trade:worryQuickTrade:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WorryQuickTrade worryQuickTrade) {
		worryQuickTradeService.save(worryQuickTrade);
		return renderResult(Global.TRUE, text("保存快捷交易成功！"));
	}
	
	/**
	 * 删除快捷交易
	 */
	@RequiresPermissions("trade:worryQuickTrade:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WorryQuickTrade worryQuickTrade) {
		worryQuickTradeService.delete(worryQuickTrade);
		return renderResult(Global.TRUE, text("删除快捷交易成功！"));
	}
	
}