package com.zmm.rabbitmq.controller;

import com.zmm.rabbitmq.annotation.AccessLimit;
import com.zmm.rabbitmq.annotation.ApiIdempotent;
import com.zmm.rabbitmq.common.ServerResponse;
import com.zmm.rabbitmq.pojo.Mail;
import com.zmm.rabbitmq.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name TestController
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@Autowired
	private TestService testService;

	@ApiIdempotent
	@PostMapping("testIdempotence")
	public ServerResponse testIdempotence() {
		return testService.testIdempotence();
	}

	@AccessLimit(maxCount = 5, seconds = 5)
	@PostMapping("accessLimit")
	public ServerResponse accessLimit() {
		return testService.accessLimit();
	}

	@PostMapping("send")
	public ServerResponse sendMail(@Validated Mail mail, Errors errors) {
		if (errors.hasErrors()) {
			String msg = errors.getFieldError().getDefaultMessage();
			return ServerResponse.error(msg);
		}

		return testService.send(mail);
	}
}
