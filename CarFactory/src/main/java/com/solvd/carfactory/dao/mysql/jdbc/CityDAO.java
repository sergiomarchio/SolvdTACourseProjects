package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;

import java.sql.*;

public class CityDAO extends AbstractMysqlJdbcDAO implements ICityDAO {
    private final static Logger LOGGER = Logger.getLogger(CityDAO.class);
    private final static String GET_CITY_BY_ID = "SELECT * FROM cities WHERE id = ?";
    private final static String SET_CITY_FROM_OBJECT = "INSERT INTO cities(name, country_id) VALUES (?), (?)";

    @Override
    public void createItem(City city) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(SET_CITY_FROM_OBJECT,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, city.getName());
            ps.setLong(1, city.getCountry().getId());
            ps.executeQuery();

            long id;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                id = rs.getLong(1);
            }
            city.setId(id);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
    @Override
    public City getItemById(long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_CITY_BY_ID)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return buildCity(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return null;
    }
    @Override
    public void updateItem(City item) {

    }
    @Override
    public void deleteItem(long id) {

    }

    private City buildCity(ResultSet rs) throws SQLException {
        City c = new City();

        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));

        Country country = new Country(rs.getLong("country_id"));
        c.setCountry(country);

        return c;
    }
}
