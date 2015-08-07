package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.dao.ReservationDAO;
import com.tubemogul.platform.tubebnb.model.Reservation;
import com.tubemogul.platform.tubebnb.response.ReservationItem;
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
@Path("/reservations")
public class ReservationResource {

    @Autowired
    private ReservationDAO reservationDAO;

    @GET
    @Path("/{reservation_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getSingleReservation(@PathParam("reservation_id") Long reservationId) {

        Reservation reservation = reservationDAO.getReservation(reservationId);

        return Response.ok(reservation).build();

    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response createReservation(@FormParam("listing_id") Long listingId,
                                      @FormParam("host_user_id") Long hostUserId,
                                      @FormParam("location_id") Long locationId,
                                      @FormParam("traveler_user_id") Long travelerUserId,
                                      @FormParam("start_time") Long startTime,
                                      @FormParam("end_time") Long endTime,
                                      @FormParam("timezone") String timezone) {

        try {
            reservationDAO.createReservation(listingId, hostUserId, locationId, travelerUserId, startTime, endTime, timezone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.ok().build();


    }
}
