package br.com.ot4.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	
	//Configuracoes de autorizacao
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers("/actuator/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.GET, "/biometrias/**").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.POST, "/biometrias/**").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .antMatchers(HttpMethod.POST, "/bloqueios/**").hasAuthority("SCOPE_meu-primeiro-escopo")
                        .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
	
}
