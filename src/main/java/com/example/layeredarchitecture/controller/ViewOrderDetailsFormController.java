package com.example.layeredarchitecture.controller;

import com.example.layeredarchitecture.bo.custom.BoFactory;
import com.example.layeredarchitecture.bo.custom.QuaryBo;
import com.example.layeredarchitecture.bo.custom.boImpl.QuaryBoImpl;
import com.example.layeredarchitecture.model.ViewOrderDTO;
import com.example.layeredarchitecture.view.tdm.ViewOrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrderDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colQuentity;

    @FXML
    private TableView<ViewOrderTm> viewOrderDetailTable;

    //QueryDao queryDao = new QueryDaoImpl();
    QuaryBo quaryBo = (QuaryBo) BoFactory.getBoFactory().getDao(BoFactory.DaoTypes.QUERY);

    ObservableList<ViewOrderTm> obList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setOrderDetails();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();

    }

    public void setCellValueFactory(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQuentity.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    public void setOrderDetails() throws SQLException, ClassNotFoundException {
        try{
            List<ViewOrderDTO> viewOrderDetails = quaryBo.viewOrderDetail();
            for (ViewOrderDTO dto : viewOrderDetails) {
                obList.add(new ViewOrderTm(
                        dto.getCustomerId(),
                        dto.getCustomerName(),
                        dto.getOrderId(),
                        dto.getItemCode(),
                        dto.getQty()
                ));
            }
            viewOrderDetailTable.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
