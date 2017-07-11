package com.dsfy.entity.authority;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_Permission")
public class Permission implements Serializable {

	private static final long serialVersionUID = -8792590494605747957L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pmsid")
	private int id;

	@Column(length = 50)
	private String name;

	@Column(length = 100)
	private String description;

	@Column(length = 50)
	private String permission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	private Permission parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Collection<Permission> children;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmss")
	private Collection<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 权限名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 权限名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 权限描述
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 权限描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 权限字符串如:</br>
	 * user:create</br>
	 * user:edit</br>
	 * user:delete</br>
	 * user:audit</br>
	 * @return
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 权限字符串如:</br>
	 * user:create</br>
	 * user:edit</br>
	 * user:delete</br>
	 * user:audit</br>
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * 父权限
	 * @return
	 */
	public Permission getParent() {
		return parent;
	}

	/**
	 * 父权限
	 * @param parent
	 */
	public void setParent(Permission parent) {
		this.parent = parent;
	}

	/**
	 * 子权限
	 * @return
	 */
	public Collection<Permission> getChildren() {
		return children;
	}

	/**
	 * 子权限
	 * @param children
	 */
	public void setChildren(Collection<Permission> children) {
		this.children = children;
	}

	/**
	 * 角色
	 * @return
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	/**
	 * 角色
	 * @param roles
	 */
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
