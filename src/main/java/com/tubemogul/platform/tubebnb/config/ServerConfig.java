package com.tubemogul.platform.tubebnb.config;

import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

import static org.eclipse.jetty.servlets.CrossOriginFilter.*;

/**
 * Created by kevin.lee on 8/6/15.
 */
@Configuration
public class ServerConfig {

    @Autowired
    private Environment environment;

    @Value("${cors.allowedMethodsParam}")
    private String allowedMethodsParam;

    @Value("${cors.allowedOriginsParam}")
    private String allowedOriginsParam;

    @Value("${cors.allowedHeadersParam}")
    private String allowedHeadersParam;

    @Value("${cors.preflightMaxAgeParam}")
    private String preflightMaxAgeParam;

    @Bean
    public FilterRegistration.Dynamic corsFilter(){
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(ALLOWED_METHODS_PARAM, this.allowedMethodsParam);
        filter.setInitParameter(ALLOWED_ORIGINS_PARAM, this.allowedOriginsParam);
        filter.setInitParameter(ALLOWED_HEADERS_PARAM, this.allowedHeadersParam);
        filter.setInitParameter(PREFLIGHT_MAX_AGE_PARAM, this.preflightMaxAgeParam);
        return filter;
    }

}
