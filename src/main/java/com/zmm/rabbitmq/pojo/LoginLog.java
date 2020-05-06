package com.zmm.rabbitmq.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Name LoginLog
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginLog {

	private static final long serialVersionUID = 9035584359424322830L;

	private Integer id;
	private Integer userId;
	private Integer type;
	private String description;
	private Date createTime;
	private Date updateTime;
	/**
	 * 消息id
	 */
	private String msgId;
}
