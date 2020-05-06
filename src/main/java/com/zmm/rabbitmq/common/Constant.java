package com.zmm.rabbitmq.common;

/**
 * @Name Constant
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public class Constant {

	public interface Redis {

		String OK = "OK";
		/**
		 * 过期时间, 60s, 一分钟
		 */
		Integer EXPIRE_TIME_MINUTE = 60;
		/**
		 *  过期时间, 一小时
		 */
		Integer EXPIRE_TIME_HOUR = 60 * 60;
		/**
		 * 过期时间, 一天
		 */
		Integer EXPIRE_TIME_DAY = 60 * 60 * 24;

		/**
		 * 创建 token 的 前缀
		 */
		String TOKEN_PREFIX = "token:";
		String MSG_CONSUMER_PREFIX = "consumer:";
		String ACCESS_LIMIT_PREFIX = "accessLimit:";

	}

	public interface LogType {
		/**
		 * 登录
		 */
		Integer LOGIN = 1;

		/**
		 * 登出
		 */
		Integer LOGOUT = 2;
	}

	public interface MsgLogStatus {
		/**
		 * 消息投递中
		 */
		Integer DELIVERING = 0;
		/**
		 * 投递成功
		 */
		Integer DELIVER_SUCCESS = 1;
		/**
		 * 投递失败
		 */
		Integer DELIVER_FAIL = 2;
		/**
		 * 已消费
		 */
		Integer CONSUMED_SUCCESS = 3;
	}

}
