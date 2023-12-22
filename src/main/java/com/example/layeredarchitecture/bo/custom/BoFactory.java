package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.custom.boImpl.*;
import com.example.layeredarchitecture.dao.SuperDao;


public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum DaoTypes{
        CUSTOMER,ITEM,PLACE_ORDER,QUERY
    }

    public SuperBo getDao(DaoTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerBoImpl();

            case ITEM:
                return new ItemBoImpl();

            case PLACE_ORDER:
                return new PlaceOrderBoImpl();

            case QUERY:
                return new QuaryBoImpl();
            default:
                return null;
        }
    }
}
