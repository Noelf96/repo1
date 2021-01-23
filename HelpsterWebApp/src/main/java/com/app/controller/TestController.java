package com.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class TestController {
	public TestController() {
		System.out.println("In ctor of Test Controller");
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping
	public List<String> test(){
		System.out.println("In test");
		return Arrays.asList("aaa","222","zxcv");
	}

}
