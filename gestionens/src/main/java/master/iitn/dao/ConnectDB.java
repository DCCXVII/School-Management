package master.iitn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import java.sql.Statement;
import java.util.Properties;

import utils.DatabasePropertiesLoader;

public class ConnectDB {
    private static ConnectDB instance;
    private Connection connection;

    private ConnectDB() {
        Properties properties = DatabasePropertiesLoader.load();
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        // System.out.println("url: " + url+ " user: " + user + " password: " + password);

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the database is successful CDB");
        } catch (SQLException e) {
            System.out.println("Connection to the database failed CDB: " + e.getMessage());
        }
    }

    public static ConnectDB getInstance() {
        if (instance == null) {
            synchronized (ConnectDB.class) {
                if (instance == null) {
                    instance = new ConnectDB();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection to the database is closed CDB");
        }
    }
}