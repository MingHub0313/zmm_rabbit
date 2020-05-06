package com.zmm.rabbitmq.designpattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Name PayProxy
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
public class PayProxy {

	private Object target;

	public PayProxy(Object target) {
		this.target = target;
	}

	public Object getPayProxy() {
		ClassLoader classLoader = target.getClass().getClassLoader();
		Class[] interfaces = target.getClass().getInterfaces();

		Object proxy = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("start...");

				Object result = method.invoke(target, args);

				System.out.println("stop");
				return result;
			}
		});

		return proxy;
	}
}
