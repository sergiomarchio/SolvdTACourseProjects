package com.solvd.carfactory.dao.mysql.jdbc;

import com.mysql.cj.MysqlType;
import com.solvd.carfactory.dao.ICarDAO;
import com.solvd.carfactory.models.car.Car;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.models.client.ClientOrder;
import com.solvd.carfactory.models.supply.PaintColor;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class CarDAO extends AbstractMysqlJdbcDAO<Car> implements ICarDAO {
    private final static Logger LOGGER = Logger.getLogger(CarDAO.class);
    private final static String CREATE_CAR_FROM_OBJECT = "INSERT INTO "
            + "cars (manufactured_date, car_model_id, client_order_id, paint_color_id) "
            + "VALUES (?), (?), (?), (?)";
    private final static String GET_CAR_BY_ID = "SELECT * FROM cars WHERE id = ?";
    private final static String UPDATE_CAR = "UPDATE cars SET "
            + "manufactured_date = ?, car_model_id = ?, client_order_id = ?, paint_color_id = ? WHERE id = ?";
    private final static String DELETE_CAR = "DELETE FROM cars WHERE id = ?";

    @Override
    public void createItem(Car item) {
        item.setId(createItem(item, CREATE_CAR_FROM_OBJECT));
    }

    @Override
    public Car getItemById(long id) {
        return getItemById(id, GET_CAR_BY_ID);
    }

    @Override
    public void updateItem(Car item) {
        updateItem(item, UPDATE_CAR, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_CAR);
    }

    @Override
    protected Car buildItem(ResultSet rs) throws SQLException {
        Car car = new Car();

        car.setId(rs.getLong("id"));
        car.setManufacturedDate(rs.getObject("manufactured_date", LocalDateTime.class));
        car.setCarModel(new CarModel(rs.getLong("car_model_id")));
        car.setClientOrder(new ClientOrder(rs.getLong("client_order_id")));
        car.setPaintColor(new PaintColor(rs.getLong("paint_color_id")));

        return car;
    }

    @Override
    protected void setPsParameters(Car item, PreparedStatement ps) throws SQLException {
        ps.setObject(1, item.getManufacturedDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")),
                MysqlType.DATETIME);
        ps.setLong(2, item.getCarModel().getId());
        ps.setLong(3, item.getClientOrder().getId());
        ps.setLong(4, item.getPaintColor().getId());
    }
}