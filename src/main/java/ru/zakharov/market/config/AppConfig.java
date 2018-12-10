package ru.zakharov.market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import ru.zakharov.market.formatters.CategoryFormatter;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
@EnableAspectJAutoProxy
@ComponentScan("ru.zakharov.market.aop")
public class AppConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        // Register resource handler for images folder
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///D:/images/");

    }

    //Formatters

    private CategoryFormatter categoryFormatter;

    @Autowired
    public void setCategoryFormatter(CategoryFormatter categoryFormatter) {
        this.categoryFormatter = categoryFormatter;
    }

    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(categoryFormatter);
    }
}
