package com.zmm.rabbitmq.mq.consumer;

import com.rabbitmq.client.Channel;
import com.zmm.rabbitmq.mq.BaseConsumer;
import com.zmm.rabbitmq.mq.MessageHelper;
import com.zmm.rabbitmq.pojo.LoginLog;
import com.zmm.rabbitmq.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Name LoginLogConsumer
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Component
@Slf4j
public class LoginLogConsumer implements BaseConsumer {

	@Autowired
	private LoginLogService loginLogService;

	@Override
	public void consume(Message message, Channel channel) throws IOException {
		log.info("收到消息: {}", message.toString());
		LoginLog loginLog = MessageHelper.msgToObj(message, LoginLog.class);
		loginLogService.insert(loginLog);
	}
}
