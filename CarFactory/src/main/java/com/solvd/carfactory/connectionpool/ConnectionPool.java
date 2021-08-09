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

    private int maxConnections;
    private String dbType;
    private String url;
    private String dbName;
    private String user;
    private String password;

    private ConnectionPool() {
        Properties prop = loadProperties();
        this.maxConnections = Integer.parseInt(prop.getProperty("maxConnections"));
        this.dbType = prop.getProperty("dbType");
        this.url = prop.getProperty("url");
        this.dbName = prop.getProperty("dbName");
        this.user = prop.getProperty("user");
        this.password = prop.getProperty("password");
        validateFields();

        this.connectionStack = new LinkedBlockingDeque<>(maxConnections);
        for (int i = 0; i < maxConnections; i++)
            connectionStack.push(Optional.empty());

        initializeDriver();
    }

    private Properties loadProperties(){
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("src/main/resources/database.properties")) {
            prop.load(input);
        } catch (IOException e){
            LOGGER.error("Error trying to open src/main/resources/database.properties\n" + e);
        }

        return prop;
    }

    private void validateFields(){
        if(maxConnections <= 0) {
            throw new IllegalArgumentException("maxConnections should be greater than zero!");
        }
    }

    private void initializeDriver(){
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
