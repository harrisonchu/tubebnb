package com.tubemogul.platform.tubebnb;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.nhuray.dropwizard.spring.SpringBundle;
import com.tubemogul.devops.dropwizard.DevopsBundle;
import com.tubemogul.devops.spring.SpringSystemContext;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by kevin.lee on 8/6/15.
 */
public class TubeBnbServer extends Application<TubeBnbServerConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(TubeBnbServer.class);
    private final SpringSystemContext systemContext = new SpringSystemContext();
    private final ConfigurableApplicationContext applicationContext = applicationContext();


    @Override
    public String getName() {
        return "fees-api";
    }



    public static void main(String[] args) throws Exception {
        new TubeBnbServer().run(args);
    }

    @Override
    public void initialize(Bootstrap<TubeBnbServerConfiguration> bootstrap) {
        bootstrap.addBundle(new DevopsBundle(getSystemContext()));
        bootstrap.addBundle(new SpringBundle<TubeBnbServerConfiguration>(applicationContext, true, true, true));

    }

    @Override
    public void run(TubeBnbServerConfiguration configuration, Environment environment) throws Exception {
        logger.info("Initializing...");

//        environment.jersey().register(new TmParameterExceptionMapper());
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter("allowedOrigins", "*");
        filter.setInitParameter("allowedHeaders",
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowedMethods", "GET,PUT,POST,OPTIONS");
        filter.setInitParameter("preflightMaxAge", "5184000"); // 2 months
        filter.setInitParameter("allowCredentials", "true");
        environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }



    private ConfigurableApplicationContext applicationContext() throws BeansException {
        SpringSystemContext sctx = getSystemContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.getBeanFactory().registerSingleton("systemContext", sctx);
        context.scan(getClass().getPackage().getName());
        context.registerShutdownHook();

        return context;
    }

    public SpringSystemContext getSystemContext() {
        return systemContext;
    }
}
