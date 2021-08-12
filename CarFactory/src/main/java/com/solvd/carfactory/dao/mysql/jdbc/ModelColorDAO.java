package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IModelColorDAO;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.models.car.ModelColor;
import com.solvd.carfactory.models.supply.PaintColor;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModelColorDAO extends AbstractMysqlJdbcDAO<ModelColor> implements IModelColorDAO {
    private final static Logger LOGGER = Logger.getLogger(ModelColorDAO.class);
    private final static String CREATE_MODELCOLOR_FROM_OBJECT = "INSERT INTO "
            + "model_colors (paint_color_id, car_model_id) "
            + "VALUES (?), (?)";
    private final static String GET_MODELCOLOR_BY_ID = "SELECT * FROM model_colors WHERE id = ?";
    private final static String UPDATE_MODELCOLOR = "UPDATE model_colors SET "
            + "paint_color_id = ?, car_model_id = ? WHERE id = ?";
    private final static String DELETE_MODELCOLOR = "DELETE FROM model_colors WHERE id = ?";

    private final static String GET_PAINTCOLORS_BY_CARMODEL_ID = "SELECT paint_color_id from model_colors "
            + "WHERE car_model_id = ?";

    @Override
    public void createItem(ModelColor item) {
        item.setId(createItem(item, CREATE_MODELCOLOR_FROM_OBJECT));
    }

    @Override
    public ModelColor getItemById(long id) {
        return getItemById(id, GET_MODELCOLOR_BY_ID);
    }

    @Override
    public void updateItem(ModelColor item) {
        updateItem(item, UPDATE_MODELCOLOR, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_MODELCOLOR);
    }

    @Override
    protected ModelColor buildItem(ResultSet rs) throws SQLException {
        ModelColor modelColor = new ModelColor();

        modelColor.setId(rs.getLong("id"));
        modelColor.setPaintColor(new PaintColor(rs.getLong("paint_color_id")));
        modelColor.setCarModel(new CarModel(rs.getLong("car_model_id")));

        return modelColor;
    }

    @Override
    protected void setPsParameters(ModelColor item, PreparedStatement ps) throws SQLException {
        ps.setLong(1, item.getPaintColor().getId());
        ps.setLong(2, item.getCarModel().getId());
    }

    @Override
    public List<PaintColor> getPaintColorsByModelId(long id) {
        return getResultsOfSelect(GET_PAINTCOLORS_BY_CARMODEL_ID,
                ps -> ps.setLong(1, id),
                rs -> {
                    List<PaintColor> paintColors = new LinkedList<>();

                    while (rs.next()) {
                        PaintColor paintColor = new PaintColor(rs.getLong("paint_color_id"));
                        paintColors.add(paintColor);
                    }

                    return paintColors;
                });
    }
}