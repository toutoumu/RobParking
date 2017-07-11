package com.dsfy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.http.JsonResponse;

/**
 * 全局异常处理
 * @author toutoumu
 *
 */
@Controller
public class ErrorController {

	@RequestMapping(value = "/error.do")
	@ResponseBody
	public JsonResponse error(HttpServletRequest request) {
		JsonResponse response = new JsonResponse();
		Exception ex = (Exception) request.getAttribute("exception");
		if (ex == null) {
			response.setSuccess(false);
			response.setMessage("未知错误");
			return response;
		}
		ex.printStackTrace();
		response.setSuccess(false);
		response.setMessage(ex.getMessage());
		return response;
		// 根据不同的异常做出不同的处理
		// if (ex instanceof JsonException) {
		// }else if(ex instanceof BusinessException.java){
		// }
	}
}
