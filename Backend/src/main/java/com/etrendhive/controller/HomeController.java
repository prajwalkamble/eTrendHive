package com.etrendhive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etrendhive.response.ApiResponse;

@RestController
public class HomeController {

	@GetMapping("/home")
	public ResponseEntity<ApiResponse> homeController(){
		
		ApiResponse res=new ApiResponse();
		res.setMessage("Welcome To E-Commerce System");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
}