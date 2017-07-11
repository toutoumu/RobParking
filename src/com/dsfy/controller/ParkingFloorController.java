package com.dsfy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.dao.util.QueryCondition;
import com.dsfy.entity.ParkingFloor;
import com.dsfy.entity.http.JsonRequest;
import com.dsfy.entity.http.JsonResponse;
import com.dsfy.exception.JsonException;
import com.dsfy.service.IParkingFloorService;

@Controller
@RequestMapping(value = "/ParkingFloorController", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
public class ParkingFloorController {

	@Resource(name = "ParkingFloorService")
	private IParkingFloorService parkingFloorService;

	/**
	 * 添加停车场信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add.do")
	public JsonResponse add(@RequestBody JsonRequest jsonRequest) {
		ParkingFloor parkingFloor = jsonRequest.getData("parkingFloor", ParkingFloor.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (parkingFloor == null) {
			throw new JsonException("停车场不能为空");
		}
		if (parkingFloor.getParkingId() == 0) {
			throw new JsonException("所属停车场不能为空");
		}
		parkingFloorService.add(parkingFloor);
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	/**
	 * 编辑停车场信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/edit.do")
	public JsonResponse edit(@RequestBody JsonRequest jsonRequest) {
		ParkingFloor parkingFloor = jsonRequest.getData("parkingFloor", ParkingFloor.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (parkingFloor == null) {
			throw new JsonException("停车场不能为空");
		}
		if (parkingFloor.getParkingId() == 0) {
			throw new JsonException("停车场ID不能为空");
		}
		parkingFloorService.update(parkingFloor);
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	/**
	 * 删除停车场信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete.do")
	public JsonResponse delete(@RequestBody JsonRequest jsonRequest) {
		ParkingFloor parkingFloor = jsonRequest.getData("parkingFloor", ParkingFloor.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (parkingFloor == null || parkingFloor.getId() == 0) {
			throw new JsonException("停车场不能为空");
		}
		parkingFloorService.delete(ParkingFloor.class, parkingFloor.getId());
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	/**
	 * 根据主键查询
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getById.do")
	public JsonResponse getById(@RequestBody JsonRequest jsonRequest) {
		String id = jsonRequest.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			throw new JsonException("查询条件不能为空");
		}
		ParkingFloor parkingFloor = parkingFloorService.getById(ParkingFloor.class, Integer.parseInt(id));
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setData("parkingFloors", parkingFloor);
		return jsonResponse;
	}

	/**
	 * 查询停车场楼层信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getByParking.do")
	public JsonResponse getByParent(@RequestBody JsonRequest jsonRequest) {
		String id = jsonRequest.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			throw new JsonException("查询条件不能为空");
		}
		QueryCondition condition = new QueryCondition("parkingId", QueryCondition.EQ, Integer.parseInt(id));
		List<ParkingFloor> parkingFloors = parkingFloorService.get(ParkingFloor.class, condition);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setData("parkingFloors", parkingFloors);
		return jsonResponse;
	}

}
