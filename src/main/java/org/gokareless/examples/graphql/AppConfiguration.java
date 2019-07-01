package org.gokareless.examples.graphql;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public FilterRegistrationBean<AppFilter> deviceFilter() {
        FilterRegistrationBean<AppFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AppFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
