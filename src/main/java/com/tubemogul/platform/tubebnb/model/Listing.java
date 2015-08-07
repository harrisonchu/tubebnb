package com.tubemogul.platform.tubebnb.model;

/**
 * Created by harrison.chu on 8/6/15.
 */
public class Listing {

    private final int userId;
    private final int listingId;
    private final int locationId;
    private final boolean briefcase;
    private final boolean flipflops;
    private final boolean allowPets;
    private final boolean allowSmoking;

    public Listing(int userId, int listingId, int locationId, boolean briefcase, boolean flipflops, boolean allowPets,
            boolean allowSmoking) {
        this.userId = userId;
        this.listingId = listingId;
        this.locationId = locationId;
        this.briefcase = briefcase;
        this.flipflops = flipflops;
        this.allowPets = allowPets;
        this.allowSmoking = allowSmoking;
    }

    public int getUserId() {
        return userId;
    }

    public int getListingId() {
        return listingId;
    }

    public int getLocationId() {
        return locationId;
    }

    public boolean isBriefcase() {
        return briefcase;
    }

    public boolean isFlipflops() {
        return flipflops;
    }

    public boolean isAllowPets() {
        return allowPets;
    }

    public boolean isAllowSmoking() {
        return allowSmoking;
    }
}
