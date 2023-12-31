package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SQLUtil;
import lk.ijse.layeredarchitecture.dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.entity.Item;
import lk.ijse.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public  ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        //Connection connection = DBConnection.getDbConnection().getConnection();
        //Statement stm = connection.createStatement();

        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> getAllItem = new ArrayList<>();
        while (rst.next()) {
           Item entity=new Item(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4));
           getAllItem.add(entity);
        }
        return getAllItem;
    }


    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
//        pstm.setString(1, itemDTO.getCode());
//        pstm.setString(2, itemDTO.getDescription());
//        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
//        pstm.setInt(4, itemDTO.getQtyOnHand());
//        return pstm.executeUpdate() > 0;
          return SQLUtil.executeQuery("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
//        pstm.setString(1, code);
//       return pstm.executeUpdate() >0;
        return SQLUtil.executeQuery("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
//        preparedStatement.setString(1, itemDTO.getDescription());
//        preparedStatement.setBigDecimal(2, itemDTO.getUnitPrice());
//        preparedStatement.setInt(3, itemDTO.getQtyOnHand());
//        preparedStatement.setString(4, itemDTO.getCode());
//        return preparedStatement.executeUpdate() >0;
        return SQLUtil.executeQuery("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
//        pstm.setString(1, code);
//        return pstm.executeQuery().next();
        ResultSet resultSet = SQLUtil.executeQuery("SELECT code FROM Item WHERE code=?",code);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
       // Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
//        pstm.setString(1, code + "");
//        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?",code);
//        rst.next();
//        return new ItemDTO(code + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?",code);
        resultSet.next();
        return new Item(code + "", resultSet.getString("description"), resultSet.getBigDecimal("unitPrice"), resultSet.getInt("qtyOnHand"));
    }
}
