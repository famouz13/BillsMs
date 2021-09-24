package org.itstep.bills_ms.security;

import org.itstep.bills_ms.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFilter authFilter;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().
                antMatchers("/api/v1/bills")
                .hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/v1/bills/all").hasAnyRole("USER");

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

//        http.authorizeRequests()
//                .antMatchers("/api/v1/admin/*").hasRole("ADMIN");

    }


}