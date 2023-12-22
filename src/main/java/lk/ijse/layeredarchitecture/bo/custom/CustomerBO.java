package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.bo.custom.boImpl.SuperBo;
import lk.ijse.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBo {
     ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    void deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateNewCustomerId()throws SQLException, ClassNotFoundException;;

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
}
