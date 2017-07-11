package com.dsfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dsfy.entity.authority.User;

/**
 * 用户信息
 * @author toutoumu
 *
 */
@Entity
@Table(name = "UserInfo")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 外键关联到用户{@link User}
	 */
	@Column
	private int userId;

	/**
	 * 姓名
	 */
	@Column
	private String name;

	/**
	 * 性别
	 */
	@Column
	private String sex;

	/**
	 * 地区(城市)
	 */
	@Column
	private int regionId;

	/**
	 * 地区名称(城市)
	 */
	@Column
	private String region;

	/**
	 * 电话号码
	 */
	@Column
	private String phoneNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 姓名
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 性别
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 所属区域(城市)
	 * @return
	 */
	public int getRegionId() {
		return regionId;
	}

	/**
	 * 所属区域(城市)
	 */
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	/**
	 * 所属区域名称
	 * @return
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * 所属区域名称
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * 联系方式
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 联系方式
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
