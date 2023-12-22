package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.custom.boImpl.SuperBo;
import com.example.layeredarchitecture.model.ViewOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface QuaryBo extends SuperBo {
    List<ViewOrderDTO> viewOrderDetail() throws SQLException, ClassNotFoundException;

}
