package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.CrudDvo;
import lk.ijse.layeredarchitecture.entity.OrderDetail;
import lk.ijse.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDvo {
    boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException;
    // public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
