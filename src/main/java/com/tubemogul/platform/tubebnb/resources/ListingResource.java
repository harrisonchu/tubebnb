package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.dao.ListingsDAO;
import com.tubemogul.platform.tubebnb.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

/**
 * Created by kamel.dabwan on 8/6/15.
 */

@Component
@Path("/v1/tubebnb/listings")
public class ListingResource {


    @Autowired
    public ListingsDAO listingsDAO;

    @GET
    @Path("/{listing_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getSingleDataRateCard(@Context UriInfo uri,
                                          @PathParam("listing_id") int listingId) {

        Listing listingResponse = listingsDAO.getListing(listingId);
        return Response.ok(listingResponse).build();

    }


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getAllListings(@Context UriInfo uri,
                                   @QueryParam("location_id") Long locationId,
                                   @QueryParam("is_briefcase") Boolean isBriefCase,
                                   @QueryParam("is_flipflops") Boolean isFlipFlops,
                                   @QueryParam("is_allow_pets") Boolean isAllowPets,
                                   @QueryParam("is_allow_smoking") Boolean isAllowSmoking,
                                   @QueryParam("is_420") Boolean is420) {

        return null;
    }

    @POST
    @Path("/{user_id}/listings/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response postListing(@PathParam("user_id") Integer userId,
                                @FormParam("location_id") Integer locationId,
                                @FormParam("is_briefcase") Boolean isBriefCase,
                                @FormParam("is_flipflops") Boolean isFlipFlops,
                                @FormParam("is_420") Boolean is420) {

        try {
            System.out.println("LOCATION ID: " + locationId);
            Listing listingResponse = listingsDAO.createListing(userId, locationId,isBriefCase,
                    isFlipFlops,is420);

            return Response.ok(listingResponse).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
