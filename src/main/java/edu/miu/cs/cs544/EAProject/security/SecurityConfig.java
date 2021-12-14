package edu.miu.cs.cs544.EAProject.security;

import edu.miu.cs.cs544.EAProject.repository.UserRepository;
import edu.miu.cs.cs544.EAProject.security.jwt.JwtTokenFilter;
import edu.miu.cs.cs544.EAProject.security.jwt.JwtTokenProvider;
import edu.miu.cs.cs544.EAProject.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This security config sets up two authentication providers (Basic-Auth and JWT filter)
 * The Basic-Auth is used to issue tokens when logging in
 * The JWT filter is used to intercept all requests other than login requests to make sure that valid JWT is provided
 */
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Configuration
    @Order(10)
    public static class basicAuthSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/login/**")
                    .csrf().disable()
                    .httpBasic()
                    .and().authorizeRequests()
                    .antMatchers("/signup/**").permitAll()
                    .anyRequest().authenticated();
        }
    }

    @RequiredArgsConstructor
    @Configuration
    @Order(20)
    public static class jwtAuthSecurityConfig extends WebSecurityConfigurerAdapter {

        private final JwtTokenProvider jwtTokenProvider;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/**")
                    .csrf().disable()
                    .apply(jwtFilterConfigurer()).and()
                    .authorizeRequests()
                    .antMatchers("/signup/**").permitAll()
                    .antMatchers("/**").authenticated()
                    .antMatchers("/admins/**").hasRole(SecurityUtils.ROLE_ADMIN)
                    .antMatchers("/students/**").hasAnyRole(SecurityUtils.ROLE_STUDENT, SecurityUtils.ROLE_ADMIN)
                    .antMatchers("/faculties/**").hasAnyRole(SecurityUtils.ROLE_FACULTY, SecurityUtils.ROLE_ADMIN);
        }

        private SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> jwtFilterConfigurer() {

            return new SecurityConfigurerAdapter<>() {

                @Override
                public void configure(HttpSecurity builder) {

                    JwtTokenFilter jwtFilter = new JwtTokenFilter(jwtTokenProvider);
                    builder.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                }
            };
        }
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElse(null);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
