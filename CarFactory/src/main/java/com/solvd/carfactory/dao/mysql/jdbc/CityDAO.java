package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.ICityDAO;
import com.solvd.carfactory.models.location.City;
import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;

import java.sql.*;

public class CityDAO extends AbstractMysqlJdbcDAO<City> implements ICityDAO {
    private final static Logger LOGGER = Logger.getLogger(CityDAO.class);
    private final static String GET_CITY_BY_ID = "SELECT * FROM cities WHERE id = ?";
    private final static String CREATE_CITY_FROM_OBJECT = "INSERT INTO cities(name, country_id) VALUES (?), (?)";
    private final static String UPDATE_CITY = "UPDATE cities SET name = ?, country_id = ? WHERE id = ?";
    private final static String DELETE_CITY = "DELETE FROM cities WHERE id = ?";

    @Override
    public void createItem(City item) {
        item.setId(createItem(item, CREATE_CITY_FROM_OBJECT));
    }

    @Override
    public City getItemById(long id) {
        return getItemById(id, GET_CITY_BY_ID);
    }

    @Override
    public void updateItem(City item) {
        updateItem(item, UPDATE_CITY, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_CITY);
    }

    @Override
    protected City buildItem(ResultSet rs) throws SQLException {
        City c = new City();

        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));

        Country country = new Country(rs.getLong("country_id"));
        c.setCountry(country);

        return c;
    }

    @Override
    protected void setPsParameters(City item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setLong(2, item.getCountry().getId());
    }

    @Override
    public City getFullCityById(long id) {
        return null;
    }

    @Override
    public City getNestedCityById(long id) {
        return null;
    }
}
