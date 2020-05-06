package com.zmm.rabbitmq.service.impl;

import com.zmm.rabbitmq.mapper.LoginLogMapper;
import com.zmm.rabbitmq.pojo.LoginLog;
import com.zmm.rabbitmq.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Name LoginLogServiceImpl
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

	@Resource
	private LoginLogMapper loginLogMapper;

	@Override
	public int insert(LoginLog loginLog) {
		return loginLogMapper.insert(loginLog);
	}

	@Override
	public LoginLog selectByMsgId(String msgId) {
		return loginLogMapper.selectByMsgId(msgId);
	}
}
