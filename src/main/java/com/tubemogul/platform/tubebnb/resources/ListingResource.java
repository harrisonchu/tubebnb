package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.response.ListingItem;
import com.tubemogul.platform.tubebnb.response.ReservationItem;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by kamel.dabwan on 8/6/15.
 */

@Component
@Path("/listings")
public class ListingResource {

    @GET
    @Path("/{listing_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getSingleDataRateCard(@Context UriInfo uri,
                                          @PathParam("listing_id") Long listingId) {


        ListingItem listingItem = new ListingItem();
        listingItem.setListingId(listingId);
        return Response.ok(listingItem).build();

    }
}
