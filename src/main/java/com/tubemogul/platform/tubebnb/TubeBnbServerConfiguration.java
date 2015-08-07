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

    @JsonProperty("user.dao.create.table.statement")
    private String userTableCreateString;

    @JsonProperty("user.dao.get.user.statement")
    private String userTableGetUserString;

    @JsonProperty("user.dao.create.user.statement")
    private String userTableCreateUserString;

    @JsonProperty("user.dao.get.user.by.email")
    private String userTableGetUserByEmail;

    @JsonProperty("listings.dao.create.table.statement")
    private String listingTableCreateString;

    @JsonProperty("listings.dao.get.listings.statement")
    private String listingTableGetString;

    @JsonProperty("listings.dao.create.listings.statement")
    private String listingTableCreateListingString;


    public String getUserTableGetUserByEmail() {
        return userTableGetUserByEmail;
    }

    public void setUserTableGetUserByEmail(String userTableGetUserByEmail) {
        this.userTableGetUserByEmail = userTableGetUserByEmail;
    }

    public String getUserTableCreateUserString() {
        return userTableCreateUserString;
    }

    public void setUserTableCreateUserString(String userTableCreateUserString) {
        this.userTableCreateUserString = userTableCreateUserString;
    }

    public String getUserTableGetUserString() {
        return userTableGetUserString;
    }

    public void setUserTableGetUserString(String userTableGetUserString) {
        this.userTableGetUserString = userTableGetUserString;
    }

    public String getUserTableCreateString() {
        return userTableCreateString;
    }

    public void setUserTableCreateString(String userTableCreateString) {
        this.userTableCreateString = userTableCreateString;
    }

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

    public String getListingTableCreateString() {
        return listingTableCreateString;
    }

    public void setListingTableCreateString(String listingTableCreateString) {
        this.listingTableCreateString = listingTableCreateString;
    }

    public String getListingTableGetString() {
        return listingTableGetString;
    }

    public void setListingTableGetString(String listingTableGetString) {
        this.listingTableGetString = listingTableGetString;
    }

    public String getListingTableCreateListingString() {
        return listingTableCreateListingString;
    }

    public void setListingTableCreateListingString(String listingTableCreateListingString) {
        this.listingTableCreateListingString = listingTableCreateListingString;
    }
}
