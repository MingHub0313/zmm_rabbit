package com.zmm.rabbitmq.mapper;

import com.zmm.rabbitmq.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @Name UserMapperWithAnnotation
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
public interface UserMapperWithAnnotation {


	@Select("select * from user")
	@Results({
			@Result(property = "username", column = "username", jdbcType = JdbcType.VARCHAR),
			@Result(property = "password", column = "password")
	})
	List<User> selectAll();

	@Select("select * from user where id = #{id}")
	@Results({
			@Result(property = "username", column = "username", jdbcType = JdbcType.VARCHAR),
			@Result(property = "password", column = "password")
	})
	User selectOne(Integer id);

	@Insert("insert into user(username, password) values(#{username}, #{password})")
	void insert(User user);

	@Update("update user set username=#{username}, password=#{password} where id = #{id}")
	void update(User user);

	@Delete("delete from user where id = #{id}")
	void delete(Integer id);
}
