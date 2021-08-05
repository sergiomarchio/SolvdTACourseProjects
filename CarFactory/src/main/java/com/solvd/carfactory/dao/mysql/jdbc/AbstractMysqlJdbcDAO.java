package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractMysqlJdbcDAO {
    private final static Logger LOGGER = Logger.getLogger(AbstractMysqlJdbcDAO.class);

    protected void deleteItemQuery(long id, String query){
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error deleting item with id: " + id + "\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}
