package com.tubemogul.platform.tubebnb.dao;

import com.tubemogul.platform.tubebnb.config.HerokuDataSource;
import com.tubemogul.platform.tubebnb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.sql.*;

/**
 * Created by harrison.chu on 8/6/15.
 */
@Component
public class UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    private static final String H2_DATABASE_DIRECTORY = "/mnt/airtube_api";
//    private static final String USER_TABLE = "users";

    @Value("${user.dao.create.table.statement}")
    private String createTableString = null;

    @Value("${user.dao.get.user.statement}")
    private String getUserString = null;

    @Value("${user.dao.create.user.statement}")
    private String createUserString = null;

    @Value("${user.dao.get.user.by.email}")
    private String getUserByEmail = null;

    private Connection connection;

    @Autowired
    private HerokuDataSource herokuDataSource;

    @PostConstruct
    public void initialize() throws Exception {
        createDirectory();
        LOGGER.debug("Initializing User Database...");

       // String h2Database = "jdbc:h2:" + H2_DATABASE_DIRECTORY + USER_TABLE;
        //connection = DriverManager.getConnection(h2Database, "test", "");
        connection = herokuDataSource.dataSource().getConnection();

        String h2Database = "jdbc:h2:" + H2_DATABASE_DIRECTORY;
        //connection = DriverManager.getConnection(h2Database, "test", "");


        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableString);
    }

    /**
     * @return a immutable {@link User} Object based on the userId.  Returns null if no user record is found
     */
    public User getUser(Long userId) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(getUserString);

            stmt.setLong(1, userId);
            rs = stmt.executeQuery();
            String name = rs.getString("name");
            String email = rs.getString("email");
            String office = rs.getString("office");
            String phoneNumber = rs.getString("phone_number");
            boolean notifyOnReservation = rs.getBoolean("is_notify_on_reservation");

            User user = new User(userId, name, email, office, phoneNumber, notifyOnReservation);
            return user;
        } catch (Exception e) {
            LOGGER.error("An exception occurred while trying to retrieve a user");
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

    /**
     * @return a immutable {@link User} Object based on the email.  Returns null if no user record is found
     */
    public User getUser(String userEmail) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(getUserByEmail);
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();
            Long userId = rs.getLong("user_id");
            String name = rs.getString("name");
            String office = rs.getString("office");
            String phoneNumber = rs.getString("phone_number");
            boolean notifyOnReservation = rs.getBoolean("is_notify_on_reservation");

            User user = new User(userId, name, userEmail, office, phoneNumber, notifyOnReservation);
            return user;
        } catch (Exception e) {
            LOGGER.error("An exception occurred while trying to retrieve a user");
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


    /**
     * @return true if creation was successful, false if not
     */
    public boolean createUser(String name, String email, String office, String phoneNumber, boolean notifyOnReservation) {
        PreparedStatement stmt = null;
        boolean isSuccess = false;
        try {
            stmt = connection.prepareStatement(createUserString);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, office);
            stmt.setString(4, phoneNumber);
            stmt.setBoolean(5, notifyOnReservation);
            int rowsUpdated = stmt.executeUpdate();

            /* Successful insert should only update one row */
            isSuccess = rowsUpdated == 1;
        } catch (Exception e) {
            isSuccess = false;
            LOGGER.error("An Exception occurreed while trying to create a user");
        }

        return isSuccess;
    }

    private void createTableIfNotExists() {
        String createTable = "";
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

    public void setGetUserByEmail(String getUserByEmail) {
        this.getUserByEmail = getUserByEmail;
    }

    public void setGetUserString(String getUserString) {
        this.getUserString = getUserString;
    }

    public void setCreateTableString(String createTableString) {
        this.createTableString = createTableString;
    }
}
