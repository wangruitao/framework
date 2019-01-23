package com.auth.handler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private TokenStore tokenStore;
	
	@PostMapping("/finduser")
	public String findUser(@RequestHeader("Authorization") String auth) {
	
		User user = (User)tokenStore.readAuthentication(auth.split(" ")[1]).getPrincipal();
		return user.getUsername() + ":" + user.getPassword();
	}
}
