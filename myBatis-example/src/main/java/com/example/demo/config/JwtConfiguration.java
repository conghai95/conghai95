package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtConfiguration {

	String signKey = "cmc";

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setAccessTokenConverter(defaultAccessTokenConverter());
		converter.setSigningKey(signKey);
		return converter;
	}

	@Bean
	public TokenEnhancerChain tokenEnhancerChain() {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		List<TokenEnhancer> tokenEnhancerList = new ArrayList<TokenEnhancer>();

		tokenEnhancerList.add(accessTokenConverter());
		tokenEnhancerChain.setTokenEnhancers(tokenEnhancerList);

		return tokenEnhancerChain;
	}

	@Bean
	public DefaultAccessTokenConverter defaultAccessTokenConverter() {
		DefaultAccessTokenConverter converter = new DefaultAccessTokenConverter();
		DefaultUserAuthenticationConverter userConverter = new DefaultUserAuthenticationConverter();

		converter.setUserTokenConverter(userConverter);
		return converter;
	}

	@Bean
	public ResourceServerTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();

		tokenServices.setTokenStore(tokenStore());
		tokenServices.setTokenEnhancer(tokenEnhancerChain());
		tokenServices.setSupportRefreshToken(true);

		return tokenServices;
	}
}
