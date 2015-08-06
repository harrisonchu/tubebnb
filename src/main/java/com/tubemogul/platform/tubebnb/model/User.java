package com.tubemogul.platform.tubebnb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by harrison.chu on 8/6/15.
 */

@JsonPropertyOrder({ "user_id, name, email, office, phone_number"})
public class User {
    @JsonProperty("user_id")
    private final Long userId;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("office")
    private final String office;

    @JsonProperty("phone_number")
    private final String phoneNumber;

    private final boolean notifyOnReservation;

    public User(Long userId, String name, String email, String office, String phoneNumber, boolean notifyOnReservation) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.office = office;
        this.phoneNumber = phoneNumber;
        this.notifyOnReservation = notifyOnReservation;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getOffice() {
        return office;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isNotifyOnReservation() {
        return notifyOnReservation;
    }
}
