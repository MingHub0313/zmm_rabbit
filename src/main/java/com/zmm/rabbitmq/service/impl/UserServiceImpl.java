package com.zmm.rabbitmq.service.impl;

import com.zmm.rabbitmq.common.Constant;
import com.zmm.rabbitmq.common.ResponseCode;
import com.zmm.rabbitmq.common.ServerResponse;
import com.zmm.rabbitmq.config.RabbitConfig;
import com.zmm.rabbitmq.mapper.MsgLogMapper;
import com.zmm.rabbitmq.mapper.UserMapper;
import com.zmm.rabbitmq.mq.MessageHelper;
import com.zmm.rabbitmq.pojo.LoginLog;
import com.zmm.rabbitmq.pojo.MsgLog;
import com.zmm.rabbitmq.pojo.User;
import com.zmm.rabbitmq.service.UserService;
import com.zmm.rabbitmq.util.JodaTimeUtil;
import com.zmm.rabbitmq.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Name UserServiceImpl
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Resource
	private MsgLogMapper msgLogMapper;

	@Override
	public List<User> getAll() {
		return userMapper.selectAll();
	}

	@Override
	public User getOne(Integer id) {
		return userMapper.selectOne(id);
	}

	@Override
	public void add(User user) {
		userMapper.insert(user);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public void delete(Integer id) {
		userMapper.delete(id);
	}

	@Override
	public User getByUsernameAndPassword(String username, String password) {
		return userMapper.selectByUsernameAndPassword(username, password);
	}

	@Override
	public ServerResponse login(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_EMPTY.getMsg());
		}

		User user = userMapper.selectByUsernameAndPassword(username, password);
		if (null == user) {
			return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_WRONG.getMsg());
		}

		saveAndSendMsg(user);

		return ServerResponse.success();
	}

	/**
	 * 保存并发送消息
	 * @param user
	 */
	private void saveAndSendMsg(User user) {
		String msgId = RandomUtil.UUID32();

		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getId());
		loginLog.setType(Constant.LogType.LOGIN);
		Date date = new Date();
		loginLog.setDescription(user.getUsername() + "在" + JodaTimeUtil.dateToStr(date) + "登录系统");
		loginLog.setCreateTime(date);
		loginLog.setUpdateTime(date);
		loginLog.setMsgId(msgId);

		CorrelationData correlationData = new CorrelationData(msgId);
		rabbitTemplate.convertAndSend(RabbitConfig.LOGIN_LOG_EXCHANGE_NAME, RabbitConfig.LOGIN_LOG_ROUTING_KEY_NAME, MessageHelper.objToMsg(loginLog), correlationData);

		MsgLog msgLog = new MsgLog(msgId, loginLog, RabbitConfig.LOGIN_LOG_EXCHANGE_NAME, RabbitConfig.LOGIN_LOG_ROUTING_KEY_NAME);
		msgLogMapper.insert(msgLog);
	}
}
