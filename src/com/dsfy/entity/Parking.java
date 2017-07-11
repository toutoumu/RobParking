package com.dsfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Parking")
public class Parking {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 外键(城市id)
	 */
	@Column
	private int regionId;

	/**
	 * 外键(用户id)
	 */
	//@Column
	//private int userId;

	/**
	 * 城市名称
	 */
	@Column
	private String region;

	/**
	 * 停车场名称
	 */
	@Column
	private String name;

	/**
	 * 地址
	 */
	@Column
	private String address;

	/**
	 * 车位总数
	 */
	@Column
	private int total;

	/**
	 * 剩余车位数
	 */
	@Column
	private int remain;

	/**
	 * 管理员名称
	 */
	@Column
	private String mamager;

	/**
	 * 联系方式
	 */
	@Column
	private String phoneNumber;

	/**
	 * 楼层数
	 */
	@Column
	private int floorLevels;

	/**
	 * 坐标
	 */
	@Column
	private String coordinate;

	@Column
	private int parkingType;

	/**
	 * 是否通过 0.未知, 1.未审核,2.已审核
	 */
	@Column
	private int passState;

	@Column
	private String imageUrl;

	@Column
	private String parkingImage;

	@Column
	private double startPrice;

	/**
	 * 停车场图片
	 * @return
	 */
	public String getParkingImage() {
		return parkingImage;
	}

	/**
	 * 停车场图片
	 * @param parkingImage
	 */
	public void setParkingImage(String parkingImage) {
		this.parkingImage = parkingImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	//	public int getUserId() {
	//		return userId;
	//	}
	//
	//	public void setUserId(int userId) {
	//		this.userId = userId;
	//	}

	/**
	 * 区域名称(城市)
	 * @return
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * 区域名称(城市)
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * 停车场名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 停车场名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 停车场地址
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 停车场地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 车位总数
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 车位总数
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 当前剩余车位数
	 * @return
	 */
	public int getRemain() {
		return remain;
	}

	/**
	 * 当前剩余车位数
	 * @param remain
	 */
	public void setRemain(int remain) {
		this.remain = remain;
	}

	/**
	 * 管理员名称
	 * @return
	 */
	public String getMamager() {
		return mamager;
	}

	/**
	 * 管理员名称
	 * @param mamager
	 */
	public void setMamager(String mamager) {
		this.mamager = mamager;
	}

	/**
	 * 联系电话
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 联系电话
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 停车场层数
	 * @return
	 */
	public int getFloorLevels() {
		return floorLevels;
	}

	/**
	 * 停车场层数
	 * @param floorLevels
	 */
	public void setFloorLevels(int floorLevels) {
		this.floorLevels = floorLevels;
	}

	/**
	 * 停车场坐标
	 * @return
	 */
	public String getCoordinate() {
		return coordinate;
	}

	/**
	 * 停车场坐标
	 * @param coordinate
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * 是否通过 0.未知, 1.未审核,2.已审核
	 * @return
	 */
	public int getPassState() {
		return passState;
	}

	/**
	 * 是否通过 是否通过 0.未知, 1.未审核,2.已审核
	 * @param pass
	 */
	public void setPassState(int pass) {
		this.passState = pass;
	}

	/**
	 * 停车场类型
	 * @return
	 */
	public int getParkingType() {
		return parkingType;
	}

	/**
	 * 停车场类型
	 * @param parkingType
	 */
	public void setParkingType(int parkingType) {
		this.parkingType = parkingType;
	}

	/**
	 * 价格图片路径
	 * @return
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 价格图片路径
	 * @param imageUrl
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 起始价格
	 * @return
	 */
	public double getStartPrice() {
		return startPrice;
	}

	/**
	 * 起始价格
	 * @return
	 */
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

}
