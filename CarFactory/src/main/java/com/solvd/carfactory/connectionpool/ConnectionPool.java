package com.solvd.carfactory.connectionpool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;

    private int maxConnections;
    private String url;
    private String dbName;
    private String user;
    private String password;

    private BlockingDeque<Optional<Connection>> connectionStack;

    private ConnectionPool(int maxConnections, String url, String dbName, String user, String password) {
        this.maxConnections = maxConnections;
        this.url = url;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.connectionStack = new LinkedBlockingDeque<>(maxConnections);

        for (int i = 0; i < maxConnections; i++)
            connectionStack.push(Optional.empty());
    }

    public static ConnectionPool init(int maxConnections, String url, String dbName, String user, String password){
        if (instance != null) {
            throw new RuntimeException("You have already initialized the pool!");
        }

        instance = new ConnectionPool(maxConnections, url, dbName, user, password);
        return instance;
    }

    public static ConnectionPool init(int maxConnections, String url, String user, String password){
        return init(maxConnections, url, "",user, password);
    }

    public static ConnectionPool getInstance(){
       if (instance == null){
           throw new RuntimeException("You have to call init the first time!");
       }

       return instance;
    }

    public Connection getConnection() {
        if (connectionStack.size() == 0) return null;

        Connection connection = connectionStack.pop().orElse(null);
        if (connection == null)
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + url + "/" + dbName, user, password);
            } catch (SQLException e){
                LOGGER.error("Error getting database connection from " + url + "\n" + e);
            }

        return connection;
    }

    public void returnConnection(Connection connection) {
        if (connectionStack.size() < maxConnections) {
            connectionStack.push(Optional.of(connection));
        }
        else {
            throw new RuntimeException("Connection stack reached its max capacity: " + maxConnections);
        }
    }

    public void closeAll(){
        try {
            // Using for each because lambdas cannot throw exceptions
            for (Optional<Connection> connectionBox : connectionStack) {
                Connection connection = connectionBox.orElse(null);
                if (connection != null) connection.close();
            }
        } catch (SQLException e){
            LOGGER.error("Error closing connection: " + e);
        }
    }

    public int getMaxConnections() {
        return maxConnections;
    }
}
