package com.dsfy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文件名: Captcha.java</br>
 * 编写者: toutoumu</br>
 * 编写日期: 2014年7月25日</br>
 * 简要描述: 验证码</br>
 * 组件列表:</br>
 * ********************  修改日志  **********************************</br>
 * 修改人：           		  修改日期：</br>
 * 修改内容：</br>
 * 
 */
@Entity
@Table(name = "Captcha")
public class Captcha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column
	private String phoneNumber;

	@Column
	private int code;

	@Column
	private String message;

	@Column
	private Date createTime;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 电话号码
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 电话号码
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 验证码
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 验证码
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 验证消息
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 验证消息
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 验证码创建日期
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 验证码创建日期
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	};
}
