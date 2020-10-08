package com.mola.auth.config;

import com.mola.auth.filter.MolaAuthFilter;
import com.mola.auth.filter.MolaLogoutFilter;
import com.mola.auth.filter.CaptchaFilter;
import com.mola.auth.handler.MolaAuthFailureHandler;
import com.mola.auth.handler.MolaAuthSuccessHandler;
import com.mola.auth.handler.MolaLogoutSuccessHandler;
import com.mola.auth.point.MolaAuthEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * 权限配置
 *
 * @author hatim
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URL = "/login";

    private static final String[] MATCHER_URLS = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui/index.html",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/actuator/**",
            // TODO 先放开拦截
            "/**"
    };

    private final MolaAuthEntryPoint molaAuthEntryPoint;
    private final MolaLogoutSuccessHandler molaLogoutSuccessHandler;
    private final MolaAuthSuccessHandler molaAuthSuccessHandler;
    private final MolaAuthFailureHandler molaAuthFailureHandler;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("loginUserServiceImpl") UserDetailsService userDetailsService, MolaAuthSuccessHandler molaAuthSuccessHandler, MolaAuthFailureHandler molaAuthFailureHandler, MolaLogoutSuccessHandler molaLogoutSuccessHandler, MolaAuthEntryPoint molaAuthEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.molaAuthSuccessHandler = molaAuthSuccessHandler;
        this.molaAuthFailureHandler = molaAuthFailureHandler;
        this.molaLogoutSuccessHandler = molaLogoutSuccessHandler;
        this.molaAuthEntryPoint = molaAuthEntryPoint;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(molaAuthEntryPoint);
        http.authorizeRequests().antMatchers(MATCHER_URLS).permitAll();
        http.addFilterBefore(new CaptchaFilter(LOGIN_URL), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(customLogoutFilter(), LogoutFilter.class);
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    MolaAuthFilter customAuthenticationFilter() throws Exception {
        // 自定义Spring security 登陆
        MolaAuthFilter filter = new MolaAuthFilter();
        filter.setAuthenticationSuccessHandler(molaAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(molaAuthFailureHandler);
        filter.setFilterProcessesUrl(LOGIN_URL);
        // 重用WebSecurityConfigurerAdapter配置的AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    MolaLogoutFilter customLogoutFilter() {
        MolaLogoutFilter molaLogoutFilter = new MolaLogoutFilter(molaLogoutSuccessHandler, securityContextLogoutHandler());
        return molaLogoutFilter;
    }

    @Bean
    SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

}
