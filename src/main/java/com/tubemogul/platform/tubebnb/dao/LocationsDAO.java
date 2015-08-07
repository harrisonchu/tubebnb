package com.tubemogul.platform.tubebnb.dao;

import com.tubemogul.platform.tubebnb.model.Listing;
import com.tubemogul.platform.tubebnb.model.Location;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harrison.chu on 8/6/15.
 */
@Component
public class LocationsDAO {

    private static final String H2_DATABASE_DIRECTORY = "/mnt/airtube_api";

    private String createTableString = "CREATE TABLE IF NOT EXISTS Locations " +
            "(location_id BIGINT(20), " +
            "name VARCHAR(100), " +
            "PRIMARY KEY(location_id), " +
            "UNIQUE (name) );";

    private String getAllLocationsString = "SELECT * FROM Locations;";

    private String createLocationString = "INSERT INTO Locations (location_id, name) VALUES (?, ?);";

    private Connection connection;

    @PostConstruct
    public void initialize() throws Exception {
        createDirectory();
        String h2Database = "jdbc:h2:" + H2_DATABASE_DIRECTORY;
        connection = DriverManager.getConnection(h2Database, "test", "");

        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableString);
        try {
            loadDefaultLocations();
        } catch (Exception e) {};
    }

    public List<Location> getAllLocations() throws Exception {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Location> locations = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(getAllLocationsString);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int locationId = rs.getInt("location_id");
                String name = rs.getString("name");
                locations.add(new Location(name, locationId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {}
            try {
                rs.close();
            } catch (Exception e) {}
        }

        return locations;
    }

    private void loadDefaultLocations() throws Exception {
        loadLocationWithNameAndId(1, "Emeryville");
        loadLocationWithNameAndId(2, "Chicago");
        loadLocationWithNameAndId(3, "Detroit");
        loadLocationWithNameAndId(4, "Kyiv");
        loadLocationWithNameAndId(5, "London");
        loadLocationWithNameAndId(6, "LA");
        loadLocationWithNameAndId(7, "Minneapolis");
        loadLocationWithNameAndId(8, "New York");
        loadLocationWithNameAndId(9, "Sao Paulo");
        loadLocationWithNameAndId(10, "Singapore");
        loadLocationWithNameAndId(11, "Shanghai");
        loadLocationWithNameAndId(12, "Sydney");
        loadLocationWithNameAndId(13, "Tokyo");
        loadLocationWithNameAndId(14, "Toronto");
        loadLocationWithNameAndId(15, "Chengdu");
        loadLocationWithNameAndId(16, "Other");
    }

    private void loadLocationWithNameAndId(int id, String name) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(createLocationString);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
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
}
