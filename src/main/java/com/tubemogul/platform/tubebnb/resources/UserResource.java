package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.response.ReservationItem;
import com.tubemogul.platform.tubebnb.response.UserItem;
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
@Path("/users")
public class UserResource {


    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getSingleDataRateCard(@Context UriInfo uri,
                                          @PathParam("user_id") Long userId) {


        UserItem userItem = new UserItem();
        userItem.setUserId(userId);

        return Response.ok(userItem).build();

    }
}
