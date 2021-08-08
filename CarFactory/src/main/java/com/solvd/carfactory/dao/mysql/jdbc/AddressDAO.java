package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.location.City;
import org.apache.log4j.Logger;

import java.sql.*;

public class AddressDAO extends AbstractMysqlJdbcDAO<Address> implements IAddressDAO {
    private final static Logger LOGGER = Logger.getLogger(AddressDAO.class);
    private final static String CREATE_ADDRESS_FROM_OBJECT = "INSERT INTO "
        + "addresses(street, number, dept_number, zip_code, city_id) VALUES (?), (?), (?), (?), (?)";
    private final static String GET_ADDRESS_BY_ID = "SELECT * FROM addresses WHERE id = ?";
    private final static String UPDATE_ADDRESS = "UPDATE addresses SET "
            + "street = ?, number = ?, dept_number = ?, zip_code = ?, city_id = ? WHERE id = ?";
    private final static String DELETE_ADDRESS = "DELETE FROM addresses WHERE id = ?";

    @Override
    public void createItem(Address item) {
        item.setId(createItem(item, CREATE_ADDRESS_FROM_OBJECT));
    }

    @Override
    public Address getItemById(long id) {
        return getItemById(id, GET_ADDRESS_BY_ID);
    }

    @Override
    public void updateItem(Address item) {
        updateItem(item, UPDATE_ADDRESS, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_ADDRESS);
    }

    @Override
    protected Address buildItem(ResultSet rs) throws SQLException{
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

    @Override
    protected void setPsParameters(Address item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getStreet());
        ps.setString(2, item.getNumber());
        ps.setString(3, item.getDeptNumber());
        ps.setString(4, item.getZipCode());
        ps.setLong(5, item.getCity().getId());
    }
}
