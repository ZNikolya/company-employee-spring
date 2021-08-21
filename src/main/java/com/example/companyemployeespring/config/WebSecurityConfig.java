package com.example.companyemployeespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET,"/addEmployee").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/employees").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/companies").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/addCompany").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"//deleteCompany/**").hasAnyRole("ADMIN");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("poxos")
                .password(encoder().encode("poxos"))
                .roles("USER").and()
                .withUser("petros")
                .password(encoder().encode("petros"))
                .roles("ADMIN");

    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
