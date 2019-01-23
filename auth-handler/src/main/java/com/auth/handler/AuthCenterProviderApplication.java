package com.auth.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 
 * @author wangrt
 * @date 2019年1月23日
 * TODO
 *
 */

@SpringBootApplication
public class AuthCenterProviderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AuthCenterProviderApplication.class, args);
	}
}
