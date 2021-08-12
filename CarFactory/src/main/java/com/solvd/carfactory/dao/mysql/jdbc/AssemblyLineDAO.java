package com.solvd.carfactory.dao.mysql.jdbc;

import com.solvd.carfactory.dao.IAssemblyLineDAO;
import com.solvd.carfactory.models.assemblyline.AssemblyLine;
import org.apache.log4j.Logger;

import java.sql.*;

public class AssemblyLineDAO extends AbstractMysqlJdbcDAO<AssemblyLine> implements IAssemblyLineDAO {
    private final static Logger LOGGER = Logger.getLogger(AssemblyLineDAO.class);
    private final static String CREATE_ASSEMBLYLINE_FROM_OBJECT = "INSERT INTO "
        + "assembly_lines (name) "
        + "VALUES (?)";
    private final static String GET_ASSEMBLYLINE_BY_ID = "SELECT * FROM assembly_lines WHERE id = ?";
    private final static String UPDATE_ASSEMBLYLINE = "UPDATE assembly_lines SET "
        + "name = ? WHERE id = ?";
    private final static String DELETE_ASSEMBLYLINE = "DELETE FROM assembly_lines WHERE id = ?";

    @Override
    public void createItem(AssemblyLine item) {
        item.setId(createItem(item, CREATE_ASSEMBLYLINE_FROM_OBJECT));
    }
                
    @Override
    public AssemblyLine getItemById(long id) {
        return getItemById(id, GET_ASSEMBLYLINE_BY_ID);
    }

    @Override
    public void updateItem(AssemblyLine item) {
        updateItem(item, UPDATE_ASSEMBLYLINE, item.getId());
    }

    @Override
    public void deleteItem(long id) {
        deleteItem(id, DELETE_ASSEMBLYLINE);
    }

    @Override
    protected AssemblyLine buildItem(ResultSet rs) throws SQLException{
        AssemblyLine assemblyLine = new AssemblyLine();

        assemblyLine.setId(rs.getLong("id"));
        assemblyLine.setName(rs.getString("name"));

        return assemblyLine;
    }

    @Override
    protected void setPsParameters(AssemblyLine item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
    }
}