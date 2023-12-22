package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.bo.custom.boImpl.SuperBo;
import lk.ijse.layeredarchitecture.model.ViewOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface QuaryBo extends SuperBo {
    List<ViewOrderDTO> viewOrderDetail() throws SQLException, ClassNotFoundException;

}
