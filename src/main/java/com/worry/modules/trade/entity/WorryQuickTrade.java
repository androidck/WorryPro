/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.trade.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 快捷交易Entity
 * @author 吴栋林
 * @version 2018-09-15
 */
@Table(name="worry_quick_trade", alias="a", columns={
		@Column(name="id", attrName="id", label="交易时间", isPK=true),
		@Column(name="userid", attrName="userid", label="用户id"),
		@Column(name="orderno", attrName="orderno", label="订单号"),
		@Column(name="realname", attrName="realname", label="交易人"),
		@Column(name="tradetime", attrName="tradetime", label="交易时间"),
		@Column(name="procedure", attrName="procedure", label="手续费"),
		@Column(name="tradestatus", attrName="tradestatus", label="交易状态"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class WorryQuickTrade extends DataEntity<WorryQuickTrade> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户id
	private String orderno;		// 订单号
	private String realname;		// 交易人
	private String tradetime;		// 交易时间
	private String procedure;		// 手续费
	private String tradestatus;		// 交易状态
	
	public WorryQuickTrade() {
		this(null);
	}

	public WorryQuickTrade(String id){
		super(id);
	}
	
	@Length(min=0, max=32, message="用户id长度不能超过 32 个字符")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=32, message="订单号长度不能超过 32 个字符")
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	@Length(min=0, max=32, message="交易人长度不能超过 32 个字符")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=32, message="交易时间长度不能超过 32 个字符")
	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}
	
	@Length(min=0, max=32, message="手续费长度不能超过 32 个字符")
	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	
	@Length(min=0, max=1, message="交易状态长度不能超过 1 个字符")
	public String getTradestatus() {
		return tradestatus;
	}

	public void setTradestatus(String tradestatus) {
		this.tradestatus = tradestatus;
	}
	
	public String getTradetime_gte() {
		return sqlMap.getWhere().getValue("tradetime", QueryType.GTE);
	}

	public void setTradetime_gte(String tradetime) {
		sqlMap.getWhere().and("tradetime", QueryType.GTE, tradetime);
	}
	
	public String getTradetime_lte() {
		return sqlMap.getWhere().getValue("tradetime", QueryType.LTE);
	}

	public void setTradetime_lte(String tradetime) {
		sqlMap.getWhere().and("tradetime", QueryType.LTE, tradetime);
	}
	
}