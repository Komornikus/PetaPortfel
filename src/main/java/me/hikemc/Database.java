package me.hikemc;

import java.sql.*;

public class Dataabse {
    private Connection connection;
    public Connection getConnection() throws SQLException {
        if(connection != null) {
            return connection;
        }
    }

}
