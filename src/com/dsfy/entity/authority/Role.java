package com.dsfy.entity.authority;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_Role")
public class Role implements Serializable {

	private static final long serialVersionUID = 6177417450707400228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private int id;

	@Column(length = 50)
	private String name;

	@Column(length = 50)
	private String description;

	@ManyToMany(mappedBy = "roles")
	@Basic(fetch = FetchType.LAZY)
	private Collection<User> users;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "Role_Permission", joinColumns = { @JoinColumn(name = "roleid", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "pmsid", updatable = false) })
	private Collection<Permission> pmss;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 角色名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 角色名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 用户列表
	 * @return
	 */
	public Collection<User> getUsers() {
		return users;
	}

	/**
	 * 用户列表
	 * @param users
	 */
	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	/**
	 * 权限列表
	 * @return
	 */
	public Collection<Permission> getPmss() {
		return pmss;
	}

	/**
	 * 权限列表
	 * @param pmss
	 */
	public void setPmss(Collection<Permission> pmss) {
		this.pmss = pmss;
	}

}
