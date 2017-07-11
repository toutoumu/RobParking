package com.dsfy.junit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.dsfy.entity.Parking;
import com.dsfy.entity.ParkingFloor;
import com.dsfy.service.IBaseService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml", "classpath:spring-mvc.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class TestPaking extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource(name = "BaseService")
	private IBaseService baseService;

	@Test
	public void initParking() {
		Parking parking = null;
		parking = new Parking();
		parking.setAddress("深南大道");
		parking.setRegionId(2);
		parking.setRegion("深圳");
		parking.setFloorLevels(1);
		parking.setTotal(200);
		parking.setRemain(200);
		parking.setPhoneNumber("15989348952");
		parking.setPassState(1);
		parking.setParkingType(1);
		parking.setName("西丽停车场");
		parking.setMamager("刘斌");
		parking.setCoordinate("123.3333,234.4444");
		baseService.add(parking);

		parking = new Parking();
		parking.setAddress("留仙大道");
		parking.setRegionId(2);
		parking.setRegion("深圳");
		parking.setFloorLevels(1);
		parking.setTotal(200);
		parking.setRemain(200);
		parking.setPhoneNumber("15989348952");
		parking.setPassState(1);
		parking.setParkingType(1);
		parking.setName("白石洲停车场");
		parking.setMamager("黄洪波");
		parking.setCoordinate("123.3333,234.4444");
		baseService.add(parking);

	}

	@Test
	public void testName() {

		Parking parking = baseService.getById(Parking.class, 1);

		ParkingFloor floor = new ParkingFloor();
		floor.setName("asdf");
		//parking.getParkingFloors().add(floor);
		baseService.add(floor);
	}

	@Test
	public void name() {
		//baseService.delete(ParkingFloor.class, 3);

		baseService.delete(Parking.class, 1);
	}

	@Test
	public void asdf() {
		Parking parking = baseService.getById(Parking.class, 1);
		Gson gson = new Gson();
		String string = gson.toJson(parking);
		System.out.println(string);
		parking = gson.fromJson(string, Parking.class);
	}

}
