package com.tubemogul.platform.tubebnb;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

/**
 * Created by kevin.lee on 8/6/15.
 */
public class TubeBnbServerConfiguration extends Configuration {

    @JsonProperty("cors.allowedMethodsParam")
    private String allowedMethodsParam;

    @JsonProperty("cors.allowedOriginsParam")
    private String allowedOriginsParam;

    @JsonProperty("cors.allowedHeadersParam")
    private String allowedHeadersParam;

    @JsonProperty("cors.preflightMaxAgeParam")
    private int preflightMaxAgeParam;



    public String getAllowedMethodsParam() {
        return allowedMethodsParam;
    }

    public void setAllowedMethodsParam(String allowedMethodsParam) {
        this.allowedMethodsParam = allowedMethodsParam;
    }

    public String getAllowedOriginsParam() {
        return allowedOriginsParam;
    }

    public void setAllowedOriginsParam(String allowedOriginsParam) {
        this.allowedOriginsParam = allowedOriginsParam;
    }

    public String getAllowedHeadersParam() {
        return allowedHeadersParam;
    }

    public void setAllowedHeadersParam(String allowedHeadersParam) {
        this.allowedHeadersParam = allowedHeadersParam;
    }

    public int getPreflightMaxAgeParam() {
        return preflightMaxAgeParam;
    }

    public void setPreflightMaxAgeParam(int preflightMaxAgeParam) {
        this.preflightMaxAgeParam = preflightMaxAgeParam;
    }

}
