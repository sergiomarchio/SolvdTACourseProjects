package com.solvd.carfactory.dao.mysql.jdbc;

import com.mysql.cj.MysqlType;
import com.solvd.carfactory.dao.ICarModelDAO;
import com.solvd.carfactory.models.car.Brand;
import com.solvd.carfactory.models.car.CarModel;

import java.time.LocalDate;
import java.time.Year;
import org.apache.log4j.Logger;

import java.sql.*;

public class CarModelDAO extends AbstractMysqlJdbcDAO<CarModel> implements ICarModelDAO {
    private final static Logger LOGGER = Logger.getLogger(CarModelDAO.class);
    private final static String CREATE_CARMODEL_FROM_OBJECT = "INSERT INTO "
        + "car_models (name, type, year, fuel_type, unitary_price, brand_id) "
        + "VALUES (?), (?), (?), (?), (?), (?)";
    private final static String GET_CARMODEL_BY_ID = "SELECT * FROM car_models WHERE id = ?";
    private final static String UPDATE_CARMODEL = "UPDATE car_models SET "
        + "name = ?, type = ?, year = ?, fuel_type = ?, unitary_price = ?, brand_id = ? WHERE id = ?";
    private final static String DELETE_CARMODEL = "DELETE FROM car_models WHERE id = ?";

    @Override
    public void createItem(CarModel item) {
        item.setId(createItem(item, CREATE_CARMODEL_FROM_OBJECT));
    }
                
    @Override
    public CarModel getItemById(long id) {
        return getItemById(id, GET_CARMODEL_BY_ID);
    }

    @Override
    public void updateItem(CarModel item) {
        updateItem(item, UPDATE_CARMODEL, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_CARMODEL);
    }

    @Override
    protected CarModel buildItem(ResultSet rs) throws SQLException{
        CarModel carModel = new CarModel();

        carModel.setId(rs.getLong("id"));
        carModel.setName(rs.getString("name"));
        carModel.setType(rs.getString("type"));
        carModel.setYear(Year.of(rs.getObject("year", LocalDate.class).getYear()));
        carModel.setFuelType(rs.getString("fuel_type"));
        carModel.setUnitaryPrice(rs.getDouble("unitary_price"));
        carModel.setBrand(new Brand(rs.getLong("brand_id")));

        return carModel;
    }

    @Override
    protected void setPsParameters(CarModel item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setString(2, item.getType());
        ps.setObject(3, item.getYear(), MysqlType.YEAR);
        ps.setString(4, item.getFuelType());
        ps.setDouble(5, item.getUnitaryPrice());
        ps.setLong(6, item.getBrand().getId());
    }

    @Override
    public CarModel getFullCarModelById(long id) {
        return null;
    }

    @Override
    public CarModel getNestedCarModelById(long id) {
        return null;
    }
}