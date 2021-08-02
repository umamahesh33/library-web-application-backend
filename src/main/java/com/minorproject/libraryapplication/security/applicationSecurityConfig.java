package com.minorproject.libraryapplication.security;

import com.minorproject.libraryapplication.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class applicationSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    userService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin/register").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADM")
                .antMatchers("/student/all").hasAuthority("ADM")
                .antMatchers(HttpMethod.DELETE, "/student/**").hasAuthority("ADM")
                .antMatchers(HttpMethod.GET, "/student/**").hasAuthority("STD")
                .antMatchers(HttpMethod.POST, "/student/**").permitAll()
                .antMatchers(HttpMethod.POST,"/book/**").hasAuthority("ADM")
                .antMatchers(HttpMethod.DELETE,"/book/**").hasAuthority("ADM")
                .antMatchers("/book/**").permitAll()
                .antMatchers("/author/**").permitAll()
                .antMatchers("/transaction/**").hasAnyAuthority("STD","ADM")
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
