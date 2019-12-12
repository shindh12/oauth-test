package com.sds.alioolio.authtest.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Date: 2019-05-31
 * Developer: dongha.shin
 * Description :
 */

@Configuration
public class OauthConfig {

    @Bean
    public TokenStore jdbcTokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }


}
