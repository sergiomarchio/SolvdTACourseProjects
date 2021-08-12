package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IModelPartDAO;
import com.solvd.carfactory.models.car.CarModel;
import com.solvd.carfactory.models.car.ModelPart;
import com.solvd.carfactory.models.supply.PartType;
import org.apache.log4j.Logger;

import java.sql.*;

public class ModelPartDAO extends AbstractMysqlJdbcDAO<ModelPart> implements IModelPartDAO {
    private final static Logger LOGGER = Logger.getLogger(ModelPartDAO.class);
    private final static String CREATE_MODELPART_FROM_OBJECT = "INSERT INTO "
        + "model_parts (car_model_id, part_type_id, count) "
        + "VALUES (?), (?), (?)";
    private final static String GET_MODELPART_BY_ID = "SELECT * FROM model_parts WHERE id = ?";
    private final static String UPDATE_MODELPART = "UPDATE model_parts SET "
        + "car_model_id = ?, part_type_id = ?, count = ? WHERE id = ?";
    private final static String DELETE_MODELPART = "DELETE FROM model_parts WHERE id = ?";

    @Override
    public void createItem(ModelPart item) {
        item.setId(createItem(item, CREATE_MODELPART_FROM_OBJECT));
    }
                
    @Override
    public ModelPart getItemById(long id) {
        return getItemById(id, GET_MODELPART_BY_ID);
    }

    @Override
    public void updateItem(ModelPart item) {
        updateItem(item, UPDATE_MODELPART, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_MODELPART);
    }

    @Override
    protected ModelPart buildItem(ResultSet rs) throws SQLException{
        ModelPart modelPart = new ModelPart();

        modelPart.setId(rs.getLong("id"));
        modelPart.setCarModel(new CarModel(rs.getLong("car_model_id")));
        modelPart.setPartType(new PartType(rs.getLong("part_type_id")));
        modelPart.setCount(rs.getInt("count"));

        return modelPart;
    }

    @Override
    protected void setPsParameters(ModelPart item, PreparedStatement ps) throws SQLException {
        ps.setLong(1, item.getCarModel().getId());
        ps.setLong(2, item.getPartType().getId());
        ps.setLong(3, item.getCount());
    }
}