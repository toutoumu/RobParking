package com.dsfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ParkingFloor")
public class ParkingFloor {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 外键关联到{@link Parking}
	 */
	@Column
	private int parkingId;

	/**
	 * 楼层名称如:一楼,负一楼等等
	 */
	@Column
	private String name;

	/**
	 * 该楼层车位数
	 */
	@Column
	private int total;

	/**
	 * 该楼层剩余车位数
	 */
	@Column
	private int remain;

	/**
	 * 该楼层的说明
	 */
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 所属停车场
	 * @return
	 */
	public int getParkingId() {
		return parkingId;
	}

	/**
	 * 所属停车场
	 * @param parkingId
	 */
	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

	/**
	 * 楼层名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 楼层名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 该楼层车位数
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 该楼层车位数
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 该楼层剩余车位数
	 * @return
	 */
	public int getRemain() {
		return remain;
	}

	/**
	 * 该楼层剩余车位数
	 * @param remain
	 */
	public void setRemain(int remain) {
		this.remain = remain;
	}

	/**
	 * 楼层说明
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 楼层说明
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
