package com.zmm.rabbitmq.designpattern.staticproxy;

import java.math.BigDecimal;

/**
 * @Name Test
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
public class Test {

	public static void main(String[] args) {
		PayService payService = new PayServiceImpl();
		PayProxy payProxy = new PayProxy(payService);
		payProxy.pay("马云", BigDecimal.TEN);
	}
}
