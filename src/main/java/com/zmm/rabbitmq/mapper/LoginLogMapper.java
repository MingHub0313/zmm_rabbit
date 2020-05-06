package com.zmm.rabbitmq.mapper;

import com.zmm.rabbitmq.pojo.LoginLog;

/**
 * @Name LoginLogMapper
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface LoginLogMapper {

	/**
	 * 新增记录
	 * @param loginLog
	 * @return
	 */
	int insert(LoginLog loginLog);

	/**
	 * 根据消息查询登录
	 * @param msgId
	 * @return
	 */
	LoginLog selectByMsgId(String msgId);
}
