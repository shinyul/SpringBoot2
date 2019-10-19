package com.ab.migration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ab.migration.auth.FirebaseAuthenticationProvider;
import com.ab.migration.filter.FirebaseFilter;

@Configuration
@EnableWebSecurity
public class SecurityCofing extends WebSecurityConfigurerAdapter {

	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Configuration
	protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

//	 	firebase token를 사용한 oauth2 커스텀시 사용예정 
//		@Autowired
//		private UserDetailsService userDetailsService;

		@Autowired
		private FirebaseAuthenticationProvider firebaseProvider;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
//		 	firebase token를 사용한 oauth2 커스텀시 사용예정
//			auth.userDetailsService(userDetailsService);
			auth.authenticationProvider(firebaseProvider);
		}
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		  auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
//		  auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//		  auth.inMemoryAuthentication().withUser("kans").password("{noop}kans123").roles("USER");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 	.cors().configurationSource(corsConfigurationSource()) // cross domain 허용
		 .and()
		 	//상태 변경 요청(GET,HEAD,OPTION,TRACE가 아닌 모든 요청)들을 가로채여서 CSRF 토큰을 확인받게 된다.
		 	//요청에 CSRF 토큰이 없거나 서버의 토큰과 일치하지 않는다면 요청은 CsrfException과 함께 실패하게 된다.
		 	// 애플리케이션의 모든 폼은 반드시 _csrf 필드를 전달해야한다. disable() 할 경우 확인 하지 않는다. 
		 	.csrf().disable() // Cross-Site Request Forgery
		 .addFilterBefore(tokenAuthorizationFilters(), BasicAuthenticationFilter.class)
         .authorizeRequests()
         	// cors를 해결 하기 위해아래와 같이 전체 허용은 사용하지 말아야한다. 
         	//.antMatchers(HttpMethod.OPTIONS, "/**/**").permitAll()
         	
         	// ROLE_ADMIN 경우 ROLE_ 은 생략 가능. 자동으로 추가 됨.  
         	//.antMatchers("/asis/**").hasRole("ADMIN")
         	// ROLE_ADMIN 으로 사용해야함 생략 불가. 
         	//.antMatchers("/asis/**").hasAnyAuthority("ADMIN")
         	// hasRole 과 동일하지만 여러개 의 권한 넣을 수 있다. 
         	//.antMatchers("/tobe/**").hasIpAddress("192.168.0.1") 또는 .hasIpAddress("192.168.0.1/56")
         	// 권한과 아이피를 동시에 사용할때는 access 를 사용
         	//.antMatchers("/tobe/**").access("hasAnyRole('ADMIN') and hasIpAddress('192.168.1.2')")
         	// ROLE과 상관없이 인증된 사용자는 허용 
         	//.antMatchers(HttpMethod.POST, "/auth/fire/token").authenticated()
         	//모든 접근 허용.
         	//.antMatchers(HttpMethod.POST, "/auth/fire/token").permitAll()
         	// 익명 사용자의 접근을 허용. 
         	//.antMatchers("/auth/fire/token").anonymous()
         	
         	.antMatchers("/asis/**").hasAnyRole("MANAGER")
         	.antMatchers("/tobe/**").hasAnyRole("ADMIN")
         	//접근을 허용하지 않음.
         	.antMatchers("/**").denyAll()
         	//anyRequest는 다른 모든 요청
         	.anyRequest().authenticated()
         	
         	// requiresSecure메소드는  https 로 자동으로 리다이렉트 
         	//.and().requiresChannel().antMatchers("/tobe/**").requiresSecure()
         	// requiresInsecure 소드는 hppt 로 사용 할때 사용  
         	//.and().requiresChannel().antMatchers("/tobe/**").requiresInsecure()
         	
     	.and()
     		//Http기반 인증으로 인증 허용 
         	.httpBasic();
		 // after 필터 사용시
		 // .and().addFilterAfter(userStatusFilter(), BasicAuthenticationFilter.class);
		 // 로그인폼 인증 허용
		 //.and().formLogin();
	}
	
	@Bean
	public FirebaseFilter tokenAuthorizationFilters() {
		return new FirebaseFilter();
	}
	
	
	
	@Bean
	//cross domain 이슈는 Filter를 이용할 수 있지만 security 에서 도 설정은 지원한다. 
	//com.ab.migration.filter.SimpleCorsFilter 를 사용한 파일 참조!! 삭제안함. 
    public CorsConfigurationSource corsConfigurationSource() {
        
		CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "TOKEN_ID", "X-Requested-With", "Authorization", "Content-Type", "Content-Length", "Cache-Control"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
	
}
