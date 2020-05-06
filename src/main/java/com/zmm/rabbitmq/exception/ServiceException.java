package com.zmm.rabbitmq.exception;

/**
 * @Name ServiceException
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public class ServiceException extends RuntimeException {

	private String code;
	private String msg;

	public ServiceException() {
	}

	public ServiceException(String msg) {
		this.msg = msg;
	}

	public ServiceException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
