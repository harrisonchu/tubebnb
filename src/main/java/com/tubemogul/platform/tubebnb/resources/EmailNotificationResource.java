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

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by harrison.chu on 8/7/15.
 */
@Component
@Path("/v1/tubebnb/email")
public class EmailNotificationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationResource.class);

    @Autowired
    NotificationService notificationService;

    @Autowired
    ListingsDAO listingDAO;

    @POST
    @Path("/create/")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable = { SerializationFeature.INDENT_OUTPUT })
    public Response postListing(@FormParam("listing_id") int listingId,
            @FormParam("seeker_email") String travelerEmail,
            @FormParam("description") String description) {

        try {
            Listing listing = getListingById(listingId);
            if (listing == null) {
                ErrorDisplay error = new ErrorDisplay("No listing found!", 400);
                return Response.status(400).entity(error).build();
            }

            String email = listing.getEmail();
            if (email == null) {
                ErrorDisplay error = new ErrorDisplay("No email found!", 400);
                return Response.status(400).entity(error).build();
            }

            notificationService.sendMessage(email, "You have a new request to stay at your sweet pad from AirTube!", "From user " +
                    travelerEmail + " \n\n\n" + description);

            notificationService.sendMessage(travelerEmail, "Your request to stay at a moguler's sweet pad", "Below is a copy "
                    + "of your original message \n\n\n " + description);
            return Response.ok().build();
        } catch (Exception e) {
            ErrorDisplay error = new ErrorDisplay(e.getMessage(), 400);
            return Response.status(400).entity(error).build();
        }

    }

    private Listing getListingById(int listingId) {

        List<Listing> listings = listingDAO.getAllListings();
        System.out.println("Listings.size() = " + listings.size());
        for (Listing listing : listings) {
            System.out.println("Looking at listing.getListingId= " + listing.getListingId() + " trying " +listingId);
            if (listing.getListingId() == listingId) {
                return listing;
            }
        }

        return null;
    }

    public void setListingDAO(ListingsDAO listingDAO) {
        this.listingDAO = listingDAO;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
