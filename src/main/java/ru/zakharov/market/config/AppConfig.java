package ru.zakharov.market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import ru.zakharov.market.formatters.CategoryFormatter;

@Configuration
public class AppConfig extends WebMvcAutoConfiguration {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
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
