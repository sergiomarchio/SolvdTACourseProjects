package com.solvd.carfactory.dao.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface IBuildItem<T> {
    T buildItem(ResultSet rs) throws SQLException;
}
