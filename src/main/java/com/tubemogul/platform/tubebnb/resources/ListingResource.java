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
import java.util.List;

/**
 * Created by kamel.dabwan on 8/6/15.
 */

@Component
@Path("/v1/tubebnb/listings")
public class ListingResource {


    @Autowired
    public ListingsDAO listingsDAO;

    @GET
    @Path("/get/{email}/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getSingleDataRateCard(@Context UriInfo uri,
                                          @PathParam("email") String email) {

        Listing listing = listingsDAO.getListingByEmail(email);
        return Response.ok(listing).build();

    }


    @GET
    @Path("/get_all/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getAllListings() {
        List<Listing> listings = listingsDAO.getAllListings();
        return Response.ok(listings).build();
    }

    @POST
    @Path("/create/{email}/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response postListing(@PathParam("email") String email,
                                @FormParam("location_id") Integer locationId,
                                @FormParam("type") String type,
                                @FormParam("name") String name,
                                @FormParam("description") String description) {

        try {
            if (!("business".equals(type) || "leisure".equals(type))) {
                ErrorDisplay error = new ErrorDisplay("invalid listing type", 400);
                return Response.status(400).entity(error).build();
            }

            Listing listingResponse = listingsDAO.createListing(email, locationId, type, name, description);

            return Response.ok(listingResponse).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
