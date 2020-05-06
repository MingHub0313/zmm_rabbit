package com.zmm.rabbitmq.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Name User
 * @Author 900045
 * @Created by 2020/4/30 0030
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	private Integer id;
	private String username;
	private String password;

}
