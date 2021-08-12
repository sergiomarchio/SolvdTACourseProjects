package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IPaintColorDAO;
import com.solvd.carfactory.models.supply.PaintColor;
import com.solvd.carfactory.models.supply.Provider;
import org.apache.log4j.Logger;

import java.sql.*;

public class PaintColorDAO extends AbstractMysqlJdbcDAO<PaintColor> implements IPaintColorDAO {
    private final static Logger LOGGER = Logger.getLogger(PaintColorDAO.class);
    private final static String CREATE_PAINTCOLOR_FROM_OBJECT = "INSERT INTO "
        + "paint_colors (name, provider_id) "
        + "VALUES (?), (?)";
    private final static String GET_PAINTCOLOR_BY_ID = "SELECT * FROM paint_colors WHERE id = ?";
    private final static String UPDATE_PAINTCOLOR = "UPDATE paint_colors SET "
        + "name = ?, provider_id = ? WHERE id = ?";
    private final static String DELETE_PAINTCOLOR = "DELETE FROM paint_colors WHERE id = ?";

    @Override
    public void createItem(PaintColor item) {
        item.setId(createItem(item, CREATE_PAINTCOLOR_FROM_OBJECT));
    }
                
    @Override
    public PaintColor getItemById(long id) {
        return getItemById(id, GET_PAINTCOLOR_BY_ID);
    }

    @Override
    public void updateItem(PaintColor item) {
        updateItem(item, UPDATE_PAINTCOLOR, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_PAINTCOLOR);
    }

    @Override
    protected PaintColor buildItem(ResultSet rs) throws SQLException{
        PaintColor paintColor = new PaintColor();

        paintColor.setId(rs.getLong("id"));
        paintColor.setName(rs.getString("name"));
        paintColor.setProvider(new Provider(rs.getLong("provider_id")));

        return paintColor;
    }

    @Override
    protected void setPsParameters(PaintColor item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setLong(2, item.getProvider().getId());
    }
}