package com.tubemogul.platform.tubebnb.config;

/**
 * Created by kamel.dabwan on 8/6/15.
 */

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import java.net.URISyntaxException;

@Configuration
@ComponentScan(basePackages = "com.tubemogul.platform.tubebnb")
public class HerokuDataSource {


    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HerokuDataSource.class);
    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {

        String username = "pmkvbjtjamaeld";
        String password = "PRL2DbH19B2jQc3CTQx20B_1uc";
        String dbUrl = "jdbc:postgresql://" + "ec2-54-204-0-120.compute-1.amazonaws.com" + ':' + "5432" + "/do063s1g5pfj3?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";


        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
