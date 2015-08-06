package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.dao.UserDAO;
import com.tubemogul.platform.tubebnb.exceptions.ErrorDisplay;
import com.tubemogul.platform.tubebnb.model.User;
import com.tubemogul.platform.tubebnb.response.ReservationItem;
import com.tubemogul.platform.tubebnb.response.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by kamel.dabwan on 8/6/15.
 */


@Component
@Path("/v1/tubebnb/users")
public class UserResource {

    @Autowired
    private UserDAO userDAO;


    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getSingleDataRateCard(@Context UriInfo uri,
                                          @PathParam("user_id") Long userId) {


        User user = userDAO.getUser(userId);
        if (user == null){
            ErrorDisplay err = new ErrorDisplay("Failed to create user", 400);
            return Response.status(400).entity(err).build();
        }

        return Response.ok(user).build();

    }

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response createUser(@Context UriInfo uri,
                               @FormParam("name") String name,
                               @FormParam("email") String email,
                               @FormParam("office") String office,
                               @FormParam("phone_number") String phoneNumber,
                               @FormParam("notify_on_reservation") boolean notifyOnReservation){

        boolean success = userDAO.createUser(name,email,office,phoneNumber,notifyOnReservation);
        if (success){
            return Response.ok("User was created successfully").build();
        }
        return Response.status(400).entity("Failed to create user").build();

    }

}
