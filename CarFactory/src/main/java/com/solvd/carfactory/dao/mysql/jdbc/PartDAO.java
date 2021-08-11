package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IPartDAO;
import com.solvd.carfactory.models.car.Car;
import com.solvd.carfactory.models.car.Part;
import com.solvd.carfactory.models.supply.PartType;
import org.apache.log4j.Logger;

import java.sql.*;

public class PartDAO extends AbstractMysqlJdbcDAO<Part> implements IPartDAO {
    private final static Logger LOGGER = Logger.getLogger(PartDAO.class);
    private final static String CREATE_PART_FROM_OBJECT = "INSERT INTO "
        + "parts (serial_number, car_id, part_type_id) "
        + "VALUES (?), (?), (?)";
    private final static String GET_PART_BY_ID = "SELECT * FROM parts WHERE id = ?";
    private final static String UPDATE_PART = "UPDATE parts SET "
        + "serial_number = ?, car_id = ?, part_type_id = ? WHERE id = ?";
    private final static String DELETE_PART = "DELETE FROM parts WHERE id = ?";

    @Override
    public void createItem(Part item) {
        item.setId(createItem(item, CREATE_PART_FROM_OBJECT));
    }
                
    @Override
    public Part getItemById(long id) {
        return getItemById(id, GET_PART_BY_ID);
    }

    @Override
    public void updateItem(Part item) {
        updateItem(item, UPDATE_PART, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_PART);
    }

    @Override
    protected Part buildItem(ResultSet rs) throws SQLException{
        Part part = new Part();

        part.setId(rs.getLong("id"));
        part.setSerialNumber(rs.getString("serial_number"));
        part.setCar(new Car(rs.getLong("car_id")));
        part.setPartType(new PartType(rs.getLong("part_type_id")));

        return part;
    }

    @Override
    protected void setPsParameters(Part item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getSerialNumber());
        ps.setLong(2, item.getCar().getId());
        ps.setLong(3, item.getPartType().getId());
    }
}