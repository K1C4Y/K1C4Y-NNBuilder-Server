package com.pz.NNServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    public void configure(HttpSecurity httpSecuruty) throws Exception{
//       httpSecuruty.csrf().disable()
//               .authorizeRequests()
//               .antMatchers("/api/auth/signup")
//               .permitAll()
//               .anyRequest()
//               .authenticated();
//   }
	@Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
    		httpSecurity.cors().and().csrf().disable()
    			.authorizeRequests().antMatchers("/**").permitAll();
    }
    
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
