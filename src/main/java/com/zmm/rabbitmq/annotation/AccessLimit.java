package com.zmm.rabbitmq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要保证 接口防刷限流 的Controller的方法上使用此注解
 * @Name AccessLimit
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

	/**
	 * 最大访问次数
	 * @return
	 */
	int maxCount();

	/**
	 *  固定时间 单位: s
	 * @return
	 */
	int seconds();
}
