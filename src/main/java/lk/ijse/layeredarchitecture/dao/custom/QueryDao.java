package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.SuperDao;

import java.sql.SQLException;
import java.util.List;


public interface QueryDao<T> extends SuperDao {
    List<T> viewOrderDetail() throws SQLException, ClassNotFoundException;

}
