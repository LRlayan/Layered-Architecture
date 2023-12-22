package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SQLUtil;
import lk.ijse.layeredarchitecture.dao.custom.QueryDao;
import lk.ijse.layeredarchitecture.model.ViewOrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {
    @Override
    public List<ViewOrderDTO> viewOrderDetail() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.executeQuery("SELECT c.id , c.name , od.oid , od.itemCode , od.qty from customer c join Orders O on c.id = O.customerID join OrderDetails OD on O.oid = OD.oid;");

        ArrayList<ViewOrderDTO> getAll = new ArrayList<>();

        while (resultSet.next()){
            ViewOrderDTO queryDTO = new ViewOrderDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
            getAll.add(queryDTO);
        }
        return getAll;
    }
}
