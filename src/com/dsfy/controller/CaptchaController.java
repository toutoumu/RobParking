package com.dsfy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.Captcha;
import com.dsfy.entity.http.JsonRequest;
import com.dsfy.entity.http.JsonResponse;
import com.dsfy.service.ICaptchaService;

@Controller
@RequestMapping(value = "/CaptchaController", method = RequestMethod.POST, produces =
{ "application/json;charset=UTF-8" })
public class CaptchaController
{
	@Resource(name = "CaptchaService")
	private ICaptchaService captchaService;

	/**
	 * 获取验证码
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCaptcha.do", method = RequestMethod.POST, consumes = "application/json")
	public JsonResponse getCaptcha(@RequestBody JsonRequest jsonRequest)
	{
		String phoneNumber = jsonRequest.getParameter("phoneNumber");
		Captcha captcha = captchaService.getCaptcha(phoneNumber);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(true);
		jsonResponse.setData("captcha", captcha);
		return jsonResponse;
	}

	/**
	 * 验证验证码
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validateCaptcha.do")
	public JsonResponse validateCaptcha(@RequestBody JsonRequest jsonRequest)
	{
		Captcha captcha = jsonRequest.getData("captcha", Captcha.class);
		boolean b = captchaService.validateCaptcha(captcha);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(b);
		jsonResponse.setMessage("验证通过");
		return jsonResponse;
	}
}
