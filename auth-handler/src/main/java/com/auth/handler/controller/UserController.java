package com.auth.handler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/getuser")
	public String getUser(Authentication auth) {
	
		
		StringBuilder sb = new StringBuilder();
		String userName = auth.getName();
		sb.append("用户姓名：" + userName);
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authList = (List<GrantedAuthority>) auth.getAuthorities();
		for(GrantedAuthority authSin : authList) {
			sb.append(", 角色：" + authSin.getAuthority());
		}
		return sb.toString() ;
	}
	
	@GetMapping("/queryuser")
	public String queryUser() {
		StringBuilder sb = new StringBuilder();
		
		SecurityContext securityContext = SecurityContextHolder.getContext();   
        if (securityContext != null) {   
            Authentication auth = securityContext.getAuthentication();   
            if (auth != null) {
            	sb.append("用户姓名：" + auth.getPrincipal().toString());
        		@SuppressWarnings("unchecked")
        		List<GrantedAuthority> authList = (List<GrantedAuthority>) auth.getAuthorities();
        		for(GrantedAuthority authSin : authList) {
        			sb.append(", 角色：" + authSin.getAuthority());
        		}
            } 
        }
        
        return sb.toString() ;
	}
}
