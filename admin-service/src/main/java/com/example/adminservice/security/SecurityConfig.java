package com.example.adminservice.security;

import org.springframework.context.annotation.Configuration;

/**
 * @author hyosunghan
 * @Description
 * @date 2019-07-23 23:18
 */
@Configuration
class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

//    private final String adminContextPath;
//
//    public SecurityConfig(AdminServerProperties adminServerProperties) {
//        this.adminContextPath = adminServerProperties.getContextPath();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
//        http.logout().logoutUrl("/logout");
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/login.html", "/**/**.css", "/img/**", "/third-party/**").permitAll();
//        http.authorizeRequests().antMatchers("/**").authenticated();
//        http.httpBasic();
//    }
}
