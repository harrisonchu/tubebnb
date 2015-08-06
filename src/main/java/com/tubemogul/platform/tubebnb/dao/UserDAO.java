package com.tubemogul.platform.tubebnb.dao;

import com.tubemogul.platform.tubebnb.model.User;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by harrison.chu on 8/6/15.
 */
public class UserDAO {

    private static final String H2_DATABASE_DIRECTORY = "/mnt/airtube-api/";
    private static final String USER_TABLE = "users";

    private Connection connection;

    public void initialize() throws Exception {
        String h2Database = "jdbc:h2:" + H2_DATABASE_DIRECTORY + USER_TABLE;
        connection = DriverManager.getConnection(h2Database, "test", "");
    }

    public User getUser(int userId) {
        return null;
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
