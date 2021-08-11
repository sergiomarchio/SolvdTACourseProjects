package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IClientDAO;
import com.solvd.carfactory.models.client.Client;
import com.solvd.carfactory.models.location.Address;
import org.apache.log4j.Logger;

import java.sql.*;

public class ClientDAO extends AbstractMysqlJdbcDAO<Client> implements IClientDAO {
    private final static Logger LOGGER = Logger.getLogger(ClientDAO.class);
    private final static String CREATE_CLIENT_FROM_OBJECT = "INSERT INTO "
        + "clients (name, email, phone, address_id) "
        + "VALUES (?), (?), (?), (?)";
    private final static String GET_CLIENT_BY_ID = "SELECT * FROM clients WHERE id = ?";
    private final static String UPDATE_CLIENT = "UPDATE clients SET "
        + "name = ?, email = ?, phone = ?, address_id = ? WHERE id = ?";
    private final static String DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";

    @Override
    public void createItem(Client item) {
        item.setId(createItem(item, CREATE_CLIENT_FROM_OBJECT));
    }
                
    @Override
    public Client getItemById(long id) {
        return getItemById(id, GET_CLIENT_BY_ID);
    }

    @Override
    public void updateItem(Client item) {
        updateItem(item, UPDATE_CLIENT, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_CLIENT);
    }

    @Override
    protected Client buildItem(ResultSet rs) throws SQLException{
        Client client = new Client();

        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setEmail(rs.getString("email"));
        client.setPhone(rs.getString("phone"));
        client.setAddress(new Address(rs.getLong("address_id")));

        return client;
    }

    @Override
    protected void setPsParameters(Client item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setString(2, item.getEmail());
        ps.setString(3, item.getPhone());
        ps.setLong(4, item.getAddress().getId());
    }
}