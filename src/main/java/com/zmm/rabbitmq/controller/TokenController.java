package com.zmm.rabbitmq.controller;

import com.zmm.rabbitmq.common.ServerResponse;
import com.zmm.rabbitmq.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name TokenController
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@GetMapping
	public ServerResponse token() {
		return tokenService.createToken();
	}
}
