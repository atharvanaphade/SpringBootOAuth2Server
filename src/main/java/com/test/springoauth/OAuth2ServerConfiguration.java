package com.test.springoauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * The AuthorizationServerConfigurerAdapter is the resource server for the OAuth tokens.
     * Where the tokens are stored and generated, and have some default endpoints which are denied by the denyAll() method,
     * and after authentication we have allowed specific endpoints by permitAll().
     *
     * Here ClientDetailsServiceConfigurer is a class user to store the client details in an in-memory database,
     * this done for testing purposes, it is recommended to use a proper JDBC Database backed for this.
     *
     * The configuration done in this env ( More can be added ) is by the following attributes : -
     *  1. Client : The clientId registered with thse client making the requests.
     *  2. Secret : The clientSecret key stored in the application.properties file.
     *  3. Scope : Scope of the application to the client, i.e what endpoints are accessible to the client.
     *  4. authorizedGrantTypes : Grant types for the client, by default it is empty.
     *  5. authorities : Authorities granted to the user.
     *  6. redirectUris : Where to redirect the user to, this takes absolute urls as parameters.
     *  7. tokenValidity : For how long is the token valid.
     *
     */

    @Value("${user.oauth.clientId}")
    private String clientId;

    @Value("${user.oauth.clientSecret}")
    private String clientSecret;

    @Value("${user.oauth.redirectUris}")
    private String redirectURLs;

    @Value("${user.oauth.accessTokenValidity}")
    private int accessTokenValidity;

    @Value("${user.oauth.refreshTokenValidity}")
    private int refreshTokenValidity;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("user_info")
                .authorities("READ_ONLY_CLIENT")
                .redirectUris(redirectURLs)
                .accessTokenValiditySeconds(accessTokenValidity)
                .refreshTokenValiditySeconds(refreshTokenValidity);
    }
}
