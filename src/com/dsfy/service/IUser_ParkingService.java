package com.dsfy.service;

import java.util.List;

import com.dsfy.entity.Parking;
import com.dsfy.entity.authority.User;

/**
 * 用户和停车场关系维护
 * @author toutoumu
 *
 */
public interface IUser_ParkingService extends IBaseService {
	/**
	 * 根据用户(停车场管理员)主键获取管理的停车场
	 * @param userId
	 * @return
	 */
	List<Parking> getParkingByUser(int userId);

	/**
	 * 根据停车场主键获取停车场管理员
	 * @param parkingId
	 * @return
	 */
	List<User> getUserByParking(int parkingId);
}
