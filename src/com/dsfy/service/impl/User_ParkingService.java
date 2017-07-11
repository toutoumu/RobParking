package com.dsfy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dsfy.entity.Parking;
import com.dsfy.entity.authority.User;
import com.dsfy.service.IUser_ParkingService;

@Service("User_ParkingService")
public class User_ParkingService extends BaseService implements IUser_ParkingService {

	@Override
	public List<Parking> getParkingByUser(int userId) {
		//String jpql = "from Parking  p where p.id in ( select up.userId from User_Parking up where up.userId = ?)";
		String jpql = "from Parking  p where EXISTS ( select up.parkingId from User_Parking up where up.parkingId = p.id and up.userId = ?)";
		return baseDao.getByJpql(jpql, userId);
	}

	@Override
	public List<User> getUserByParking(int parkingId) {
		//String jpql = "from User u where u.id in ( select p.userId from User_Parking p where p.parkingId = ?)";
		String jpql = "from User u where EXISTS ( select up.userId from User_Parking up where up.userId = u.id and up.parkingId = ?)";
		return baseDao.getByJpql(jpql, parkingId);
	}
}
