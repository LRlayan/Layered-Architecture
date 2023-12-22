package lk.ijse.layeredarchitecture.view.tdm;


public class ViewOrderTm {
    private String customerId;
    private String customerName;
    private String orderId;
    private String itemCode;
    private int qty;

    public ViewOrderTm(String customerId, String customerName, String orderId, String itemCode, int qty) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.qty = qty;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "QueryTm{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                '}';
    }
}
