package com.zmm.rabbitmq.service.impl;

import com.zmm.rabbitmq.mapper.MsgLogMapper;
import com.zmm.rabbitmq.pojo.MsgLog;
import com.zmm.rabbitmq.service.MsgLogService;
import com.zmm.rabbitmq.util.JodaTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Name MsgLogServiceImpl
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@Service
public class MsgLogServiceImpl implements MsgLogService {

	@Resource
	private MsgLogMapper msgLogMapper;

	@Override
	public int updateStatus(String msgId, Integer status) {
		MsgLog msgLog = new MsgLog();
		msgLog.setMsgId(msgId);
		msgLog.setStatus(status);
		msgLog.setUpdateTime(new Date());
		return msgLogMapper.updateStatus(msgLog);
	}

	@Override
	public MsgLog selectByMsgId(String msgId) {
		return msgLogMapper.selectByPrimaryKey(msgId);
	}

	@Override
	public List<MsgLog> selectTimeoutMsg() {
		return msgLogMapper.selectTimeoutMsg();
	}

	@Override
	public void updateTryCount(String msgId, Date tryTime) {
		Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);

		MsgLog msgLog = new MsgLog();
		msgLog.setMsgId(msgId);
		msgLog.setNextTryTime(nextTryTime);

		msgLogMapper.updateTryCount(msgLog);
	}
}
