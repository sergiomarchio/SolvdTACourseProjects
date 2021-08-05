package com.solvd.carfactory.connectionpool;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Properties;

public class ConnectionPool {
    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private BlockingDeque<Optional<Connection>> connectionStack;

    private static int maxConnections;
    private static String dbType;
    private static String url;
    private static String dbName;
    private static String user;
    private static String password;

    private ConnectionPool() {
        this.connectionStack = new LinkedBlockingDeque<>(maxConnections);
        for (int i = 0; i < maxConnections; i++)
            connectionStack.push(Optional.empty());
    }

    private static void loadProperties(){
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("src/main/resources/database.properties")) {
            prop.load(input);

            maxConnections = Integer.parseInt(prop.getProperty("maxConnections"));
            if(maxConnections < 0) {
                throw new IllegalArgumentException("maxConnections should be greater than zero!");
            }
            dbType = prop.getProperty("dbType");
            url = prop.getProperty("url");
            dbName = prop.getProperty("dbName");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e){
            LOGGER.error("Error trying to open src/main/resources/database.properties\n" + e);
        }
    }

    private static void initializeDriver(){
        switch (dbType){
            case "mysql":
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    LOGGER.error("Error loading mysql driver class:\n" + e);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported database: " + dbType);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            loadProperties();
            initializeDriver();
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connectionStack.size() == 0) return null;

        Connection connection = connectionStack.pop().orElse(null);
        if (connection == null)
            try {
                connection = DriverManager.getConnection("jdbc:" + dbType + "://" + url + "/" + dbName,
                        user, password);
            } catch (SQLException e) {
                LOGGER.error("Error getting database connection from " + url + "\n" + e);
            }

        return connection;
    }

    public void returnConnection(Connection connection) {
        if (connectionStack.size() < maxConnections) {
            connectionStack.push(Optional.of(connection));
        } else {
            throw new RuntimeException("Connection stack reached its max capacity: " + maxConnections);
        }
    }

    public void closeAll() {
        try {
            // Using for each because lambdas cannot throw exceptions
            for (Optional<Connection> connectionBox : connectionStack) {
                Connection connection = connectionBox.orElse(null);
                if (connection != null) connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Error closing connection: " + e);
        }
    }
}
