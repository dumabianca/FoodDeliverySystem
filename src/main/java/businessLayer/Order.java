package businessLayer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private Date orderDate;
    public Order()
    {

    }
    public Order(int clientID, Date orderDate)
    {
        this.orderID=(int)Math.floor(Math.random()*(10000-2+1)+2);
        this.clientID=clientID;
        this.orderDate=orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID && clientID == order.clientID && Objects.equals(orderDate, order.orderDate);
    }
    /**
     * Method  used  to compute the hash value within the Map from the attributes of the businessLayer.Order
     * @return int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderID, clientID,orderDate);
    }

    @Override
    public String toString() {
        return "businessLayer.Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", orderDate=" + orderDate +
                '}';
    }
}
