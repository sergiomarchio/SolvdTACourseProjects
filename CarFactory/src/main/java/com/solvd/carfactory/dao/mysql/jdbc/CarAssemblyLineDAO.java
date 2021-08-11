package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.ICarAssemblyLineDAO;
import com.solvd.carfactory.models.assemblyline.AssemblyLine;
import com.solvd.carfactory.models.assemblyline.CarAssemblyLine;
import com.solvd.carfactory.models.car.Car;
import org.apache.log4j.Logger;

import java.sql.*;

public class CarAssemblyLineDAO extends AbstractMysqlJdbcDAO<CarAssemblyLine> implements ICarAssemblyLineDAO {
    private final static Logger LOGGER = Logger.getLogger(CarAssemblyLineDAO.class);
    private final static String CREATE_CARASSEMBLYLINE_FROM_OBJECT = "INSERT INTO "
        + "car_assembly_lines (assembly_line_id, car_id) "
        + "VALUES (?), (?)";
    private final static String GET_CARASSEMBLYLINE_BY_ID = "SELECT * FROM car_assembly_lines WHERE id = ?";
    private final static String UPDATE_CARASSEMBLYLINE = "UPDATE car_assembly_lines SET "
        + "assembly_line_id = ?, car_id = ? WHERE id = ?";
    private final static String DELETE_CARASSEMBLYLINE = "DELETE FROM car_assembly_lines WHERE id = ?";

    @Override
    public void createItem(CarAssemblyLine item) {
        item.setId(createItem(item, CREATE_CARASSEMBLYLINE_FROM_OBJECT));
    }
                
    @Override
    public CarAssemblyLine getItemById(long id) {
        return getItemById(id, GET_CARASSEMBLYLINE_BY_ID);
    }

    @Override
    public void updateItem(CarAssemblyLine item) {
        updateItem(item, UPDATE_CARASSEMBLYLINE, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_CARASSEMBLYLINE);
    }

    @Override
    protected CarAssemblyLine buildItem(ResultSet rs) throws SQLException{
        CarAssemblyLine carAssemblyLine = new CarAssemblyLine();

        carAssemblyLine.setId(rs.getLong("id"));
        carAssemblyLine.setAssemblyLine(new AssemblyLine(rs.getLong("assembly_line_id")));
        carAssemblyLine.setCar(new Car(rs.getLong("car_id")));

        return carAssemblyLine;
    }

    @Override
    protected void setPsParameters(CarAssemblyLine item, PreparedStatement ps) throws SQLException {
        ps.setLong(1, item.getAssemblyLine().getId());
        ps.setLong(2, item.getCar().getId());
    }
}