package com.solvd.carfactory.dao.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface IResultParser <T> {
    T parse(ResultSet rs) throws SQLException;
}
