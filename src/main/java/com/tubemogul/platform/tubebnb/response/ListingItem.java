package com.tubemogul.platform.tubebnb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by kamel.dabwan on 8/6/15.
 */


@JsonPropertyOrder({ "listing_id"})
public class ListingItem {

    @JsonProperty("listing_id")
    private Long listingId;

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }
}
