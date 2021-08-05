package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;

import java.sql.*;

public class CountryDAO extends AbstractMysqlJdbcDAO implements ICountryDAO {
    private final static Logger LOGGER = Logger.getLogger(CountryDAO.class);
    private final static String CREATE_COUNTRY_FROM_OBJECT = "INSERT INTO countries(name) VALUES (?)";
    private final static String GET_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id = ?";
    private final static String UPDATE_COUNTRY = "UPDATE countries SET name = ? WHERE id = ?";
    private final static String DELETE_COUNTRY = "DELETE FROM countries WHERE id = ?";

    @Override
    public void createItem(Country item) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(CREATE_COUNTRY_FROM_OBJECT,
                Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, item.getName());
            ps.executeUpdate();

            long id;
            try(ResultSet rs = ps.getGeneratedKeys()){
                rs.next();
                id = rs.getLong(1);
            }
            item.setId(id);
        } catch (SQLException e) {
            LOGGER.error("Error creating item:\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public Country getItemById(long id) {
        return getItemById(id, GET_COUNTRY_BY_ID, this::buildCountry);
    }

    @Override
    public void updateItem(Country item) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(UPDATE_COUNTRY)){
            ps.setString(1, item.getName());
            ps.setLong(2, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating item:\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_COUNTRY);
    }

    private Country buildCountry(ResultSet rs) throws SQLException{
        Country c = new Country();
        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));
        return c;
    }
}
