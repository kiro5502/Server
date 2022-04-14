package com.ajou.mse.magicaduel.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.dto.HelloResponseDto;

@RestController
public class HelloController {

	@GetMapping("/")
	public String hello() {
		return "hello";
	}

	@GetMapping("/dto")
	public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
		return new HelloResponseDto(name, amount);
	}

}
