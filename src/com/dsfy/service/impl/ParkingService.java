package com.dsfy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dsfy.dao.util.QueryCondition;
import com.dsfy.entity.Parking;
import com.dsfy.service.IParkingService;

@Service("ParkingService")
public class ParkingService extends BaseService implements IParkingService {

	@Override
	public List<Parking> getParkingByUser(int id) {
		QueryCondition condition = new QueryCondition("id", QueryCondition.EQ, id);
		List<QueryCondition> paras = new ArrayList<QueryCondition>();
		paras.add(condition);

		return baseDao.get(Parking.class, paras);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateParkingRemain(int id, int count) {
		Parking parking = baseDao.getById(Parking.class, id);
		if (parking != null) {
			parking.setRemain(parking.getRemain() + count);
			baseDao.update(parking);
			return true;
		}
		return false;
	}

	@Override
	public List<Parking> getByCondition(Parking parking) {
		if (parking == null) {
			return null;
		}
		List<QueryCondition> conditions = new ArrayList<QueryCondition>();
		QueryCondition condition = null;
		// 地区(城市)
		if (parking.getRegionId() != 0) {
			condition = new QueryCondition("regionId", QueryCondition.EQ, parking.getRegionId());
			conditions.add(condition);
		}

		// 城市名称
		if (!StringUtils.isEmpty(parking.getRegion())) {
			condition = new QueryCondition("region", QueryCondition.LK, parking.getRegion());
			conditions.add(condition);
		}

		// 停车场名称
		if (!StringUtils.isEmpty(parking.getName())) {
			condition = new QueryCondition("name", QueryCondition.LK, parking.getName());
			conditions.add(condition);
		}

		// 审核状态
		if (parking.getPassState() != 0) {
			condition = new QueryCondition("passState", QueryCondition.EQ, parking.getPassState());
			conditions.add(condition);
		}
		return baseDao.get(Parking.class, conditions);
	}
}
