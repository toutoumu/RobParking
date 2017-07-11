package com.dsfy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.Parking;
import com.dsfy.entity.authority.User;
import com.dsfy.entity.http.JsonRequest;
import com.dsfy.entity.http.JsonResponse;
import com.dsfy.entity.relation.PK_User_Parking;
import com.dsfy.entity.relation.User_Parking;
import com.dsfy.exception.JsonException;
import com.dsfy.service.IUser_ParkingService;

@Controller
@RequestMapping(value = "/User_ParkingController", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
public class User_ParkingController {

	@Resource(name = "User_ParkingService")
	IUser_ParkingService upService;

	@ResponseBody
	@RequestMapping(value = "/addRelation.do")
	public JsonResponse addRelation(@RequestBody JsonRequest jsonRequest) {
		User_Parking up = jsonRequest.getData("User_Parking", User_Parking.class);
		if (up.getParkingId() == 0 || up.getUserId() == 0) {
			throw new JsonException("主键不能为空");
		}
		PK_User_Parking pk = new PK_User_Parking();
		pk.setUserId(up.getUserId());
		pk.setParkingId(up.getParkingId());

		if (upService.getById(User_Parking.class, pk) != null) {
			throw new JsonException("关联关系已经存在");
		}
		upService.add(up);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("添加成功");
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/removeRelation.do")
	public JsonResponse removeRelation(@RequestBody JsonRequest jsonRequest) {
		User_Parking up = jsonRequest.getData("User_Parking", User_Parking.class);
		if (up.getParkingId() == 0 || up.getUserId() == 0) {
			throw new JsonException("主键不能为空");
		}

		PK_User_Parking pk = new PK_User_Parking();
		pk.setUserId(up.getUserId());
		pk.setParkingId(up.getParkingId());

		up = upService.getById(User_Parking.class, pk);
		if (up == null) {
			throw new JsonException("关联关系不存在");
		}
		upService.delete(User_Parking.class, pk);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("删除成功");
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/getUserByParking.do")
	public JsonResponse getUserByParking(@RequestBody JsonRequest jsonRequest) {
		User_Parking up = jsonRequest.getData("User_Parking", User_Parking.class);
		if (up.getParkingId() == 0) {
			throw new JsonException("主键不能为空");
		}
		List<User> users = upService.getUserByParking(up.getParkingId());
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setData("users", users);
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/getParkingByUser.do")
	public JsonResponse getParkingByUser(@RequestBody JsonRequest jsonRequest) {
		User_Parking up = jsonRequest.getData("User_Parking", User_Parking.class);
		if (up.getUserId() == 0) {
			throw new JsonException("主键不能为空");
		}
		List<Parking> parkings = upService.getParkingByUser(up.getUserId());
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("根据用户查询停车场成功");
		jsonResponse.setData("parkings", parkings);
		return jsonResponse;
	}

}
