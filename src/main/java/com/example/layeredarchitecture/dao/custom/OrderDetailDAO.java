package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDvo;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDvo {
    boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
    // public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
