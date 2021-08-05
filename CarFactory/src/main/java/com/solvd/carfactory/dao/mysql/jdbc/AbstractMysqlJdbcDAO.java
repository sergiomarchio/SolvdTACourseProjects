package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class AbstractMysqlJdbcDAO {
    private final static Logger LOGGER = Logger.getLogger(AbstractMysqlJdbcDAO.class);

    protected<T> T getItemById(long id, String query, IBuildItem<T> itemBuilder){
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);

            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return itemBuilder.buildItem(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Error reading item:\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return null;
    }

    protected void deleteItem(long id, String query){
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
