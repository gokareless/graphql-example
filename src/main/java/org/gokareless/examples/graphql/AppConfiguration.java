package org.gokareless.examples.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

// @Configuration
public class AppConfiguration {

    // @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}
