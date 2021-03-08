package com.gwell.crudtemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-04
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Register thymeleaf library to handle paging
     *
     * @return
     * @see <a href="https://github.com/jpenren/thymeleaf-spring-data-dialect</a>
     */
    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }

    /**
     * Set template path
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }


}
