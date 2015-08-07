package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.dao.ListingsDAO;
import com.tubemogul.platform.tubebnb.exceptions.ErrorDisplay;
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
    @Path("/post/{email}/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response postListing(@PathParam("email") String email,
                                @FormParam("location_id") Integer locationId,
                                @FormParam("type") String type) {

        try {
            if (!("business".equals(type) || "leisure".equals(type))) {
                ErrorDisplay error = new ErrorDisplay("invalid listing type", 400);
                return Response.status(400).entity(error).build();
            }

            Listing listingResponse = listingsDAO.createListing(email, locationId, type);

            return Response.ok(listingResponse).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
