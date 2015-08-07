package com.tubemogul.platform.tubebnb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kevin.lee on 8/6/15.
 */
public class Reservation {

    @JsonProperty("reservation_id")
    public long reservationId;

    @JsonProperty("listing_id")
    public long listingId;

    @JsonProperty("host_user_id")
    public long hostUserId;

    @JsonProperty("location_id")
    public long locationId;

    @JsonProperty("traveler_user_id")
    public long travelerUserId;

    @JsonProperty("start_time")
    public String startTime;

    @JsonProperty("end_time")
    public String endTime;

    @JsonProperty("timezone")
    public String timezone;

    public Reservation(long reservationId, long listingId, long hostUserId, long locationId, long travelerUserId, String startTime, String endTime, String timezone) {
        this.reservationId = reservationId;
        this.listingId = listingId;
        this.hostUserId = hostUserId;
        this.locationId = locationId;
        this.travelerUserId = travelerUserId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timezone = timezone;
    }


    public long getReservationId() {
        return reservationId;
    }

    public long getListingId() {
        return listingId;
    }

    public long getHostUserId() {
        return hostUserId;
    }

    public long getLocationId() {
        return locationId;
    }

    public long getTravelerUserId() {
        return travelerUserId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTimezone() {
        return timezone;
    }
}
