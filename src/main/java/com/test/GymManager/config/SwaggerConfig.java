package com.test.GymManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * Swagger的配置类,用来配置Swagger的一些规范
 */
@Configuration //这个注解与@Controller相似,让当前的类交给spring容器管理
public class SwaggerConfig {
    //创建一个对象Docket,用来配置参数
    @Bean  //这个注解表述,让spring容器来调用docket()方法
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2) //指定版本
                .apiInfo(apiInfo())//设置Swagger文档的元数据
                .enable(true)  //默认是true表示启动swagger文档
                .select() //通过Docket中的select()方法扫描指定包下的controller结构
                .apis(basePackage("com.test.GymManager.controller"))
                .paths(PathSelectors.any())  //包含所有的路径
                .build();
    }
    Contact contact = new Contact("软通动力","https://www.isoftstone.com/","contact@isoftstone.com");
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("一个GYM管理系统")
                .description("一个并不完善的GYM管理系统程序")
                .contact(contact)   //联系系统的开发者
                .version("1.0")
                .build();
    }
}

