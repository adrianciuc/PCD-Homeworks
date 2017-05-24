package com.fii.pcd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .authenticationProvider(authenticationProvider());
//        auth.inMemoryAuthentication()
//                .withUser("prof").password("prof").roles("PROF")
//                .and().withUser("stud").password("stud").roles("STUD")
//                .and().withUser("adm").password("adm").roles("ADM");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/professor", "/professor/**")
                .access("hasRole('PROF')")
                .and()
                .authorizeRequests()
                .antMatchers("/student", "/student/**")
                .access("hasRole('STUD')")
                .and()
                .authorizeRequests()
                .antMatchers("/admin", "/admin/**")
                .access("hasRole('ADM')")
                .and()
                .authorizeRequests()
                .antMatchers("/enter")
                .access("hasRole('PROF') or hasRole('STUD') or hasRole('ADM')")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/enter")
                .usernameParameter("ssoId")
                .passwordParameter("password")
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .and()
                .csrf()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/unauthorized")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Override
    public void init(final WebSecurity web) throws Exception {
        final HttpSecurity http = getHttp();
        web.addSecurityFilterChainBuilder(http).postBuildAction(new Runnable() {
            public void run() {
                FilterSecurityInterceptor securityInterceptor = http
                        .getSharedObject(FilterSecurityInterceptor.class);
                ((AffirmativeBased)(securityInterceptor.getAccessDecisionManager())).setMessageSource(new StaticMessageSource());
                web.securityInterceptor(securityInterceptor);
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/css/**", "/fonts/**", "/js/**", "/img/**");
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        return new StaticMessageSource();
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
