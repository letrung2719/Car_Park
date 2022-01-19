package com.example.carpark.config;

import com.example.carpark.security.ApplicationUserPermission;
import com.example.carpark.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.example.carpark.security.ApplicationUserPermission.*;
import static com.example.carpark.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/car-park/**").hasAuthority(DELETE_PERMISSION.getPermission())
                    .antMatchers(HttpMethod.POST, "/car-park/**").hasAuthority(ADD_PERMISSION.getPermission())
                    .antMatchers(HttpMethod.PUT, "/car-park/**").hasAuthority(EDIT_PERMISSION.getPermission())
                    .antMatchers(HttpMethod.GET, "/car-park/**").hasAnyRole(ADMIN.name(), STAFF.name())
                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/car-park/home", true)
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(31))
                    .key("secured-key")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    //Authentication
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails employeeUser = User.builder()
                .username("employee")
                .password(passwordEncoder.encode("12345"))
//                .roles(EMPLOYEE.name()) //ROLE_EMPLOYEE
                .authorities(EMPLOYEE.getSimpleGrantedAuthorities())
                .build();

        UserDetails staffUser = User.builder()
                .username("staff")
                .password(passwordEncoder.encode("12345"))
//                .roles(STAFF.name()) //ROLE_ADMIN
                .authorities(STAFF.getSimpleGrantedAuthorities())
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("12345"))
//                .roles(ADMIN.name()) //ROLE_ADMIN
                .authorities(ADMIN.getSimpleGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(employeeUser, staffUser, adminUser);
    }
}
