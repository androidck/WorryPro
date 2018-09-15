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
 * 信用卡信息表Entity
 * @author 吴栋林
 * @version 2018-09-15
 */
@Table(name="worry_creditcardinfo", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="userid", attrName="userid", label="用户id"),
		@Column(name="cardno", attrName="cardno", label="信用卡号"),
		@Column(name="cardname", attrName="cardname", label="银行名称"),
		@Column(name="cardlogo", attrName="cardlogo", label="信用卡logo"),
		@Column(name="va_year", attrName="vaYear", label="有效期年"),
		@Column(name="va_month", attrName="vaMonth", label="有效期月"),
		@Column(name="cvn2", attrName="cvn2", label="签名栏后三位"),
		@Column(name="isusable", attrName="isusable", label="是否可用"),
		@Column(name="phone", attrName="phone", label="预留手机号"),
	}, orderBy="a.id DESC"
)
public class WorryCreditcardinfo extends DataEntity<WorryCreditcardinfo> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户id
	private String cardno;		// 信用卡号
	private String cardname;		// 银行名称
	private String cardlogo;		// 信用卡logo
	private String vaYear;		// 有效期年
	private String vaMonth;		// 有效期月
	private String cvn2;		// 签名栏后三位
	private String isusable;		// 是否可用
	private String phone;		// 预留手机号
	
	public WorryCreditcardinfo() {
		this(null);
	}

	public WorryCreditcardinfo(String id){
		super(id);
	}
	
	@Length(min=0, max=32, message="用户id长度不能超过 32 个字符")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=32, message="信用卡号长度不能超过 32 个字符")
	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	@Length(min=0, max=50, message="银行名称长度不能超过 50 个字符")
	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	
	@Length(min=0, max=100, message="信用卡logo长度不能超过 100 个字符")
	public String getCardlogo() {
		return cardlogo;
	}

	public void setCardlogo(String cardlogo) {
		this.cardlogo = cardlogo;
	}
	
	@Length(min=0, max=4, message="有效期年长度不能超过 4 个字符")
	public String getVaYear() {
		return vaYear;
	}

	public void setVaYear(String vaYear) {
		this.vaYear = vaYear;
	}
	
	@Length(min=0, max=2, message="有效期月长度不能超过 2 个字符")
	public String getVaMonth() {
		return vaMonth;
	}

	public void setVaMonth(String vaMonth) {
		this.vaMonth = vaMonth;
	}
	
	@Length(min=0, max=3, message="签名栏后三位长度不能超过 3 个字符")
	public String getCvn2() {
		return cvn2;
	}

	public void setCvn2(String cvn2) {
		this.cvn2 = cvn2;
	}
	
	@Length(min=0, max=1, message="是否可用长度不能超过 1 个字符")
	public String getIsusable() {
		return isusable;
	}

	public void setIsusable(String isusable) {
		this.isusable = isusable;
	}
	
	@Length(min=0, max=11, message="预留手机号长度不能超过 11 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}