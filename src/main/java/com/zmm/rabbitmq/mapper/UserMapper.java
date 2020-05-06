package com.zmm.rabbitmq.mapper;

import com.zmm.rabbitmq.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Name UserMapper
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface UserMapper {

	/**
	 * 选择全部用户
	 * @return
	 */
	List<User> selectAll();

	/**
	 * 根据 ID 选择一个用户
	 * @param id
	 * @return
	 */
	User selectOne(Integer id);

	/**
	 * 新增一条记录
	 * @param user
	 */
	int insert(User user);

	/**
	 * 更新一条记录
	 * @param user
	 * @return
	 */
	int update(User user);

	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 根据 用户名 和 密码 登录
	 * @param username
	 * @param password
	 * @return
	 */
	User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
