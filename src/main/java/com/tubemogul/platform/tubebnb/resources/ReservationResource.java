package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.response.ReservationItem;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import io.dropwizard.auth.Auth;
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
@Path("/reservations")
public class ReservationResource {

    @GET
    @Path("/{reservation_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getSingleDataRateCard(@Context UriInfo uri,
                                          @PathParam("reservation_id") String reservationId) {

        ReservationItem reservationItem = new ReservationItem();
        reservationItem.setReservationId(reservationId);

        return Response.ok(reservationItem).build();

    }

}
