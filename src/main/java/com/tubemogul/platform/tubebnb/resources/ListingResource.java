package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.dao.ListingsDAO;
import com.tubemogul.platform.tubebnb.exceptions.ErrorDisplay;
import com.tubemogul.platform.tubebnb.model.Listing;
import com.tubemogul.platform.tubebnb.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingResource.class);

    @Autowired
    public ListingsDAO listingsDAO;

    @Autowired
    NotificationService notificationService;

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
    @Path("/create/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response postListing(@FormParam("email") String email,
                                @FormParam("location_id") Integer locationId,
                                @FormParam("type") String type,
                                @FormParam("name") String name,
                                @FormParam("description") String description,
                                @FormParam("image_link") String imageLink,
                                @FormParam("auth_code") String authCode) {

        try {
            if (!("business".equals(type) || "leisure".equals(type) || "both".equals(type))) {
                ErrorDisplay error = new ErrorDisplay("invalid listing type", 400);
                return Response.status(400).entity(error).build();
            }

            Listing listingResponse = listingsDAO.createListing(email, locationId, type, name, description, imageLink, authCode);

            notificationService.sendMessage(email, "AirTube created listing confirmation", "Hi! \n\n\n " +
                    "Your listing has been created.  Please use this code to edit or delete your posting " + authCode);

            return Response.ok(listingResponse).build();
        } catch (Exception e) {
            LOGGER.error("Error during create ", e);
            ErrorDisplay error = new ErrorDisplay(e.getMessage(), 400);
            return Response.status(400).entity(error).build();
        }
    }

    @POST
    @Path("/update/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response editListing(@FormParam("email") String email,
            @FormParam("location_id") Integer locationId,
            @FormParam("type") String type,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("image_link") String imageLink,
            @FormParam("auth_code") String authCode,
            @PathParam("id") int id) {

        try {
            if (!("business".equals(type) || "leisure".equals(type) || "both".equals(type))) {
                ErrorDisplay error = new ErrorDisplay("invalid listing type", 400);
                return Response.status(400).entity(error).build();
            }

            Listing listing = listingsDAO.updateListingById(id, email, locationId, type, name, description, imageLink, authCode);

            return Response.ok(listing).build();
        } catch (Exception e) {
            LOGGER.error("Error during create ", e);
            ErrorDisplay error = new ErrorDisplay(e.getMessage(), 400);
            return Response.status(400).entity(error).build();
        }
    }

    @POST
    @Path("/delete/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response deleteListing(@FormParam("listing_id") int listingId) {

        try {
            listingsDAO.deleteListingById(listingId);
            return Response.ok().build();
        } catch (Exception e) {
            ErrorDisplay error = new ErrorDisplay(e.getMessage(), 400);
            return Response.status(400).entity(error).build();
        }

    }
}
