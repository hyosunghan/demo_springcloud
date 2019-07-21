package com.example.uaaservice.config;


import com.example.uaaservice.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by hyosunghan on 2019/7/12.
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //CSRF:因为不再依赖于Cookie，所以你就不需要考虑对CSRF（跨站请求伪造）的防范。
        http
            .csrf().disable()
                .exceptionHandling()
                // test
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                // test
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                    }
//                })
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
            .and()
                .httpBasic();
    }

    @Autowired
    UserServiceDetail userServiceDetail;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetail)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
