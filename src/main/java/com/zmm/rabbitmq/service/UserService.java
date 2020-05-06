package com.zmm.rabbitmq.service;

import com.zmm.rabbitmq.common.ServerResponse;
import com.zmm.rabbitmq.pojo.User;

import java.util.List;

/**
 * @Name UserService
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface UserService {

	/**
	 * 查询 所有的用户
	 * @return
	 */
	List<User> getAll();

	/**
	 * 根据 用户Id 查询 用户记录
	 * @param id
	 * @return
	 */
	User getOne(Integer id);

	/**
	 * 添加一个用户
	 * @param user
	 */
	void add(User user);

	/**
	 *  更新用户记录
	 * @param user
	 */
	void update(User user);


	/**
	 * 根据 用户Id 删除用户记录
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 根据 用户名 和 密码 登录
	 * @param username
	 * @param password
	 * @return
	 */
	User getByUsernameAndPassword(String username, String password);

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	ServerResponse login(String username, String password);
}
