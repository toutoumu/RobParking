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
import com.dsfy.entity.UserInfo;
import com.dsfy.entity.http.JsonRequest;
import com.dsfy.entity.http.JsonResponse;
import com.dsfy.exception.JsonException;
import com.dsfy.service.IUserInfoService;

@Controller
@RequestMapping(value = "/UserInfoController", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
public class UserInfoController {

	@Resource(name = "UserInfoService")
	private IUserInfoService userInfoService;

	/**
	 * 添加用户信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add.do")
	public JsonResponse add(@RequestBody JsonRequest jsonRequest) {
		UserInfo userInfo = jsonRequest.getData("userInfo", UserInfo.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (userInfo == null) {
			throw new JsonException("用户不能为空");
		}
		if (userInfo.getUserId() == 0) {
			throw new JsonException("用户ID不能为空");
		}

		userInfoService.add(userInfo);
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	/**
	 * 编辑用户信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/edit.do")
	public JsonResponse edit(@RequestBody JsonRequest jsonRequest) {
		UserInfo userInfo = jsonRequest.getData("userInfo", UserInfo.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (userInfo == null) {
			throw new JsonException("用户不能为空");
		}
		if (userInfo.getUserId() == 0) {
			throw new JsonException("用户ID不能为空");
		}
		userInfoService.update(userInfo);
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	/**
	 * 删除用户信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete.do")
	public JsonResponse delete(@RequestBody JsonRequest jsonRequest) {
		UserInfo userInfo = jsonRequest.getData("userInfo", UserInfo.class);
		JsonResponse jsonResponse = new JsonResponse();
		if (userInfo == null || userInfo.getId() == 0) {
			throw new JsonException("用户不能为空");
		}
		userInfoService.delete(UserInfo.class, userInfo.getId());
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
		UserInfo userInfo = userInfoService.getById(UserInfo.class, Integer.parseInt(id));
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setData("userInfo", userInfo);
		return jsonResponse;
	}

	/**
	 * 删除用户信息
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getByParent.do")
	public JsonResponse getByParent(@RequestBody JsonRequest jsonRequest) {
		String id = jsonRequest.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			throw new JsonException("查询条件不能为空");
		}
		QueryCondition condition = new QueryCondition("userId", QueryCondition.EQ, Integer.parseInt(id));
		List<UserInfo> userInfos = userInfoService.get(UserInfo.class, condition);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setData("userInfos", userInfos);
		return jsonResponse;
	}

}
