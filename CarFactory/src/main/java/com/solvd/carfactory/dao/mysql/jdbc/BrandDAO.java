package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IBrandDAO;
import com.solvd.carfactory.models.car.Brand;
import org.apache.log4j.Logger;

import java.sql.*;

public class BrandDAO extends AbstractMysqlJdbcDAO<Brand> implements IBrandDAO {
    private final static Logger LOGGER = Logger.getLogger(BrandDAO.class);
    private final static String CREATE_BRAND_FROM_OBJECT = "INSERT INTO "
        + "brands (name) "
        + "VALUES (?)";
    private final static String GET_BRAND_BY_ID = "SELECT * FROM brands WHERE id = ?";
    private final static String UPDATE_BRAND = "UPDATE brands SET "
        + "name = ? WHERE id = ?";
    private final static String DELETE_BRAND = "DELETE FROM brands WHERE id = ?";

    @Override
    public void createItem(Brand item) {
        item.setId(createItem(item, CREATE_BRAND_FROM_OBJECT));
    }
                
    @Override
    public Brand getItemById(long id) {
        return getItemById(id, GET_BRAND_BY_ID);
    }

    @Override
    public void updateItem(Brand item) {
        updateItem(item, UPDATE_BRAND, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_BRAND);
    }

    @Override
    protected Brand buildItem(ResultSet rs) throws SQLException{
        Brand brand = new Brand();

        brand.setId(rs.getLong("id"));
        brand.setName(rs.getString("name"));

        return brand;
    }

    @Override
    protected void setPsParameters(Brand item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
    }
}