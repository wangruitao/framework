package com.auth.handler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author wangrt
 * @date 2019年1月23日
 * TODO
 * 
 */
@Configuration
@EnableAuthorizationServer
public class CustomerAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenStore jwtTokenStore;
	
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	// authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候
	// ，请设置这个属性注入一个 AuthenticationManager 对象。
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(jwtTokenStore)
			.accessTokenConverter(jwtAccessTokenConverter).userDetailsService(userService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory().withClient("wrt").secret(passwordEncoder.encode("wrt001"))
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("all", "write", "read").accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(259200);
		
	}

/*	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		//其他应用要访问认证服务器的tokenKey（就是下边jwt签名的imooc）的时候需要经过身份认证，获取到秘钥才能解析jwt
		security.tokenKeyAccess("isAuthenticated()");
	}*/
	
}
