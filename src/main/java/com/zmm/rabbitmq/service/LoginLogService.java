package com.zmm.rabbitmq.service;

import com.zmm.rabbitmq.pojo.LoginLog;

/**
 * @Name LoginLogService
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface LoginLogService {

	/**
	 * 新增 登录日志
	 * @param loginLog
	 * @return
	 */
	int insert(LoginLog loginLog);


	/**
	 * 根据消息Id 查询登录日志
	 * @param msgId
	 * @return
	 */
	LoginLog selectByMsgId(String msgId);
}
