package businessLayer;

import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDeliveryServiceProcessing {


    public ArrayList<User> getUsers();
    /**
     *
     * @pre users!=null
     * @pre newUser!=null
     * @post usersSize() ==  usersSize()@pre + 1
     */
    public void addUser(User newUser);
    /**
     *
     * @pre menuItem!=null
     * @pre menuItem.getPrice() > 0.0f
     * @post menuItemsSize() ==  menuItemsSize()@pre + 1
     */
    public void addMenuItem(MenuItem newMenuItem);
    /**
     *
     * @inv emptyMenuItemsList()
     * @pre listSize() >= 2
     * @post listSize() == listSize()@pre - 1
     */
    public void deleteMenuItem(MenuItem newMenuItem);
    public void usersSerialization(ArrayList<User> usersList);
    public ArrayList<User> usersDeserialization() ;
    public ArrayList<MenuItem> menuItemsDeserialization();

    public void menuItemsSerialization(ArrayList<MenuItem> menuList);
    public void readCSV() throws IOException ;
    /**
     *
     * @pre order != null
     * @pre menuItemsOrder.size() >= 1
     * @post hashMapSize() == hashMapSize()@pre + 1
     */
    public void addOrder(Order order, ArrayList<MenuItem> menuItemsOrder);
    public HashMap<Order,ArrayList<MenuItem>> ordersDeserialization();

    public void ordersSerialization(HashMap<Order,ArrayList<MenuItem>> orders);
    public void generateBill(User user, ArrayList<MenuItem> items, int orderId);
}


