package com.dsfy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.authority.User;
import com.dsfy.entity.http.JsonRequest;
import com.dsfy.entity.http.JsonResponse;
import com.dsfy.exception.JsonException;
import com.dsfy.service.IUserService;

@Controller
@RequestMapping(value = "/UserController")
public class UserController {

	@Resource(name = "UserService")
	private IUserService userService;

	@ResponseBody
	@RequestMapping(value = "/getUser.do", method = RequestMethod.POST)
	public JsonResponse getByCategory(@RequestBody JsonRequest jsonRequest) {
		User user = jsonRequest.getData("user", User.class);
		if (user == null || user.getCategory() == 0) {
			throw new JsonException("用户信息不能为空");
		}
		List<User> users = userService.getByJpql("from User where category = ?", user.getCategory());
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setData("user", users);
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	public JsonResponse add(@RequestBody JsonRequest jsonRequest) {
		User user = jsonRequest.getData("user", User.class);
		if (user == null || user.getCategory() == 0) {
			throw new JsonException("用户类别不能为空");
		}
		if (StringUtils.isEmpty(user.getAccount())) {
			throw new JsonException("帐号不能为空");
		}
		userService.add(user);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("用户添加成功");
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/editUser.do", method = RequestMethod.POST)
	public JsonResponse edit(@RequestBody JsonRequest jsonRequest) {
		User user = jsonRequest.getData("user", User.class);
		if (user == null || user.getId() == 0) {
			throw new JsonException("用户类别不能为空");
		}
		userService.update(user);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("用户添加成功");
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/disableUser.do", method = RequestMethod.POST)
	public JsonResponse disableUser(@RequestBody JsonRequest jsonRequest) {
		User user = jsonRequest.getData("user", User.class);
		if (user == null || user.getId() == 0) {
			throw new JsonException("用户信息不能为空");
		}
		user = userService.getById(User.class, user.getId());
		user.setLocked(true);
		userService.update(user);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/enableUser.do", method = RequestMethod.POST)
	public JsonResponse enableUser(@RequestBody JsonRequest jsonRequest) {
		User user = jsonRequest.getData("user", User.class);
		if (user == null || user.getId() == 0) {
			throw new JsonException("用户信息不能为空");
		}
		user = userService.getById(User.class, user.getId());
		user.setLocked(false);
		userService.update(user);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	//@RequiresRoles("administrator")
	public boolean register(User user) {
		return userService.register(user);
	}
}
