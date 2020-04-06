package com.komsia.kom.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.komsia.kom.handler.AuthFailureHandler;
import com.komsia.kom.handler.AuthSuccessHandler;
import com.komsia.kom.service.UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserService userService;
	private AuthSuccessHandler authSuccessHandler;
	private AuthFailureHandler authFailureHandler;
	 
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 인증 무시
		web.ignoring().antMatchers("/css/**"
									, "/fonts/**"
									, "/img/**"
									, "/komsia/**"
									, "/js/**"
									, "/lib/**"
									);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/user/**").permitAll()
			.antMatchers("/user/myinfo").hasRole("MEMBER")
			.antMatchers("/admin/**").hasAnyRole("ADMIN","SYSTEM")
			.antMatchers("/activity/stock/analyst/chat/manager").hasRole("MANAGER")
			.antMatchers("/activity/stock/analyst/chat/analyst").hasRole("ANALYST")
			.antMatchers("/activity/**").authenticated()
			.and()
				.formLogin()
				.loginPage("/user/login")
				.defaultSuccessUrl("/")
				.failureHandler(authFailureHandler)
				.successHandler(authSuccessHandler)
				.usernameParameter("userId")
				.passwordParameter("password")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			.and()
				.exceptionHandling().accessDeniedPage("/user/denied")
			.and().csrf().disable();
//			.and().csrf().ignoringAntMatchers("/","/user/login","/user/logout");
	}
	
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
	
}
