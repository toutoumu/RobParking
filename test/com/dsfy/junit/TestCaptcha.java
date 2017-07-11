package com.dsfy.junit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.dsfy.entity.Captcha;
import com.dsfy.entity.Car;
import com.dsfy.exception.JsonException;
import com.dsfy.service.ICaptchaService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml", "classpath:spring-mvc.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class TestCaptcha extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource(name = "CaptchaService")
	private ICaptchaService captchaService;

	private static final String phone = "15989348952";

	@Test
	/**
	 * 测试获取验证码
	 */
	public void getc() {
		captchaService.getCaptcha(phone);
	}

	/**
	 * 验证验证码
	 */
	public void setc() {
		Captcha captcha = new Captcha();
		captcha.setPhoneNumber(phone);
		captcha.setCode(777777);
		captchaService.validateCaptcha(captcha);
	}

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Car.class);
		// 记录debug级别的信息  
		logger.debug("This is debug message.");
		// 记录info级别的信息  
		logger.info("This is info message.");
		// 记录error级别的信息  
		logger.error("This is error message.");
		logger.error(new NullPointerException("asfd"));
		logger.error("asf", new NullPointerException("asfd"));

	}

}
