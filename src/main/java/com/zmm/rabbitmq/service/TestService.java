package com.zmm.rabbitmq.service;

import com.zmm.rabbitmq.common.ServerResponse;
import com.zmm.rabbitmq.pojo.Mail;

/**
 * @Name TestService
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface TestService {

	ServerResponse testIdempotence();

	ServerResponse accessLimit();

	/**
	 * 发送邮件
	 * @param mail
	 * @return
	 */
	ServerResponse send(Mail mail);
}
