package com.my.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommonController {

	@GetMapping("/")
	public String gotoHome()
	{
		return "Home";
	}
	@GetMapping("/logout")
	public String logout(HttpSession httpSession,ModelMap map)
	{
		httpSession.invalidate();
		map.put("pos", "Logged out Successfully");
		return "Home";
	}
}
