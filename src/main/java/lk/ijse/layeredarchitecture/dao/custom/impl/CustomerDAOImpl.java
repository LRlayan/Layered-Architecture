package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SQLUtil;
import lk.ijse.layeredarchitecture.dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
       // Statement stm = connection.createStatement();
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            Customer entity= new Customer(rst.getString("id"),
                    rst.getString("name"), rst.getString("address"));
            getAllCustomer.add(entity);
        }
        return getAllCustomer;
    }
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
//            Connection connection = DBConnection.getDbConnection().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
//            pstm.setString(1, dto.getId());
//            pstm.setString(2, dto.getName());
//            pstm.setString(3, dto.getAddress());
//            return pstm.executeUpdate()> 0;
       return SQLUtil.executeQuery("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getAddress());

    }
    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
//       Connection connection = DBConnection.getDbConnection().getConnection();
//       PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
//       pstm.setString(1,dto.getName());
//       pstm.setString(2, dto.getAddress());
//       pstm.setString(3, dto.getId());
//       return pstm.executeUpdate()> 0;

        return SQLUtil.executeQuery("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());

   }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
//        pstm.setString(1,id);
//        return pstm.executeUpdate()>0;

        return SQLUtil.executeQuery("DELETE FROM Customer WHERE id=?",id);
    }
    @Override
    public  String generateNewId() throws SQLException, ClassNotFoundException {
      //  Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id);
        return resultSet.next();
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?",id);
        resultSet.next();
        return new Customer(id + "", resultSet.getString("name"), resultSet.getString("address"));
    }

//    @Override
//    public boolean exist(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
//        pstm.setString(1, id);
//        return pstm.executeQuery().next();
//
//        ResultSet resultSet = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id);
//        return resultSet.next();
//    }
//    @Override
//    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
//        pstm.setString(1, id + "");
//        ResultSet rst = pstm.executeQuery();
//        rst.next();
//        return new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));
//        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?",id);
//        resultSet.next();
//        return new CustomerDTO(id + "", resultSet.getString("name"), resultSet.getString("address"));
    //}

}
