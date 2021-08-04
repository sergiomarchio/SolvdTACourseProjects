package com.solvd.carfactory.dao;

public interface IBaseDAO<T> {
    void createItem(T item);
    T getItemById(long id);
    void updateItem(T item);
    void deleteItem(long id);
}
