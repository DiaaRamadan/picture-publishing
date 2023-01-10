package com.picture_publishing.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registerResourceHandler("uploaded-images", registry);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }


    private void registerResourceHandler(String dirName, ResourceHandlerRegistry registry) {

        var dirPath = Paths.get(dirName);
        var photoPath = dirPath.toFile().getAbsolutePath();
        var logicalPath = dirName.replace("..", "") + "/**";
        registry.addResourceHandler(logicalPath).addResourceLocations("file:/" + photoPath + "/");

    }

}