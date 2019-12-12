package com.sds.alioolio.authtest.config;

import com.sds.alioolio.authtest.service.AlioClientDetailsService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Date: 2019-05-31
 * Developer: dongha.shin
 * description : auth server
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AlioClientDetailsService alioClientDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public PasswordEncoder passwordEncoder;

    // jdbc 로 client 정보 관리 alioClientDetails
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).clients(alioClientDetailsService);
    }

    // credential 검증 시 사용할 spring security의 authenticationManager를 주입
    // 토큰은 DB로 저장하도록 설정
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore());

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setClientDetailsService(alioClientDetailsService);
        endpoints.tokenServices(defaultTokenServices)
        ;
    }

    // token을 DB에서 관리함
    @Bean
    public TokenStore tokenStore() {
      return new JdbcTokenStore(dataSource);
    }
}
