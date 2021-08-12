package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IProviderDAO;
import com.solvd.carfactory.models.location.Address;
import com.solvd.carfactory.models.supply.Provider;
import org.apache.log4j.Logger;

import java.sql.*;

public class ProviderDAO extends AbstractMysqlJdbcDAO<Provider> implements IProviderDAO {
    private final static Logger LOGGER = Logger.getLogger(ProviderDAO.class);
    private final static String CREATE_PROVIDER_FROM_OBJECT = "INSERT INTO "
        + "providers (name, email, phone, address_id) "
        + "VALUES (?), (?), (?), (?)";
    private final static String GET_PROVIDER_BY_ID = "SELECT * FROM providers WHERE id = ?";
    private final static String UPDATE_PROVIDER = "UPDATE providers SET "
        + "name = ?, email = ?, phone = ?, address_id = ? WHERE id = ?";
    private final static String DELETE_PROVIDER = "DELETE FROM providers WHERE id = ?";

    @Override
    public void createItem(Provider item) {
        item.setId(createItem(item, CREATE_PROVIDER_FROM_OBJECT));
    }
                
    @Override
    public Provider getItemById(long id) {
        return getItemById(id, GET_PROVIDER_BY_ID);
    }

    @Override
    public void updateItem(Provider item) {
        updateItem(item, UPDATE_PROVIDER, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_PROVIDER);
    }

    @Override
    protected Provider buildItem(ResultSet rs) throws SQLException{
        Provider provider = new Provider();

        provider.setId(rs.getLong("id"));
        provider.setName(rs.getString("name"));
        provider.setEmail(rs.getString("email"));
        provider.setPhone(rs.getString("phone"));
        provider.setAddress(new Address(rs.getLong("address_id")));

        return provider;
    }

    @Override
    protected void setPsParameters(Provider item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setString(2, item.getEmail());
        ps.setString(3, item.getPhone());
        ps.setLong(4, item.getAddress().getId());
    }
}