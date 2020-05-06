package com.zmm.rabbitmq.service;

import com.zmm.rabbitmq.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @Name TokenService
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface TokenService {

	/**
	 * 创建 token
	 * @return
	 */
	ServerResponse createToken();

	/**
	 * 检查token
	 * @param request
	 */
	void checkToken(HttpServletRequest request);
}
