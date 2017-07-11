package com.dsfy.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dsfy.entity.authority.User;
import com.dsfy.service.IUserService;

@Service("UserService")
public class UserService extends BaseService implements IUserService {

	public User getByAccount(String account) {
		User user = (User) baseDao.getUniqueResultByJpql("from User as o where o.account=?", account);
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean register(User user) {
		boolean flag = false;
		try {
			baseDao.save(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
