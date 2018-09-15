/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.worry.modules.user.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 无忧支付用户表Entity
 * @author 吴栋林
 * @version 2018-09-15
 */
@Table(name="worry_user", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="username", attrName="username", label="用户名"),
		@Column(name="password", attrName="password", label="密码"),
		@Column(name="realname", attrName="realname", label="真实姓名"),
		@Column(name="idcardno", attrName="idcardno", label="身份证号"),
		@Column(name="idcardfont", attrName="idcardfont", label="身份证正面照片"),
		@Column(name="idcardback", attrName="idcardback", label="身份证反面照片"),
		@Column(name="issue", attrName="issue", label="签发机关"),
		@Column(name="startdate", attrName="startdate", label="起始日期"),
		@Column(name="enddate", attrName="enddate", label="截止日期"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="userstate", attrName="userstate", label="用户状态"),
		@Column(name="islogin", attrName="islogin", label="是否启用"),
	}, orderBy="a.id DESC"
)
public class WorryUser extends DataEntity<WorryUser> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 用户名
	private String password;		// 密码
	private String realname;		// 真实姓名
	private String idcardno;		// 身份证号
	private String idcardfont;		// 身份证正面照片
	private String idcardback;		// 身份证反面照片
	private String issue;		// 签发机关
	private String startdate;		// 起始日期
	private String enddate;		// 截止日期
	private String createTime;		// 创建时间
	private String userstate;		// 用户状态
	private String islogin;		// 是否启用
	
	public WorryUser() {
		this(null);
	}

	public WorryUser(String id){
		super(id);
	}
	
	@Length(min=0, max=11, message="用户名长度不能超过 11 个字符")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=50, message="密码长度不能超过 50 个字符")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=50, message="真实姓名长度不能超过 50 个字符")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=18, message="身份证号长度不能超过 18 个字符")
	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	
	@Length(min=0, max=100, message="身份证正面照片长度不能超过 100 个字符")
	public String getIdcardfont() {
		return idcardfont;
	}

	public void setIdcardfont(String idcardfont) {
		this.idcardfont = idcardfont;
	}
	
	@Length(min=0, max=100, message="身份证反面照片长度不能超过 100 个字符")
	public String getIdcardback() {
		return idcardback;
	}

	public void setIdcardback(String idcardback) {
		this.idcardback = idcardback;
	}
	
	@Length(min=0, max=50, message="签发机关长度不能超过 50 个字符")
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	@Length(min=0, max=50, message="起始日期长度不能超过 50 个字符")
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
	@Length(min=0, max=50, message="截止日期长度不能超过 50 个字符")
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	@Length(min=0, max=50, message="创建时间长度不能超过 50 个字符")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=1, message="用户状态长度不能超过 1 个字符")
	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}
	
	@Length(min=0, max=1, message="是否启用长度不能超过 1 个字符")
	public String getIslogin() {
		return islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}
	
}