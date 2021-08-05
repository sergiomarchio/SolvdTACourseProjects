package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;

import java.sql.*;

public class CountryDAO extends AbstractMysqlJdbcDAO<Country> implements ICountryDAO {
    private final static Logger LOGGER = Logger.getLogger(CountryDAO.class);
    private final static String CREATE_COUNTRY_FROM_OBJECT = "INSERT INTO countries(name) VALUES (?)";
    private final static String GET_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id = ?";
    private final static String UPDATE_COUNTRY = "UPDATE countries SET name = ? WHERE id = ?";
    private final static String DELETE_COUNTRY = "DELETE FROM countries WHERE id = ?";

    @Override
    public void createItem(Country item) {
        item.setId(createItem(item, CREATE_COUNTRY_FROM_OBJECT));
    }

    @Override
    public Country getItemById(long id) {
        return getItemById(id, GET_COUNTRY_BY_ID);
    }

    @Override
    public void updateItem(Country item) {
        updateItem(item, UPDATE_COUNTRY, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_COUNTRY);
    }

    @Override
    protected Country buildItem(ResultSet rs) throws SQLException{
        Country c = new Country();
        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));
        return c;
    }

    @Override
    protected void setPsParameters(Country item, PreparedStatement ps) throws SQLException{
        ps.setString(1, item.getName());
    }
}
