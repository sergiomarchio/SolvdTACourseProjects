package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.ICountryDAO;
import com.solvd.carfactory.models.location.Country;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO extends AbstractMysqlJdbcDAO implements ICountryDAO {
    private final static Logger LOGGER = Logger.getLogger(CountryDAO.class);
    private final static String GET_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id = ?";

    @Override
    public void createItem(Country item) {

    }
    @Override
    public Country getItemById(long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(GET_COUNTRY_BY_ID)) {
            ps.setLong(1, id);

            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                Country c = new Country();

                c.setId(rs.getLong("id"));
                c.setName(rs.getString("name"));

                return c;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return null;
    }
    @Override
    public void updateItem(Country item) {

    }
    @Override
    public void deleteItem(long id) {

    }
}
