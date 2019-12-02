package com.whitenight.gate.security.config;

import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class SecurityProperties {

    /**
     * 匿名访问的url
     */
    @Value("${security.anonymous}")
    private String anonymous;

    /**
     * 忽略的资源,直接跳过spring security权限校验,一般是用做静态资源
     */
    @Value("${security.ignoring}")
    private String ignoring;

    /**
     * 登陆页
     */
    @Value("${security.loginPage")
    private String loginPage;

    /**
     * 登陆请求路径
     */
    @Value("${security.loginProcessingUrl}")
    private String loginProcessingUrl;

    public String[] getAnonymousArray(){
        return StringUtils.isBlank(anonymous) ? ArrayUtils.EMPTY_STRING_ARRAY : anonymous.split(",");
    }

    public String[] getIgnoringArray(){
        return StringUtils.isNotBlank(ignoring)? ArrayUtils.EMPTY_STRING_ARRAY : ignoring.split(",");


    }
}
