package com.zmm.rabbitmq.service;

import com.zmm.rabbitmq.pojo.MsgLog;

import java.util.Date;
import java.util.List;

/**
 * @Name MsgLogService
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface MsgLogService {

	/**
	 * 改变消息的状态
	 * @param msgId
	 * @param status
	 * @return
	 */
	int updateStatus(String msgId, Integer status);

	/**
	 * 根据消息Id 查询消息记录
	 * @param msgId
	 * @return
	 */
	MsgLog selectByMsgId(String msgId);

	/**
	 * 选择超时的消息
	 * @return
	 */
	List<MsgLog> selectTimeoutMsg();

	/**
	 * 增加投递次数
	 * @param msgId
	 * @param tryTime
	 */
	void updateTryCount(String msgId, Date tryTime);
}
