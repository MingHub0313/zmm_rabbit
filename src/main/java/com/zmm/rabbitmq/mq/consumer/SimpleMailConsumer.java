package com.zmm.rabbitmq.mq.consumer;

import com.rabbitmq.client.Channel;
import com.zmm.rabbitmq.common.Constant;
import com.zmm.rabbitmq.config.RabbitConfig;
import com.zmm.rabbitmq.mq.MessageHelper;
import com.zmm.rabbitmq.pojo.Mail;
import com.zmm.rabbitmq.pojo.MsgLog;
import com.zmm.rabbitmq.service.MsgLogService;
import com.zmm.rabbitmq.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Name SimpleMailConsumer
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Component
@Slf4j
public class SimpleMailConsumer {

	@Autowired
	private MsgLogService msgLogService;

	@Autowired
	private MailUtil mailUtil;

	@RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
	public void consume(Message message, Channel channel) throws IOException {
		Mail mail = MessageHelper.msgToObj(message, Mail.class);
		log.info("收到消息: {}", mail.toString());

		String msgId = mail.getMsgId();

		MsgLog msgLog = msgLogService.selectByMsgId(msgId);
		// 消费幂等性
		if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {
			log.info("重复消费, msgId: {}", msgId);
			return;
		}

		MessageProperties properties = message.getMessageProperties();
		long tag = properties.getDeliveryTag();

		boolean success = mailUtil.send(mail);
		if (success) {
			msgLogService.updateStatus(msgId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
			// 消费确认
			channel.basicAck(tag, false);
		} else {
			channel.basicNack(tag, false, true);
		}
	}
}
