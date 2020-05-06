package com.zmm.rabbitmq.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @Name BaseConsumer
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
public interface BaseConsumer {

	/**
	 * 优惠券 消费
	 * @param message
	 * @param channel
	 * @throws IOException
	 */
	void consume(Message message, Channel channel) throws IOException;
}
