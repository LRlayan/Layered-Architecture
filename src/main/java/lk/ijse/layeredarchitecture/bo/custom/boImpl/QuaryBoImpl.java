package lk.ijse.layeredarchitecture.bo.custom.boImpl;

import lk.ijse.layeredarchitecture.bo.custom.QuaryBo;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.dao.custom.QueryDao;
import lk.ijse.layeredarchitecture.model.ViewOrderDTO;

import java.sql.SQLException;
import java.util.List;

public class QuaryBoImpl implements QuaryBo {

    QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<ViewOrderDTO> viewOrderDetail() throws SQLException, ClassNotFoundException {
        return queryDao.viewOrderDetail();
    }
}
