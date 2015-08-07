package com.tubemogul.platform.tubebnb.model;

/**
 * Created by harrison.chu on 8/6/15.
 */
public class Location {

    private String name;
    private int locationId;

    public Location(String name, int locationId) {
        this.name = name;
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public int getLocationId() {
        return locationId;
    }
}
