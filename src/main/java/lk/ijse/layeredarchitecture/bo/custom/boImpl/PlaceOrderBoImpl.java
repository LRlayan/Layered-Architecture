package lk.ijse.layeredarchitecture.bo.custom.boImpl;

import lk.ijse.layeredarchitecture.bo.custom.PlaceOrderBo;
import lk.ijse.layeredarchitecture.dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.dao.custom.OrderDAO;
import lk.ijse.layeredarchitecture.dao.custom.OrderDetailDAO;
import lk.ijse.layeredarchitecture.db.DBConnection;
import lk.ijse.layeredarchitecture.entity.Customer;
import lk.ijse.layeredarchitecture.entity.Item;
import lk.ijse.layeredarchitecture.entity.Order;
import lk.ijse.layeredarchitecture.entity.OrderDetail;
import lk.ijse.layeredarchitecture.model.CustomerDTO;
import lk.ijse.layeredarchitecture.model.ItemDTO;
import lk.ijse.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.model.OrderDTO;
import lk.ijse.layeredarchitecture.dao.custom.*;

import java.time.LocalDate;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBo {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    QueryDao queryDAO= (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        connection= DBConnection.getDbConnection().getConnection();
        //Check order id already exist or not
        boolean b1 = orderDAO.exist(orderId);
        /*if order id already exist*/
        if (b1) {
            return false;
        }
        connection.setAutoCommit(false);
        //Save the Order to the order table
        boolean b2 = orderDAO.save(new Order(orderId, orderDate, customerId));
        if (!b2) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        // add data to the Order Details table
        for (OrderDetailDTO detail : orderDetails) {
            boolean b3 = orderDetailsDAO.save(new OrderDetail(detail.getOid(),detail.getItemCode(),detail.getQty(),detail.getUnitPrice()));
            if (!b3) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            //Search & Update Item
            ItemDTO itemDTO = findItem(detail.getItemCode());
            itemDTO.setQtyOnHand(itemDTO.getQtyOnHand() - detail.getQty());
            //update item
            boolean b = itemDAO.update(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));

            if (!b) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public ItemDTO findItem(String code) {
        try {

            Item itemCode = itemDAO.search(code);
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setCode(String.valueOf(itemCode));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {

        Item item = itemDAO.search(id);

        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allItems = customerDAO.getAll();
        ArrayList<CustomerDTO> getAllCustomers = new ArrayList<>();

        for (Customer customer : allItems) {
            getAllCustomers.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return getAllCustomers;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItem = itemDAO.getAll();
        ArrayList<ItemDTO> getAllItems = new ArrayList<>();

        for (Item items : allItem) {
            getAllItems.add(new ItemDTO(items.getCode(),items.getDescription(),items.getUnitPrice(),items.getQtyOnHand()));
        }
        return getAllItems;
    }


}
