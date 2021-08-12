package com.solvd.carfactory.dao.mysql.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface IPrepareStatement {
    void prepare(PreparedStatement ps) throws SQLException;
}
