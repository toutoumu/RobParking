package com.dsfy.service;

import java.util.List;

import com.dsfy.entity.Parking;

public interface IParkingService extends IBaseService {

	/**
	 * 根据用户的主键获取停车场信息
	 * @param id
	 * @return
	 */
	List<Parking> getParkingByUser(int id);

	/**
	 * 更新车位数量
	 * @param id
	 * @param count 增加或者减少 增加传正数,减少传负数
	 */
	boolean updateParkingRemain(int id, int count);

	/**
	 * 根据条件查询
	 * @param parking
	 * @return
	 */
	List<Parking> getByCondition(Parking parking);
}
