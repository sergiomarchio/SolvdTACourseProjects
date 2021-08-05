package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.connectionpool.ConnectionPool;
import com.solvd.carfactory.dao.IAddressDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.location.City;
import org.apache.log4j.Logger;

import java.sql.*;

public class AddressDAO extends AbstractMysqlJdbcDAO implements IAddressDAO {
    private final static Logger LOGGER = Logger.getLogger(AddressDAO.class);
    private final static String CREATE_ADDRESS_FROM_OBJECT = "INSERT INTO "
        + "address(street, number, dept_number, zip_code, city_id) VALUES (?), (?), (?), (?), (?)";
    private final static String GET_ADDRESS_BY_ID = "SELECT * FROM addresses WHERE id = ?";
    private final static String UPDATE_ADDRESS = "UPDATE addresses SET "
            + "street = ?, number = ?, dept_number = ?, zip_code = ?, city_id = ? WHERE id = ?";
    private final static String DELETE_ADDRESS = "DELETE FROM addresses WHERE id = ?";

    @Override
    public void createItem(Address item) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_ADDRESS_FROM_OBJECT,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getStreet());
            ps.setString(2, item.getNumber());
            ps.setString(3, item.getDeptNumber());
            ps.setString(4, item.getZipCode());
            ps.setLong(5, item.getCity().getId());
            ps.executeQuery();

            long id;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                id = rs.getLong(1);
            }
            item.setId(id);
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
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
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

        return null;
    }

    @Override
    public void updateItem(Address item) {
        Connection connection = ConnectionPool.getInstance().getConnection();

        try(PreparedStatement ps = connection.prepareStatement(UPDATE_ADDRESS)){
            ps.setString(1, item.getStreet());
            ps.setString(2, item.getNumber());
            ps.setString(3, item.getDeptNumber());
            ps.setString(4, item.getZipCode());
            ps.setLong(5, item.getCity().getId());
            ps.setLong(6, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error updating item:\n" + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void deleteItem(long id) {
        deleteItemQuery(id, DELETE_ADDRESS);
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
