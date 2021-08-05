package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public abstract class AbstractMysqlJdbcDAO<T> {
    private final static Logger LOGGER = Logger.getLogger(AbstractMysqlJdbcDAO.class);

    protected abstract T buildItem(ResultSet rs) throws SQLException;
    protected abstract void setPsParameters(T item, PreparedStatement ps) throws SQLException;

    protected Long createItem(T item, String query){
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)){
            setPsParameters(item, ps);
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error creating item:\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return null;
    }

    protected T getItemById(long id, String query){
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);

            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return buildItem(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Error reading item:\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return null;
    }

    protected void updateItem(T item, String query, long id){
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(query)){
            setPsParameters(item, ps);
            ps.setLong(ps.getParameterMetaData().getParameterCount(), id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating item:\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
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
