package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IPartTypeDAO;
import com.solvd.carfactory.models.supply.PartType;
import com.solvd.carfactory.models.supply.Provider;
import org.apache.log4j.Logger;

import java.sql.*;

public class PartTypeDAO extends AbstractMysqlJdbcDAO<PartType> implements IPartTypeDAO {
    private final static Logger LOGGER = Logger.getLogger(PartTypeDAO.class);
    private final static String CREATE_PARTTYPE_FROM_OBJECT = "INSERT INTO "
        + "part_types (name, type, provider_id) "
        + "VALUES (?), (?), (?)";
    private final static String GET_PARTTYPE_BY_ID = "SELECT * FROM part_types WHERE id = ?";
    private final static String UPDATE_PARTTYPE = "UPDATE part_types SET "
        + "name = ?, type = ?, provider_id = ? WHERE id = ?";
    private final static String DELETE_PARTTYPE = "DELETE FROM part_types WHERE id = ?";

    @Override
    public void createItem(PartType item) {
        item.setId(createItem(item, CREATE_PARTTYPE_FROM_OBJECT));
    }
                
    @Override
    public PartType getItemById(long id) {
        return getItemById(id, GET_PARTTYPE_BY_ID);
    }

    @Override
    public void updateItem(PartType item) {
        updateItem(item, UPDATE_PARTTYPE, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_PARTTYPE);
    }

    @Override
    protected PartType buildItem(ResultSet rs) throws SQLException{
        PartType partType = new PartType();

        partType.setId(rs.getLong("id"));
        partType.setName(rs.getString("name"));
        partType.setType(rs.getString("type"));
        partType.setProvider(new Provider(rs.getLong("provider_id")));

        return partType;
    }

    @Override
    protected void setPsParameters(PartType item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setString(2, item.getType());
        ps.setLong(3, item.getProvider().getId());
    }
}