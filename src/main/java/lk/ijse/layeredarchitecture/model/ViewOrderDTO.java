package lk.ijse.layeredarchitecture.model;

public class ViewOrderDTO {
    private String customerId;
    private String customerName;
    private String orderId;
    private String itemCode;
    private int qty;

    public ViewOrderDTO(String customerId, String customerName, String orderId, String itemCode, int qty) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.qty = qty;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "QueryDTO{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                '}';
    }
}
