package com.cn.sysManager.config;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by lijm on 2018-10-10.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private Environment env;

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("外部接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping(env.getProperty("doc.api.basePath"))// imageCode 路径
                .select()
                .paths(regex("/api/.*"))
                .build()
                .apiInfo(demoApiInfo());
    }

    @Bean
    Docket innerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("内部接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping(env.getProperty("doc.api.basePath"))// imageCode 路径
                .select()
                .paths(regex("/inner/.*"))//
                .build()
                .apiInfo(demoApiInfo());
    }

 /*   @Bean
    Docket updateV1_0() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V1.0更新接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping(env.getProperty("doc.api.basePath"))
                .select()
                .paths(userPathsRes()).build()
                .apiInfo(demoApiInfo());
    }

    //最新更新的接口
    private Predicate<String> userPathsRes(){
        return or(
                regex("/inner/notice/getAll"),
                regex("/api/opUser/getBasic")
        );
    }*/


    public ApiInfo demoApiInfo(){
       return  new ApiInfo("前端接口",
                "web android ios 调用接口",//小标题
                env.getProperty("doc.api.version"),
                env.getProperty("doc.api.termsOfServiceUrl"),
                env.getProperty("doc.api.contact"),
                env.getProperty("doc.api.license"),
                env.getProperty("doc.api.licenseUrl")
        );
    }

}
