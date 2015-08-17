package com.tubemogul.platform.tubebnb.model;

/**
 * Created by harrison.chu on 8/6/15.
 */
public class Listing {

    private final int listingId;
    private final String email;
    private final int locationId;
    private final String type;
    private final String name;
    private final String description;
    private final String imageLink;
    private final String authCode;

    public Listing(String type, int locationId, String email, int listingId, String name, String description, String imageLink, String authCode) {
        this.type = type;
        this.locationId = locationId;
        this.email = email;
        this.listingId = listingId;
        this.name = name;
        this.description = description;
        this.imageLink = imageLink;
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getListingId() {
        return listingId;
    }

    public String getEmail() {
        return email;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getType() {
        return type;
    }
}
