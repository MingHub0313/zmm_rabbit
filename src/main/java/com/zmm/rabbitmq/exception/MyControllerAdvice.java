package com.zmm.rabbitmq.exception;

import com.zmm.rabbitmq.common.ResponseCode;
import com.zmm.rabbitmq.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Name MyControllerAdvice
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
@ControllerAdvice
@Slf4j
public class MyControllerAdvice {

	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	public ServerResponse serviceExceptionHandler(ServiceException se) {
		return ServerResponse.error(se.getMsg());
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ServerResponse exceptionHandler(Exception e) {
		log.error("Exception: ", e);
		return ServerResponse.error(ResponseCode.SERVER_ERROR.getMsg());
	}
}
