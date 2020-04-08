package com.profectusweb.ecommerce.configuration;

import com.profectusweb.ecommerce.services.EcommerceUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore = new InMemoryTokenStore();

    @Autowired
    private EcommerceUserDetailService ecommerceUserDetailService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(ecommerceUserDetailService)
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("ecommerce")
                .secret(new BCryptPasswordEncoder(10).encode("ecommerce"))
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .refreshTokenValiditySeconds(20000)
                .accessTokenValiditySeconds(20000)
        ;
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices1 = new DefaultTokenServices();
        defaultTokenServices1.setSupportRefreshToken(true);
        defaultTokenServices1.setTokenStore(tokenStore);
        return defaultTokenServices1;
    }
}
