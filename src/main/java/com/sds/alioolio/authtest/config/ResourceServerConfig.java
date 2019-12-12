package com.sds.alioolio.authtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * Date: 2019-06-03
 * Developer: dongha.shin
 * Description : Resource Server Configuration
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // Resource 사용에 대한 허용 rule
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and()
            .csrf().disable().anonymous()
                .and()
            .authorizeRequests().antMatchers("/h2-console").permitAll()
            .antMatchers("/test").authenticated()
                .and()
            .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    // ResourceServerConfigurer 인터페이스에 여기서 적어도 resourceId는 설정하라고 해서..
    // 이 Resource를 식별할 수 있는 ID고 OAUTH_CLIENT_DETAILS 에서 사용하는 Resource 정보를 같이 관리한다
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("test");
    }
}
