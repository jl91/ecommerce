package com.profectusweb.ecommerce.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resource) {
        resource.resourceId("ecommerce");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and().authorizeRequests()
                .antMatchers(
                        "/users/**",
                        "/carts/**",
                        "/products/**"
                )
                .hasAnyRole("master", "customer")
                .anyRequest()
                .denyAll()
        ;
    }
}
