package com.zmm.rabbitmq.controller;

import com.zmm.rabbitmq.common.ServerResponse;
import com.zmm.rabbitmq.pojo.User;
import com.zmm.rabbitmq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Name UserController
 * @Author 900045
 * @Created by 2020/5/6 0006
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public String getAll() {
		List<User> users = userService.getAll();
		return users.toString();
	}

	@GetMapping("{id}")
	public String getOne(@PathVariable Integer id) {
		User user = userService.getOne(id);
		if (null != user) {
			return user.toString();
		} else {
			return "not exists";
		}
	}

	@PostMapping
	public String add(User user) {
		userService.add(user);
		return "nice";
	}

	@PutMapping
	public String update(User user) {
		userService.update(user);
		return "nice";
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable Integer id) {
		userService.delete(id);
		return "nice";
	}

	@PostMapping("login")
	public ServerResponse login(String username, String password) {
		return userService.login(username, password);
	}
}
