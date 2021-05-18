package com.n4pst3rcod.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.oidc.authentication.OidcIdTokenValidator;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.util.*;

@Configuration
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {




        http
                .authorizeRequests()
                .anyRequest().authenticated()
//                .antMatchers("/").permitAll()

//                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessHandler(new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository));

//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


//    AuthenticationManagerResolver<HttpServletRequest> customAuthenticationManager() {
//        LinkedHashMap<RequestMatcher, AuthenticationManager> authenticationManagers = new LinkedHashMap<>();
//
//        // USE JWT tokens (locally validated) to validate HEAD, GET, and OPTIONS requests
//        List<String> readMethod = Arrays.asList("HEAD", "GET", "OPTIONS");
//        RequestMatcher readMethodRequestMatcher = request -> readMethod.contains(request.getMethod());
//        authenticationManagers.put(readMethodRequestMatcher, jwt());
//
//        // all other requests will use opaque tokens (remotely validated)
//        RequestMatchingAuthenticationManagerResolver authenticationManagerResolver
//                = new RequestMatchingAuthenticationManagerResolver(authenticationManagers);
//
//        // Use opaque tokens (remotely validated) for all other requests
//        authenticationManagerResolver.setDefaultAuthenticationManager(opaque());
//        return authenticationManagerResolver;
//    }
//
//    // Mimic the default configuration for JWT validation.
//    AuthenticationManager jwt() {
//        // this is the keys endpoint for okta
//
//        String jwkSetUri = "https://login.microsoftonline.com/76e59a7c-e3df-4992-ad85-2ece50ef0ff8/v2.0" + "/v1/keys";
//
//        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
//
//        // okta recommends validating the `iss` and `aud` claims
//        // see: https://developer.okta.com/docs/guides/validate-access-tokens/java/overview/
//        List<OAuth2TokenValidator<Jwt>> validators = new ArrayList<>();
//        validators.add(new JwtTimestampValidator());
//        // Add validation of the issuer claim
//        validators.add(new JwtIssuerValidator("https://login.microsoftonline.com/76e59a7c-e3df-4992-ad85-2ece50ef0ff8/v2.0"));
//        validators.add(token -> {
//            Set<String> expectedAudience = new HashSet<>();
//            // Add validation of the audience claim
//            expectedAudience.add("api://default");
//            // For new Okta orgs, the default audience is `api://default`,
//            // if you have changed this from the default update this value
//            return !Collections.disjoint(token.getAudience(), expectedAudience)
//                    ? OAuth2TokenValidatorResult.success()
//                    : OAuth2TokenValidatorResult.failure(new OAuth2Error(
//                    OAuth2ErrorCodes.INVALID_REQUEST,
//                    "This aud claim is not equal to the configured audience",
//                    "https://tools.ietf.org/html/rfc6750#section-3.1"));
//        });
//        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(validators);
//        jwtDecoder.setJwtValidator(validator);
//
//        JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
//        authenticationProvider.setJwtAuthenticationConverter(new JwtBearerTokenAuthenticationConverter());
//        return authenticationProvider::authenticate;
//    }
}
