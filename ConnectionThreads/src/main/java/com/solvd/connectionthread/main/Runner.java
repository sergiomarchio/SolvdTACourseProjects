package com.solvd.connectionthread.main;


import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.*;

public class Runner {
    private final static Logger LOGGER = Logger.getLogger(Runner.class);

    // Temporary database for testing purposes
    private static void createTempDB(){
        try(Connection conn = DriverManager.getConnection("jdbc:derby:memory:test_db;create=true");){
            Statement s = conn.createStatement();

            s.executeUpdate("create table test_table "
                    + "(id int primary key, firstName varchar(20), lastName varchar(20), "
                    +"age int, email varchar(50), country varchar(25), "
                    + "occupation varchar(20))");

            s.executeUpdate("insert into test_table values "
                    + "(1, 'Mickey', 'Mouse', 10, 'mickeymouse@disney.com', 'USA', '2D character'), "
                    + "(2, 'Peter', 'Parker', 21, 'spiderman@marvel.com', 'USA', 'Photographer'),"
                    + "(3, 'Clark', 'Kent', 38, 'super@dailyplanet.com', 'Kriptonian Replublic', 'Journalist')");
        } catch (SQLException e){
            LOGGER.error("Error creating test database: " + e);
        }
    }

    public final static void main(String[] args) {
        createTempDB();

        ExecutorService es = Executors.newFixedThreadPool(6);
        ConnectionPool cp = ConnectionPool.init(5);

        Future<ResultSet> userFirstName = es.submit(
                () -> runTask("select firstName from test_table where id = 1", cp));
        Future<ResultSet> userLastName = es.submit(
                () -> runTask("select lastName from test_table where id = 1", cp));
        Future<ResultSet> userEmail = es.submit(
                () -> runTask("select email from test_table where id = 1", cp));
        Future<ResultSet> userCountry = es.submit(
                () -> runTask("select country from test_table where id = 1", cp));
        Future<ResultSet> userAge = es.submit(
                () -> runTask("select age from test_table where id = 1", cp));
        Future<ResultSet> userOccupation = es.submit(
                () -> runTask("select occupation from test_table where id = 1", cp));

        es.shutdown();

        try {
            LOGGER.debug("For the user with id 1:"
                    + "\nfirst name: " + userFirstName.get().getString("firstName")
                    + "\nlast name: " + userLastName.get().getString("lastName")
                    + "\nage: " + userAge.get().getInt("age")
                    + "\nemail: " + userEmail.get().getString("email")
                    + "\ncountry: " + userCountry.get().getString("country")
                    + "\noccupation: " + userOccupation.get().getString("occupation"));
        } catch (ExecutionException | InterruptedException | SQLException e) {
            LOGGER.error(e);
        }

        cp.closeAll();

        try {
            LOGGER.debug("Shutting down database service");
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e)  {
            System.out.println(e);
        }
    }

    private static ResultSet runTask(String query, ConnectionPool cp) {
        String threadName = Thread.currentThread().getName();
        ResultSet result = null;

        try {
            LOGGER.debug("Thread " + threadName + " is running...");

            Connection connection = cp.getConnection();
            while (connection == null){
                LOGGER.debug("Thread " + threadName + " waiting for available connections...");
                Thread.sleep(50);
                connection = cp.getConnection();
            }

            Statement s = connection.createStatement();

            result = s.executeQuery(query);

            result.next();

            cp.returnConnection(connection);

        } catch (InterruptedException | SQLException e){
            LOGGER.error("Error in thread " + threadName + ": " + e);
        }

        return result;
    }
}
