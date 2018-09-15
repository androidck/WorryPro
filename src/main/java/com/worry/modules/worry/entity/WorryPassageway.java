/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.worry.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 通道列表Entity
 * @author 吴栋林
 * @version 2018-09-15
 */
@Table(name="worry_passageway", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="passname", attrName="passname", label="通道名称"),
		@Column(name="passrate", attrName="passrate", label="通道费率"),
		@Column(name="passtype", attrName="passtype", label="商户类型"),
		@Column(name="isusable", attrName="isusable", label="是否可用"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class WorryPassageway extends DataEntity<WorryPassageway> {
	
	private static final long serialVersionUID = 1L;
	private String passname;		// 通道名称
	private String passrate;		// 通道费率
	private String passtype;		// 商户类型
	private String isusable;		// 是否可用
	
	public WorryPassageway() {
		this(null);
	}

	public WorryPassageway(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="通道名称长度不能超过 50 个字符")
	public String getPassname() {
		return passname;
	}

	public void setPassname(String passname) {
		this.passname = passname;
	}
	
	@Length(min=0, max=100, message="通道费率长度不能超过 100 个字符")
	public String getPassrate() {
		return passrate;
	}

	public void setPassrate(String passrate) {
		this.passrate = passrate;
	}
	
	@Length(min=0, max=1, message="商户类型长度不能超过 1 个字符")
	public String getPasstype() {
		return passtype;
	}

	public void setPasstype(String passtype) {
		this.passtype = passtype;
	}
	
	@Length(min=0, max=1, message="是否可用长度不能超过 1 个字符")
	public String getIsusable() {
		return isusable;
	}

	public void setIsusable(String isusable) {
		this.isusable = isusable;
	}
	
}