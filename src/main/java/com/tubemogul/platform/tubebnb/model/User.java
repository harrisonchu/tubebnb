package com.tubemogul.platform.tubebnb.model;

/**
 * Created by harrison.chu on 8/6/15.
 */
public class User {

    private final int userId;
    private final String name;
    private final String email;
    private final String office;
    private final int phoneNumber;
    private final boolean notifyOnReservation;

    public User(int userId, String name, String email, String office, int phoneNumber, boolean notifyOnReservation) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.office = office;
        this.phoneNumber = phoneNumber;
        this.notifyOnReservation = notifyOnReservation;
    }

    public int getUserId() {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isNotifyOnReservation() {
        return notifyOnReservation;
    }
}
