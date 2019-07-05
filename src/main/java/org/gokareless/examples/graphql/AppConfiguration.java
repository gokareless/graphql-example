package org.gokareless.examples.graphql;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public FilterRegistrationBean<AppEncodingFilter> deviceFilter() {
        FilterRegistrationBean<AppEncodingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AppDeviceFilter());
        registrationBean.setFilter(new AppEncodingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
