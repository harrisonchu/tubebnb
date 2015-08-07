package com.tubemogul.platform.tubebnb.dao;

import com.tubemogul.platform.tubebnb.model.Listing;
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
 * Created by michael.okuma on 8/6/15.
 */
@Component
public class ListingsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingsDAO.class);


    private static final String H2_DATABASE_DIRECTORY = "/mnt/airtube_api";

    @Value("${listings.dao.create.table.statement}")
    private String createTableString = null;

    @Value("${listings.dao.get.listings.statement}")
    private String getListingString = null;

    @Value("${listings.dao.create.listings.statement}")
    private String createListingString = null;

    @Value("${listings.dao.get.all.listings}")
    private String getAllListingString = null;

    @Value("${listing.dao.delete.listing}")
    private String deleteListingString = null;

    private Connection connection;

    @PostConstruct
    public void initialize() throws Exception {
        createDirectory();
        String h2Database = "jdbc:h2:" + H2_DATABASE_DIRECTORY;
        connection = DriverManager.getConnection(h2Database, "test", "");

        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableString);
    }

    public void deleteListingById(int listingId) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(deleteListingString);
            stmt.setInt(1, listingId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("An exception occurred while trying to retrieve a listing");
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {}
        }
    }

    public List<Listing> getAllListings() {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Listing> listings = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(getAllListingString);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int locationId = rs.getInt("location_id");
                String type = rs.getString("type");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int listingId = rs.getInt("listing_id");
                String email = rs.getString("email");
                Listing listing = new Listing(type, locationId, email, listingId, name, description);
                listings.add(listing);
            }
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

        return listings;
    }

    public Listing getListingByEmail(String email) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(getListingString);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rs.next();

            int locationId = rs.getInt("location_id");
            String type = rs.getString("type");
            String name = rs.getString("name");
            String description = rs.getString("description");
            int listingId = rs.getInt("listing_id");
            return new Listing(type, locationId, email, listingId, name, description);
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

    public Listing createListing(String email,
                                 Integer locationId,
                                 String type,
                                 String name,
                                 String description) throws SQLException {
        Listing listingResponse = null;
        boolean isSuccess;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(createListingString, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, email);
            stmt.setInt(2, locationId);
            stmt.setString(3, type);
            stmt.setString(4, name);
            stmt.setString(5, description);

            int rowsUpdated = stmt.executeUpdate();

            /* Successful insert should only update one row */
            isSuccess = rowsUpdated == 1;

            if(isSuccess) {
                rs = stmt.getGeneratedKeys();
                int listingId = 0;
                if(rs.next()) {
                    listingId = rs.getInt(1);
                }
                listingResponse = new Listing(type, locationId, email, listingId, name, description);
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

    private void createDirectory() throws Exception {
        File f = new File(H2_DATABASE_DIRECTORY);
        if (!f.exists()) {
            boolean success = f.mkdirs();
            if (!success) {
                throw new Exception("Error creating directory for H2 database.");
            }
        }
    }

    public void setDeleteListingString(String deleteListingString) {
        this.deleteListingString = deleteListingString;
    }

    public void setCreateTableString(String createTableString) {
        this.createTableString = createTableString;
    }

    public void setGetListingString(String getListingString) {
        this.getListingString = getListingString;
    }

    public void setCreateListingString(String createListingString) {
        this.createListingString = createListingString;
    }

    public void setGetAllListingString(String getAllListingString) {
        this.getAllListingString = getAllListingString;
    }
}
