package com.vuongnh.myweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)	//	Cung cấp UserDetailService cho Spring security
		.passwordEncoder(passwordEncoder());	// Cung cấp passwordEncoder để mãi hóa mật khẩu
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/home").hasRole("MEMBER")
				.and()
			.rememberMe()
				//.rememberMeParameter("remember-me")
				.key("uniqueAndSecret")
				.tokenValiditySeconds(300)
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/?sucessfulLogin")
				.failureUrl("/login?error")
				.and()
			.logout()
				//.deleteCookies("remember-me")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403");
	}
}
