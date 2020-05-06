package com.zmm.rabbitmq.mq.consumer;

import com.rabbitmq.client.Channel;
import com.zmm.rabbitmq.exception.ServiceException;
import com.zmm.rabbitmq.mq.BaseConsumer;
import com.zmm.rabbitmq.mq.MessageHelper;
import com.zmm.rabbitmq.pojo.Mail;
import com.zmm.rabbitmq.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Name MailConsumer
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Component
@Slf4j
public class MailConsumer implements BaseConsumer {

	@Autowired
	private MailUtil mailUtil;

	@Override
	public void consume(Message message, Channel channel) throws IOException {
		Mail mail = MessageHelper.msgToObj(message, Mail.class);
		log.info("收到消息: {}", mail.toString());

		boolean success = mailUtil.send(mail);
		if (!success) {
			throw new ServiceException("send mail error");
		}
	}
}
