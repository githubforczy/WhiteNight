package com.whitenight.gate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable:false}")
    private boolean swaggerEnable;
    //文档访问前缀
    public static final String ACCESS_PREFIX = "/swagger-resources/**,/swagger-ui.html**,/webjars/**,/v2/**";

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .enable(swaggerEnable)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.whitenight.gate.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("客户管理")
            .description("白夜的接口文档")
            //服务条款网址
            .termsOfServiceUrl("http://www.ityouknow.com/")
            .version("1.0")
            .contact(new Contact("白夜","www.taobao.com","516790759@qq.com"))
            .build();
    }

}
