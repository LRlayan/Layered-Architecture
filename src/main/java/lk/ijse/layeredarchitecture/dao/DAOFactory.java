package lk.ijse.layeredarchitecture.dao;

import lk.ijse.layeredarchitecture.dao.custom.impl.*;

//factory design pattern

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL,QUERY

    }

    public SuperDao getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();

            case ITEM:
                return new ItemDAOImpl();

            case ORDER:
                return new OrderDAOImpl();

            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();

            case QUERY:
                return new QueryDaoImpl();
            default:
                return null;
        }
    }
}
