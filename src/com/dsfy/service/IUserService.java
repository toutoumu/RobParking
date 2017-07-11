package com.dsfy.service;

import com.dsfy.entity.authority.User;

public interface IUserService extends IBaseService {

	/**
	 * 根据账号获取用户
	 * @param account
	 * @return
	 */
	public User getByAccount(String account);

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public boolean register(User user);
}
