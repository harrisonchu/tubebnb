package com.tubemogul.platform.tubebnb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kamel.dabwan on 8/6/15.
 */
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
