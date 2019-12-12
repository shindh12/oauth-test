package com.sds.alioolio.authtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Date: 2019-05-31
 * Developer: dongha.shin
 * Description : Security Configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    // 얘는 Resource Server에서 해줄거라 이제 필요는 없음
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().disable()
               .and()
            .csrf().disable().anonymous()
                .and()
            .authorizeRequests().antMatchers("/oauth/**").permitAll()
                                .antMatchers("/h2-console").permitAll()
            .anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
