package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.location.City;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO extends AbstractMysqlJdbcDAO implements IAddressDAO {
    private final static Logger LOGGER = Logger.getLogger(AddressDAO.class);
    private final static String GET_ADDRESS_BY_ID = "SELECT * FROM addresses WHERE id = ?";

    @Override
    public void createItem(Address item) {

    }
    @Override
    public Address getItemById(long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(GET_ADDRESS_BY_ID)) {
            ps.setLong(1, id);

            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                return buildAddress(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return null;
    }
    @Override
    public void updateItem(Address item) {

    }
    @Override
    public void deleteItem(long id) {

    }

    private Address buildAddress(ResultSet rs) throws SQLException{
        Address a = new Address();

        a.setId(rs.getLong("id"));
        a.setStreet(rs.getString("street"));
        a.setNumber(rs.getString("number"));
        a.setDeptNumber(rs.getString("dept_number"));
        a.setZipCode(rs.getString("zip_code"));

        City city = new City(rs.getLong("city_id"));
        a.setCity(city);

        return a;
    }
}
