package com.zmm.rabbitmq.service.impl;

import com.zmm.rabbitmq.common.ResponseCode;
import com.zmm.rabbitmq.common.ServerResponse;
import com.zmm.rabbitmq.config.RabbitConfig;
import com.zmm.rabbitmq.mapper.MsgLogMapper;
import com.zmm.rabbitmq.mq.MessageHelper;
import com.zmm.rabbitmq.pojo.Mail;
import com.zmm.rabbitmq.pojo.MsgLog;
import com.zmm.rabbitmq.service.TestService;
import com.zmm.rabbitmq.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Name TestServiceImpl
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Service
public class TestServiceImpl implements TestService {

	@Resource
	private MsgLogMapper msgLogMapper;

	@Autowired
	private RabbitTemplate rabbitTemplate;


	@Override
	public ServerResponse testIdempotence() {
		return ServerResponse.success("testIdempotence: success");
	}

	@Override
	public ServerResponse accessLimit() {
		return ServerResponse.success("accessLimit: success");
	}

	@Override
	public ServerResponse send(Mail mail) {
		String msgId = RandomUtil.UUID32();
		mail.setMsgId(msgId);

		MsgLog msgLog = new MsgLog(msgId, mail, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
		// 消息入库
		msgLogMapper.insert(msgLog);

		CorrelationData correlationData = new CorrelationData(msgId);
		// 发送消息
		rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);

		return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
	}
}
