package project.reddit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RedditSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.authorizeRequests().anyRequest().authenticated()
				.antMatchers("/userposts").hasRole("USER")
				.antMatchers("/**").hasRole("ADMIN");
				//.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
		http
				.antMatcher("/home")
				.formLogin().failureUrl("/")
				.defaultSuccessUrl("/home")
				.loginPage("/")
				.permitAll();
				//.and()
				//.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
		
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("daniel").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("bet").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dave").password("password").roles("USER");
    }
}
