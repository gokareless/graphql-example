package org.gokareless.examples.graphql;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import graphql.servlet.ObjectMapperConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.ArrayList;

@Configuration
public class AppConfiguration {

    @Autowired
    EncodeStringSerializer personSerializer;

    @Bean
    public FilterRegistrationBean<AppEncodingFilter> deviceFilter() {
        FilterRegistrationBean<AppEncodingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AppDeviceFilter());
        registrationBean.setFilter(new AppEncodingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public ObjectMapperConfigurer objectMapperConfigurer() {
        ArrayList<Module> modules = new ArrayList<>();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Person.class, personSerializer);
        modules.add(simpleModule);

        return (ObjectMapper mapper) ->mapper.registerModule(simpleModule);
    }

}
