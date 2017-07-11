package com.dsfy.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.Captcha;
import com.dsfy.entity.authority.User;
import com.dsfy.entity.http.JsonRequest;
import com.dsfy.entity.http.JsonResponse;
import com.dsfy.exception.JsonException;
import com.dsfy.service.ICaptchaService;
import com.dsfy.service.IUserService;
import com.dsfy.util.JsonUtils;
import com.dsfy.util.ValidateCode;

@Controller
@RequestMapping(value = "/LoginController")
public class LoginController {

	@Resource(name = "CaptchaService")
	ICaptchaService captchaService;

	@Resource(name = "UserService")
	IUserService userService;

	/**
	 * 代理商登录,不是管理员 ,用户
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/proxyLogin.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public JsonResponse proxyLogin(@RequestBody JsonRequest jsonRequest) {
		Captcha captcha = jsonRequest.getData("captcha", Captcha.class);
		if (captchaService.validateCaptcha(captcha)) {
			User user = userService.getByAccount(captcha.getPhoneNumber());
			if (user == null) {
				throw new JsonException("用户不存在" + captcha.getPhoneNumber());
			}
			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setSuccess(true);
			jsonResponse.setData("user", user);
			return jsonResponse;
		}
		throw new JsonException("登录失败");
	}

	/**
	 * 用户登录,不是管理员,代理商
	 * @param jsonRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userLogin.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public JsonResponse userLogin(@RequestBody JsonRequest jsonRequest) {
		Captcha captcha = jsonRequest.getData("captcha", Captcha.class);
		if (captchaService.validateCaptcha(captcha)) {
			User user = userService.getByAccount(captcha.getPhoneNumber());
			if (user == null) {
				user = new User();
				user.setAccount(captcha.getPhoneNumber());
				user.setNickname(captcha.getPhoneNumber());
				user.setCategory(2);
				userService.add(user);
			}
			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setSuccess(true);
			jsonResponse.setData("user", user);
			return jsonResponse;
		}
		throw new JsonException("登录失败");
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String login(User currUser, HttpSession session, HttpServletRequest request) {
		String code = (String) session.getAttribute("validateCode");
		String submitCode = WebUtils.getCleanParam(request, "validateCode");
		if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code, submitCode.toLowerCase())) {
			return "redirect:/";
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(currUser.getAccount(), currUser.getPassword());
		token.setRememberMe(true);
		try {
			user.login(token);
			return "/system/main";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/loginBack.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public JsonResponse login1(@RequestBody JsonRequest jsonRequest, HttpSession session, HttpServletRequest request) {
		String code = (String) session.getAttribute("validateCode");
		String submitCode = jsonRequest.getParameter("validateCode");
		JsonResponse jsonResponse = new JsonResponse();
		if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code, submitCode.toLowerCase())) {
			throw new JsonException("验证码错误");
		}
		Subject user = SecurityUtils.getSubject();
		User currUser = jsonRequest.getData("user", User.class);
		UsernamePasswordToken token = new UsernamePasswordToken(currUser.getAccount(), currUser.getPassword());
		token.setRememberMe(true);
		try {
			user.login(token);
			// 重定向到主页
			jsonResponse.setSuccess(true);
			jsonResponse.setMessage("验证成功,跳转到主页");
			jsonResponse.setCode(302);
			jsonResponse.setLocation("/RobParking/system/main.jsp");
			return jsonResponse;
		} catch (AuthenticationException e) {
			throw new JsonException("用户名或密码错误", e);
		}
	}

	/**
	 * 生成验证码,用于后台登陆
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateCode.do")
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");
		//设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("validateCode", verifyCode);
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}

	public static void main(String[] args) {
		User user = new User();
		user.setAccount("123456789");
		user.setCategory(2);
		user.setId(23);
		user.setLocked(false);
		user.setNickname("dddddd");
		user.setPassword("dddddd");
		System.out.println(JsonUtils.getGson().toJson(user));
	}
}
