package com.dsfy.entity.authority;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 用户表</br>
 * 用户根据角色区分(管理员,代理商等等)
 * @author toutoumu
 *
 */
@Entity
@Table(name = "T_User")
public class User implements Serializable {

	private static final long serialVersionUID = 7419229779731522702L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private int id;

	@Column(length = 50, unique = true)
	private String account;

	@Column(length = 100)
	//@JsonIgnore
	//@Transient
	//springmvc生成json不包含此字段	
	private String password;

	@Column(length = 50)
	private String nickname;

	//@JsonIgnore
	//@Transient
	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinTable(name = "User_Role", joinColumns = { @JoinColumn(name = "userid", referencedColumnName = "userid") }, inverseJoinColumns = { @JoinColumn(name = "roleid", referencedColumnName = "roleid") })
	private Collection<Role> roles;

	/**
	 * 用户类别: 1.管理员,2.用户,3.代理商
	 */
	@Column
	private int category;

	/**
	 * 是否锁定
	 */
	private boolean locked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 用户帐号
	 * @return
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 用户帐号
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 昵称
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 昵称
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 角色列表
	 * @return
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	/**
	 * 角色列表
	 * @param roles
	 */
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	public String toString() {
		return account;
	}

	/**
	 * 用户类别: 1.管理员,2.用户,3.代理商
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * 用户类别: 1.管理员,2.用户,3.代理商
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * 是否锁定
	 */
	public boolean getLocked() {
		return locked;
	}

	/**
	 * 是否锁定
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
