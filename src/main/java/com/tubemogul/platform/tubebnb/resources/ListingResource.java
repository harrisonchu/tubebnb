package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by kevin.lee on 8/6/15.
 */
@Component
@Path("/listing")
public class ListingResource {

    @GET
    @Path("/{listing_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public javax.ws.rs.core.Response getSingleListing() {

        return null;
    }

}
