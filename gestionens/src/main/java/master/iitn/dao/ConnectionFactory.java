package master.iitn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(ConnectionProps.url, ConnectionProps.user, ConnectionProps.password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database");


        }
        return connection;
    }

    
}