package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.cotiinformatica.filters.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {

	// método para configurar o uso do JWT
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//definir o filter da aplicação (gerenciar a autenticação)
		//definir quais serviços da aplicação não exigem autenticação
		http.csrf().disable().addFilterAfter(new JwtAuthorizationFilter(), 
				UsernamePasswordAuthenticationFilter.class).authorizeRequests()
				//serviços que não exigem autenticação
				.antMatchers("/api/enderecos").permitAll()
				.antMatchers("/api/clientes").permitAll()
				.antMatchers("/api/produtos").permitAll()
				.antMatchers("/api/login").permitAll()
				//todo o restante da API exige autenticação!
				.anyRequest().authenticated();
	}

}
