package com.gabrielrossilopes.appmalote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MaloteController {

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		return "index";
	}
}
