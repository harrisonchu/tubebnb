package com.tubemogul.platform.tubebnb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by kamel.dabwan on 8/6/15.
 */


@JsonPropertyOrder({"@uri", "reservation_id"})
public class ReservationItem {


    @JsonProperty("@uri")
    private String uri;

    @JsonProperty("reservation_id")
    private String reservationId;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
}
