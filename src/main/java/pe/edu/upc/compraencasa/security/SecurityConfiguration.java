package pe.edu.upc.compraencasa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pe.edu.upc.compraencasa.security.LoggingAccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
	private LoggingAccessDeniedHandler loggingAccessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider() );
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http
			.authorizeRequests()
				.antMatchers("/compraencasa/index.html").permitAll()
				.antMatchers("/compraencasa/productos").hasRole("SELLER")
				.antMatchers("/compraencasa/vendedor").hasAnyRole("SELLER" , "ADMIN")
				.antMatchers("/compraencasa/cliente").hasAnyRole("BUYER", "ADMIN")
				.antMatchers("/compraencasa/vendedor/registrar").hasAuthority("ACCESS_ADDUSERVENDEDOR")
				.antMatchers("/compraencasa/vendedor/edit/**").hasAuthority("ACCESS_EDITVENDEDOR")
				.antMatchers("/compraencasa/vendedor/del/**").hasAuthority("ACCESS_DELVENDEDOR")
				.antMatchers("/compraencasa/cliente/registrar").hasAuthority("ACCESS_ADDUSERCOMPRADOR")
				.antMatchers("/compraencasa/cliente/edit/**").hasAuthority("ACCESS_EDITCOMORADOR")
				.antMatchers("/compraencasa/cliente/del/**").hasAuthority("ACCESS_DELCOMPRADOR")
			.and()
			.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/compraencasa/login")
				.usernameParameter("inputUsername")
				.passwordParameter("inputPassword")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/compraencasa")
			.and()
			.rememberMe()
				.tokenValiditySeconds(2592000)
				.key("Cl4v3.")
				.rememberMeParameter("checkRememberMe")
				.userDetailsService(usuarioDetailsService)
			.and()
				.exceptionHandling()
				.accessDeniedHandler(loggingAccessDeniedHandler);
				
					
				
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.usuarioDetailsService);
		return daoAuthenticationProvider;
	}
	
	
}
