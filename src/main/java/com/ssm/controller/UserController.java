package com.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssm.dao.UserMapper;
import com.ssm.entity.User;

@Controller  
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	// /user/test?id=1
	@RequestMapping(value = "/user/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		System.out.println("userId ===:" + userId);
		User user = null;
		if (userId == 1) {
			user = new User();
			user.setAge(11);
			user.setId(1);
			user.setPassword("123");
			user.setName("javen");
		} else if (userId == 2) {
			// 根据id查询用户信息
			System.out.println("error info :" + userId);
			user = userMapper.getUserById(userId);
		}
		System.out.println(user);

		model.addAttribute("user", user);
		return "welcome";
	}
}


