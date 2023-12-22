package com.example.layeredarchitecture.bo.custom.boImpl;

import com.example.layeredarchitecture.bo.custom.BoFactory;
import com.example.layeredarchitecture.bo.custom.QuaryBo;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.QueryDao;
import com.example.layeredarchitecture.dao.custom.impl.QueryDaoImpl;
import com.example.layeredarchitecture.model.ViewOrderDTO;

import java.sql.SQLException;
import java.util.List;

public class QuaryBoImpl implements QuaryBo {

    QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<ViewOrderDTO> viewOrderDetail() throws SQLException, ClassNotFoundException {
        return queryDao.viewOrderDetail();
    }
}
