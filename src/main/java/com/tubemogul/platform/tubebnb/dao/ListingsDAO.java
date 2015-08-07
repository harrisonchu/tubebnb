package com.tubemogul.platform.tubebnb.dao;

import com.tubemogul.platform.tubebnb.model.Listing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.sql.*;

/**
 * Created by michael.okuma on 8/6/15.
 */
@Component
public class ListingsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingsDAO.class);


    private static final String H2_DATABASE_DIRECTORY = "/mnt/airtube_api";
    private static final String LISTINGS_TABLE = "listings";

    @Value("${listings.dao.create.table.statement}")
    private String createTableString = null;

    @Value("${listings.dao.get.listings.statement}")
    private String getListingString = null;

    @Value("${listings.dao.create.listings.statement}")
    private String createListingString = null;

    private Connection connection;

    @PostConstruct
    public void initialize() throws Exception {
        createDirectory();
        String h2Database = "jdbc:h2:" + H2_DATABASE_DIRECTORY;
        connection = DriverManager.getConnection(h2Database, "test", "");

        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableString);
    }

    public Listing getListing(int listingId) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(getListingString);
            stmt.setInt(1,listingId);
            rs = stmt.executeQuery();
            rs.next();

            int userId = rs.getInt("user_id");
            int locationId = rs.getInt("location_id");
            Boolean briefcase = rs.getBoolean("is_briefcase");
            Boolean flipflops = rs.getBoolean("is_flipflops");

            Listing listing = new Listing(userId, listingId, locationId, briefcase, flipflops);
            return listing;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("An exception occurred while trying to retrieve a listing");
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {}
            try {
                rs.close();
            } catch (Exception e) {}
        }

        /* If no listing returned, then null */
        return null;
    }

    /*

            INSERT INTO Listings (user_id, location_id, is_briefcase, is_flipflops,
                                            is_allow_pets, is_allow_smoking)
                                        VALUES (?, ?, ?, ?, ?,?);
     */

    public Listing createListing(Integer userId,
                                 Integer locationId,
                                 Boolean isBriefCase,
                                 Boolean isFlipFlops,
                                 Boolean is420) throws SQLException {
        Listing listingResponse = null;
        boolean isSuccess;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(createListingString, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, userId);
            stmt.setInt(2, locationId);
            stmt.setBoolean(3, isBriefCase);
            stmt.setBoolean(4, isFlipFlops);
            stmt.setBoolean(5, false);

            int rowsUpdated = stmt.executeUpdate();

            /* Successful insert should only update one row */
            isSuccess = rowsUpdated == 1;

            if(isSuccess) {

                rs = stmt.getGeneratedKeys();
                int listingId = 0;
                if(rs.next()) {
                    listingId = rs.getInt(1);
                }
                listingResponse = new Listing(userId,listingId, locationId, isBriefCase, isFlipFlops);
            }
            return listingResponse;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
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

}
