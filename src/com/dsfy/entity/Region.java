package com.dsfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Region")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 名字
	 */
	@Column
	private String name;

	/**
	 * 父节点ID
	 */
	@Column
	private int parentId;

	/**
	 * 节点类型
	 */
	@Column
	private int regionType;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 地区名称(省份,城市,区)
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 地区名称(省份,城市,区)
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 父节点主键
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * 父节点主键
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * 区域类型(1.省,2.城市,3.区(乡镇,县)
	 * @return the regionType
	 */
	public int getRegionType() {
		return regionType;
	}

	/**
	 * 区域类型(1.省,2.城市,3.区(乡镇,县)
	 * @param regionType the regionType to set
	 */
	public void setRegionType(int regionType) {
		this.regionType = regionType;
	}
}
