package com.solvd.carfactory.dao.mysql.jdbc;

import com.mysql.cj.MysqlType;
import com.solvd.carfactory.dao.IClientOrderDAO;
import com.solvd.carfactory.models.client.Client;
import com.solvd.carfactory.models.client.ClientOrder;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class ClientOrderDAO extends AbstractMysqlJdbcDAO<ClientOrder> implements IClientOrderDAO {
    private final static Logger LOGGER = Logger.getLogger(ClientOrderDAO.class);
    private final static String CREATE_CLIENTORDER_FROM_OBJECT = "INSERT INTO "
        + "client_orders (creation_date, discount_percent, delivery_date, client_id) "
        + "VALUES (?), (?), (?), (?)";
    private final static String GET_CLIENTORDER_BY_ID = "SELECT * FROM client_orders WHERE id = ?";
    private final static String UPDATE_CLIENTORDER = "UPDATE client_orders SET "
        + "creation_date = ?, discount_percent = ?, delivery_date = ?, client_id = ? WHERE id = ?";
    private final static String DELETE_CLIENTORDER = "DELETE FROM client_orders WHERE id = ?";

    @Override
    public void createItem(ClientOrder item) {
        item.setId(createItem(item, CREATE_CLIENTORDER_FROM_OBJECT));
    }
                
    @Override
    public ClientOrder getItemById(long id) {
        return getItemById(id, GET_CLIENTORDER_BY_ID);
    }

    @Override
    public void updateItem(ClientOrder item) {
        updateItem(item, UPDATE_CLIENTORDER, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_CLIENTORDER);
    }

    @Override
    protected ClientOrder buildItem(ResultSet rs) throws SQLException{
        ClientOrder clientOrder = new ClientOrder();

        clientOrder.setId(rs.getLong("id"));
        clientOrder.setCreationDate(rs.getObject("creation_date", LocalDateTime.class));
        clientOrder.setDiscountPercent(rs.getDouble("discount_percent"));
        clientOrder.setDeliveryDate(rs.getObject("delivery_date", LocalDateTime.class));
        clientOrder.setClient(new Client(rs.getLong("client_id")));

        return clientOrder;
    }

    @Override
    protected void setPsParameters(ClientOrder item, PreparedStatement ps) throws SQLException {
        ps.setObject(1, item.getCreationDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")), MysqlType.DATETIME);
        ps.setDouble(2, item.getDiscountPercent());
        ps.setObject(3, item.getDeliveryDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")), MysqlType.DATETIME);
        ps.setLong(4, item.getClient().getId());
    }
}