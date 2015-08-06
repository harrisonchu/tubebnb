package com.tubemogul.platform.tubebnb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by kamel.dabwan on 8/6/15.
 */


@JsonPropertyOrder({"@uri", "reservation_id"})
public class ReservationItem {

    @JsonProperty("reservation_id")
    private Long reservationId;


    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
