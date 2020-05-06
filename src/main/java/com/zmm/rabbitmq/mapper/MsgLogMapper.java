package com.zmm.rabbitmq.mapper;

import com.zmm.rabbitmq.pojo.MsgLog;

import java.util.List;

/**
 * @Name MsgLogMapper
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface MsgLogMapper {

	/**
	 * 新增消息记录
	 * @param msgLog
	 * @return
	 */
	int insert(MsgLog msgLog);

	/**
	 * 更新消息状态
	 * @param msgLog
	 * @return
	 */
	int updateStatus(MsgLog msgLog);

	/**
	 * 选择消息超时
	 * @return
	 */
	List<MsgLog> selectTimeoutMsg();

	/**
	 * 选择异常消息
	 * @param msgLog
	 * @return
	 */
	int updateTryCount(MsgLog msgLog);

	/**
	 * 根据消息Id 查询记录
	 * @param msgId
	 * @return
	 */
	MsgLog selectByPrimaryKey(String msgId);
}
