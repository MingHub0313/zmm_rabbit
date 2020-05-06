package com.zmm.rabbitmq.mq.listener;

import com.rabbitmq.client.Channel;
import com.zmm.rabbitmq.config.RabbitConfig;
import com.zmm.rabbitmq.mq.BaseConsumer;
import com.zmm.rabbitmq.mq.BaseConsumerProxy;
import com.zmm.rabbitmq.mq.consumer.MailConsumer;
import com.zmm.rabbitmq.service.MsgLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Name MailListener
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Component
public class MailListener {

	@Autowired
	private MailConsumer mailConsumer;

	@Autowired
	private MsgLogService msgLogService;

	@RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
	public void consume(Message message, Channel channel) throws IOException {
		BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
		BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
		if (null != proxy) {
			proxy.consume(message, channel);
		}
	}
}
