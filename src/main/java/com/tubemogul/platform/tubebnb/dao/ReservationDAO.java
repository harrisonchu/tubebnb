package com.tubemogul.platform.tubebnb.dao;

import com.tubemogul.platform.tubebnb.model.Reservation;
import com.tubemogul.platform.tubebnb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin.lee on 8/6/15.
 */
@Component
public class ReservationDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDAO.class);

    private static final String H2_DATABASE_DIRECTORY = "/mnt/airtube_api";
    private static final String RESERVATION_TABLE = "reservations";

    @Value("${reservation.dao.create.table.statement}")
    private String createTableString = null;

    @Value("${reservation.dao.get.reservation.statement}")
    private String getReservationString = null;

    @Value("${reservation.dao.create.reservation.statement}")
    private String createReservationString = null;

    @Value("${reservation.dao.get.reservation.by.user.statement}")
    private String getReservationByUserString = null;

    private Connection connection;

    @PostConstruct
    public void initialize() throws Exception {
        createDirectory();
        LOGGER.info("Initializing Reservation Database...");
        String h2Database = "jdbc:h2:" + H2_DATABASE_DIRECTORY;
        connection = DriverManager.getConnection(h2Database, "test", "");

        System.out.println(createTableString);
        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableString);
    }

    /**
     * @return a immutable {@link com.tubemogul.platform.tubebnb.model.Reservation} Object based on the userId.  Returns null if no user record is found
     */
    public Reservation getReservation(long reservationId) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(getReservationString);
            stmt.setLong(1, reservationId);
            rs = stmt.executeQuery(getReservationString);

            long listingId = rs.getLong("listing_id");
            long hostUserId = rs.getLong("host_user_id");
            long locationId = rs.getLong("location_id");
            long travelerUserId = rs.getLong("traveler_user_id");
            String startTime = rs.getString("start_time");
            String endTime = rs.getString("end_time");
            String timezone = rs.getString("timezone");
            String status = rs.getString("status");

            Reservation reservation = new Reservation(reservationId, listingId, hostUserId, locationId, travelerUserId, startTime, endTime, timezone, status);
            return reservation;
        } catch (Exception e) {
            LOGGER.error("An exception occurred while trying to retrieve a user", e);
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {}
            try {
                rs.close();
            } catch (Exception e) {}
        }

        /* If no user returned, then null */
        return null;
    }

    public List<Reservation> getAllReservationsForUser(long userId) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Reservation> reservations = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(getReservationByUserString);
            stmt.setLong(1, userId);
            stmt.setLong(2, userId);
            rs = stmt.executeQuery();

            long reservationId = rs.getLong("reservation_id");
            long listingId = rs.getLong("listing_id");
            long hostUserId = rs.getLong("host_user_id");
            long locationId = rs.getLong("location_id");
            long travelerUserId = rs.getLong("traveler_user_id");
            String startTime = rs.getString("start_time");
            String endTime = rs.getString("end_time");
            String timezone = rs.getString("timezone");
            String status = rs.getString("status");

            Reservation reservation = new Reservation(reservationId, listingId, hostUserId, locationId, travelerUserId, startTime, endTime, timezone, status);
            reservations.add(reservation);
        } catch (Exception e) {
            LOGGER.error("An exception occurred while trying to retrieve a user", e);
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {}
            try {
                rs.close();
            } catch (Exception e) {}
        }

        return reservations;
    }

    /**
     * @return true if creation was successful, false if not
     */
    public boolean createReservation(long listingId, long hostUserId, long locationId, long travelerUserId, long startTime, long endTime, String timezone) throws SQLException {
        PreparedStatement stmt = null;
        boolean isSuccess = false;
        try {
            stmt = connection.prepareStatement(createReservationString, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, listingId);
            stmt.setLong(2, hostUserId);
            stmt.setLong(3, locationId);
            stmt.setLong(4, travelerUserId);
            stmt.setLong(5, startTime);
            stmt.setLong(6, endTime);
            stmt.setString(8, timezone);
            int rowsUpdated = stmt.executeUpdate();

            /* Successful insert should only update one row */
            isSuccess = rowsUpdated == 1;

            if (isSuccess) {
//                Reservation reservation = new Reservation(reservationId, listingId, hostUserId, locationId, travelerUserId, startTime, endTime, status, timezone);
            }
        } catch (Exception e) {
            isSuccess = false;
            LOGGER.error("An Exception occurreed while trying to create a user", e);
        } finally {
            stmt.close();
        }

        return isSuccess;
    }

    private void createDirectory() throws Exception {
        File f = new File(H2_DATABASE_DIRECTORY);
        if (!f.exists()) {
            boolean success = f.mkdirs();
            if (!success) {
                throw new Exception("Error creating directory for H2 database.");
            }
        }
    }

    public void setGetReservationByUserString(String getReservationByUserString) {
        this.getReservationByUserString = getReservationByUserString;
    }

    public void setGetReservationString(String getReservationString) {
        this.getReservationString = getReservationString;
    }

    public void setCreateTableString(String createTableString) {
        this.createTableString = createTableString;
    }
}
