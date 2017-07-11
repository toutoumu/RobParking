package com.dsfy.service;

import com.dsfy.entity.Captcha;

public interface ICaptchaService extends IBaseService {
	/**
	 * 根据手机号码获取验证码
	 * @param phoneNumber
	 * @return
	 */
	Captcha getCaptcha(String phoneNumber);

	/**
	 * 验证验证码
	 * @param captcha
	 * @return
	 */
	boolean validateCaptcha(Captcha captcha);
}
