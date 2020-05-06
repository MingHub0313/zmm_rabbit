package com.zmm.rabbitmq.pojo;

import com.zmm.rabbitmq.common.Constant;
import com.zmm.rabbitmq.util.JodaTimeUtil;
import com.zmm.rabbitmq.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Name MsgLog
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MsgLog {

	private String msgId;
	private String msg;
	private String exchange;
	private String routingKey;
	private Integer status;
	private Integer tryCount;
	private Date nextTryTime;
	private Date createTime;
	private Date updateTime;

	public MsgLog(String msgId, Object msg, String exchange, String routingKey) {
		this.msgId = msgId;
		this.msg = JsonUtil.objToStr(msg);
		this.exchange = exchange;
		this.routingKey = routingKey;

		this.status = Constant.MsgLogStatus.DELIVERING;
		this.tryCount = 0;

		Date date = new Date();
		this.createTime = date;
		this.updateTime = date;
		this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
	}
}
