package com.tubemogul.platform.tubebnb;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by kevin.lee on 8/6/15.
 */
public class TubeBnbServer extends Application<TubeBnbServerConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(TubeBnbServer.class);



    @Override
    public String getName() {
        return "fees-api";
    }



    public static void main(String[] args) throws Exception {
        new TubeBnbServer().run(args);
    }

    @Override
    public void initialize(Bootstrap<TubeBnbServerConfiguration> bootstrap) {

    }

    @Override
    public void run(TubeBnbServerConfiguration configuration, Environment environment) throws Exception {
        logger.info("Initializing...");

//        environment.jersey().register(new TmParameterExceptionMapper());
        environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
}
