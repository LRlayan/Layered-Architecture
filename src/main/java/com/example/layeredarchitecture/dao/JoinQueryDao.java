package com.example.layeredarchitecture.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface JoinQueryDao<T> {
    List<T> viewOrderDetail() throws SQLException, ClassNotFoundException;
}
