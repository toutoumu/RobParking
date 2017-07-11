package com.dsfy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.Parking;
import com.dsfy.entity.http.JsonRequest;
import com.dsfy.entity.http.JsonResponse;
import com.dsfy.exception.JsonException;
import com.dsfy.service.IParkingService;

@Controller
@RequestMapping(value = "/ParkingController", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
public class ParkingController {

	@Resource(name = "ParkingService")
	private IParkingService parkingService;

	/**
	 * 添加停车场信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add.do")
	public JsonResponse add(@RequestBody JsonRequest jsonRequest) {
		Parking parking = jsonRequest.getData("parking", Parking.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (parking == null) {
			throw new JsonException("停车场不能为空");
		}
		parking.setPassState(1);
		parkingService.add(parking);
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
		Parking parking = jsonRequest.getData("parking", Parking.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (parking == null) {
			throw new JsonException("停车场不能为空");
		}
		if (parking.getId() == 0) {
			throw new JsonException("停车场ID不能为空");
		}
		parkingService.update(parking);
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
		Parking parking = jsonRequest.getData("parking", Parking.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (parking == null || parking.getId() == 0) {
			throw new JsonException("停车场不能为空");
		}
		parkingService.delete(Parking.class, parking.getId());
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	/**
	 * 通过审核(设置passState=2)
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pass.do")
	public JsonResponse pass(@RequestBody JsonRequest jsonRequest) {
		String id = jsonRequest.getParameter("id");
		JsonResponse jsonResponse = new JsonResponse();
		if (StringUtils.isEmpty(id)) {
			throw new JsonException("停车场不能为空");
		}
		parkingService.executeBySQL("update parking set passState = 2 where id = ?", id);
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	/**
	 * 根据条件查询停车场( 城市,城市id,停车场名称,是否通过审核)
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/byCondition.do")
	public JsonResponse getParkingByCondition(@RequestBody JsonRequest jsonRequest) {
		Parking parking = jsonRequest.getData("parking", Parking.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (parking == null) {
			throw new JsonException("停车场不能为空");
		}
		List<Parking> list = parkingService.getByCondition(parking);
		jsonResponse.setSuccess(true);
		jsonResponse.setData("parking", list);
		return jsonResponse;
	}
}
