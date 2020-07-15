package rs.ac.ni.pmf.web.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.ni.pmf.web.exception.ErrorInfo;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ErrorCode;
import rs.ac.ni.pmf.web.exception.ExceptionEnums.ResourceType;
import rs.ac.ni.pmf.web.model.entity.UserEnums.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String[] SWAGGER_WHITELIST = { "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs",
			"/webjars/**" };

	@Autowired
	private ObjectMapper objectMapper;

	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(SWAGGER_WHITELIST).permitAll()
				.antMatchers(HttpMethod.DELETE, "/services/rest/person-user/*").hasRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.GET, "/services/rest/person-user").hasRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.DELETE, "/services/rest/*/**").hasRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.GET, "/services/rest/*/**").permitAll().and().httpBasic()
				.authenticationEntryPoint((request, response, e) -> {
					final ErrorInfo errorInfo = ErrorInfo.builder().errorCode(ErrorCode.AUTHENTICATION_FAILED)
							.resourceType(ResourceType.ACCESS).message("Failed to authorize the user.").build();

					response.setContentType("application/json;charset=UTF-8");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
				}).and().exceptionHandling().accessDeniedHandler((reqest, response, e) -> {
					final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					final String message = "User" + (auth != null ? " '" + auth.getName() + "'" : "")
							+ " attempted to access the protected URL: " + reqest.getRequestURI();
					final ErrorInfo errorInfo = ErrorInfo.builder().errorCode(ErrorCode.UNAUTHORIZED)
							.resourceType(ResourceType.ACCESS).message(message).build();

					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
				});
	}

}
