package com.leoman.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/test")
public class UserController{

	
	@RequestMapping(value = "/user")
	public String user(String uname){
		System.out.println("newname:" + uname);
//		userService.add(user.getUname()); 
		//�����׳��쳣֪ͨ
//		userService.delete();
		return "index";
	}

}
