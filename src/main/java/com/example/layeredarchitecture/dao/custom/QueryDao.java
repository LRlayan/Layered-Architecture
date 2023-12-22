package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SuperDao;

import java.sql.SQLException;
import java.util.List;


public interface QueryDao<T> extends SuperDao {
    List<T> viewOrderDetail() throws SQLException, ClassNotFoundException;

}
