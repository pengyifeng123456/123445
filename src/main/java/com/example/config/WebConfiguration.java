package com.example.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.entity.TestBean;
import com.example.interceptor.Main02Interceptor;
import com.example.interceptor.MainInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Collections;
import java.util.List;

@ComponentScan("com.example.controller")
@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {//我不知道WebMvcConfigurer干嘛的

    //我们需要使用ThymeleafViewResolver作为视图解析器，并解析我们的HTML页面
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(@Autowired SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setOrder(1);//可以存在多个视图解析器，并且可以为他们设定解析顺序
        resolver.setCharacterEncoding("UTF-8");//编码格式是重中之重
        resolver.setTemplateEngine(springTemplateEngine); //和之前JavaWeb阶段- 一样， 需要使用模板引擎进行解析，所以这里也需要设定一下 模板引擎
        return resolver;
    }

    //配置模板解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver() {//SpringResourceTemplateResolve的父类的父类继承了ITemplateResolver接口
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setSuffix(".html"); //需要解析的后缀名称
        resolver.setPrefix("/WEB-INF/template/"); //需要解析的HTML页面 文件存放的位置
        return resolver;
    }

    //配置模板引擎Bean
    @Bean
    public SpringTemplateEngine springTemplateEngine(@Autowired ITemplateResolver resolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver); //模板解析器， 默认即可
        return engine;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();//开启默认的Servlet
    }

    @Override//静态资源模板
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/static/");//配置静态资源的访问路径
    }//"/static/**"表示只要是访问static下面的任意东西，都将在该东西路径前加上/WEB-INF/static/即可访问

    @Override/*添加拦截器*/
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new MainInterceptor())
                .addPathPatterns("/**") //添加拦截器的匹配路径，只要匹配一律拦截
                .excludePathPatterns("/home502"); //拦截器不进行拦截的路径

        registry/*第二个拦截器*/
                .addInterceptor(new Main02Interceptor())
                .addPathPatterns("/**") //添加拦截器的匹配路径，只要匹配一律拦截
                .excludePathPatterns("/home502"); //拦截器不进行拦截的路径
    }

    /*  @Override*//*json中给浏览器返回一个User对象自动把user转换成json字符串格式，这里设置了才会把对象自动转换为json字符串格式，不然不会*//*
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FastJsonHttpMessageConverter());
    }*/
   /* @Override//下面设置是只有当APPLICATION_JSON形式才会做相应的转换，其他类型交给默认的转换
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }*/


    @Bean//Scope中的single单例模式，再次请求或者再次打开浏览器都不会创建新的
    @RequestScope//如果某个地方用到了TestBean，每次请求都会创建新的
    // @SessionScope//如果某个地方用到了TestBean，每次请求不去会创建新的，只有在关闭浏览器后重新打开浏览器才会创建新的
    public TestBean testBean() {
        return new TestBean();
    }

    @Bean("multipartResolver") //文件的上传与下载，注意这里Bean的名称是固定的，必须是multipartResolver
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1024 * 1024 * 10);//最大10MB大小
        resolver.setDefaultEncoding("UTF-8"); //默认编码格式
        return resolver;

    }
}
