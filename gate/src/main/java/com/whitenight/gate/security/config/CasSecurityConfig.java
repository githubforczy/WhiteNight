package com.whitenight.gate.security.config;

import javax.annotation.Resource;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class CasSecurityConfig extends WebSecurityConfig {
    @Resource
    private CasProperties casProperties;

    @Resource
    private AuthenticationUserDetailsService<CasAssertionAuthenticationToken> casAuthenticationUserDetailsService;

    @Resource
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 设置cas认证provider
        auth.authenticationProvider(casAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        if (casProperties.isEnable()){
            http.authorizeRequests()
                .and()
                .addFilterBefore(casAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(singleSignOutFilter(),CasAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(casAuthenticationEntryPoint());
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        if (casProperties.isEnable()) {
            auth.authenticationProvider(casAuthenticationProvider());
        }
    }
    /**
     * cas认证入口
     */
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casProperties.getLoginUrl());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    /**
     * cas校验器
     */
    @Bean
    public Cas20ServiceTicketValidator ticketValidator() {
        return new Cas20ServiceTicketValidator(casProperties.getServerUrl());
    }

    /**
     * cas认证Provider
     */
    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {

        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(ticketValidator());
        // 设置UserDetailsService
        provider.setAuthenticationUserDetailsService(casAuthenticationUserDetailsService);
        provider.setKey("CAS_PROVIDER_LOCALHOST_8123");
        return provider;
    }



    /**
     * 单点登出过滤器
     */
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casProperties.getServerUrl());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    /**
     * 配置单点项目信息
     */
    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties sp = new ServiceProperties();
        // 单点登录成功回调地址
        sp.setService(casProperties.getClientCasUrl());
        return sp;
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception{
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(serviceProperties());

        filter.setAuthenticationManager(authenticationManager());
        // 可以手动设置认证成功处理器，和认证失败处理器
        // filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        // filter.setAuthenticationSuccessHandler(simpleUrlAuthenticationSuccessHandler());
        // 设置客户端cas登录url，默认值是 /login/cas
        //filter.setFilterProcessesUrl("/login/cas")
        return filter;
    }
}
