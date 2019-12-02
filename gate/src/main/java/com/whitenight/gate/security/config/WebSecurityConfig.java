package com.whitenight.gate.security.config;


import com.whitenight.gate.config.SwaggerConfig;
import io.micrometer.core.instrument.util.StringUtils;
import javax.annotation.Resource;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Value("${swagger.enable:false}")
    private boolean swaggerEnable;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/*","/user/**").anonymous()
            //.anyRequest().authenticated()
            //.antMatchers("/user/**").hasAuthority("/user")
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/calendar")
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        //禁用CSRF（等我单点搞好了再来解决你）
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] ignoringArray = securityProperties.getIgnoringArray();
        if (swaggerEnable && StringUtils.isNotBlank(SwaggerConfig.ACCESS_PREFIX)){
            ignoringArray = ArrayUtils.addAll(ignoringArray, SwaggerConfig.ACCESS_PREFIX);
        }
        web.ignoring().antMatchers(ignoringArray);
    }

    /**
     * 声明密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
}