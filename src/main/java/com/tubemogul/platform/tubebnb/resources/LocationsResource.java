package com.tubemogul.platform.tubebnb.resources;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.tubemogul.platform.tubebnb.dao.LocationsDAO;
import com.tubemogul.platform.tubebnb.dao.ReservationDAO;
import com.tubemogul.platform.tubebnb.exceptions.ErrorDisplay;
import com.tubemogul.platform.tubebnb.model.Location;
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
import java.util.List;

/**
 * Created by kamel.dabwan on 8/6/15.
 */

@Component
@Path("/v1/tubebnb/locations")
public class LocationsResource {

    @Autowired
    private LocationsDAO locationsDAO;

    @GET
    @Path("/get_all/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Response getAllLocations() {

        try {
            List<Location> locations = locationsDAO.getAllLocations();
            return Response.ok(locations).build();
        } catch (Exception e) {
            ErrorDisplay error = new ErrorDisplay(e.getMessage(), 400);
            return Response.status(400).entity(error).build();
        }

    }
}
