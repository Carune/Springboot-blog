package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.repository.UserRepository;

@Configuration // 빈등록 (IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig{
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인시 password를 가로채기 시도
	// 해당 password가 어떤것으로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬와 비교 가능
	
	
	 //WebSecurityConfigurerAdapter 가 deprecated 되면서 authentication manager를 빈으로 등록하는 것으로 바뀜
	  @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
	            throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		  .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두기)
		  .authorizeHttpRequests()
		    .antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		  .and()
		    .formLogin()
		    .loginPage("/auth/loginForm")
		    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서 대신 로그인.
		    .defaultSuccessUrl("/"); // 정상일시 이동
		    
		return http.build();
	}
}
