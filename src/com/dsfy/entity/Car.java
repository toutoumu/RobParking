package com.dsfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dsfy.entity.authority.User;

/**
 * 车辆信息,用户可以不止一辆车
 * @author toutoumu
 *
 */
@Entity
@Table(name = "Car")
public class Car {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 外键用户id关联到{@link User}
	 */
	@Column
	private int userId;

	/**
	 * 车牌号
	 */
	@Column
	private String carNumber;

	/**
	 * 车龄
	 */
	@Column
	private int carAge;

	/**
	 * 车型
	 */
	@Column
	private String category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 外键用户id关联到{@link User}
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 外键用户id关联到{@link User}
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 车牌号
	 * @return
	 */
	public String getCarNumber() {
		return carNumber;
	}

	/**
	 * 车牌号
	 * @param carNumber
	 */
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	/**
	 * 车龄
	 * @return
	 */
	public int getCarAge() {
		return carAge;
	}

	/**
	 * 车龄
	 * @param carAge
	 */
	public void setCarAge(int carAge) {
		this.carAge = carAge;
	}

	/**
	 * 车型号
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 车型号
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
