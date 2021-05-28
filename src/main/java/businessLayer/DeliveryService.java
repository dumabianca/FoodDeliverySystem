package businessLayer;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Duma Bianca
 * @Since: May 18, 2021
 * Main class containing essential methods such as adding a new user, menu item or a new businessLayer.Order
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<Order, ArrayList<MenuItem>>();
    /**
     * This is the constructor of the class
     * @throws IOException,ClassNotFoundException On input error.
     * @see IOException,ClassNotFoundException
     */
    public DeliveryService() throws IOException, ClassNotFoundException {
        this.users = this.usersDeserialization();
        this.menuItems = this.menuItemsDeserialization();
        this.orders = this.ordersDeserialization(); }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User newUser) {
        assert newUser != null : "The new user cannot be null!";
        int oldSize = usersSize();
        newUser.setClientId((int) Math.floor(Math.random() * (10000 - 2 + 1) + 2));
        this.users.add(newUser);
        this.usersSerialization(this.users);
        assert oldSize == usersSize() - 1 : "The new size must be the old size + 1!"; }

    public void addMenuItem(MenuItem newMenuItem) {
        assert newMenuItem.getPrice() > 0.0f : "Price must be positive!";
        assert newMenuItem != null : "The new item cannnot be null!";
        int oldSize = menuItemsSize();
        this.menuItems.add(newMenuItem);
        this.menuItemsSerialization(this.menuItems);
        assert oldSize == menuItemsSize() - 1 : "The new size must be the old size + 1!"; }

    public void deleteMenuItem(MenuItem newMenuItem) {
        assert !emptyMenuItemsList() : " The list is empty! ";
        assert menuItemsSize() >= 2 : " The size must be at least 2! ";
        int oldSize = menuItems.size();
        this.menuItems.remove(newMenuItem);
        this.menuItemsSerialization(this.menuItems);
        assert !emptyMenuItemsList() : " The list is empty! ";
        assert oldSize == menuItems.size() + 1 : " The old size must be the new size + 1 !"; }

    public static ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
    /**
     * This is the method implementing serialization which is necessary for data persistence
     * @param usersList
     */
    public void usersSerialization(ArrayList<User> usersList) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("users.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            oos.writeObject(usersList);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace(); } }
    /**
     * This method is called everytime a new businessLayer.DeliveryService instance is created
     * @return ArrayList<businessLayer.User>.
     */
    public ArrayList<User> usersDeserialization() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("users.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace(); }
        ArrayList<User> usersSerialized = null;
        try {
            usersSerialized = (ArrayList<User>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersSerialized; }
    public ArrayList<MenuItem> menuItemsDeserialization() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("menuItems.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace(); }
        ArrayList<MenuItem> menuItemsSerialized = null;
        try {
            menuItemsSerialized = (ArrayList<MenuItem>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        return menuItemsSerialized; }

    public void menuItemsSerialization(ArrayList<MenuItem> menuList) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("menuItems.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            oos.writeObject(menuList);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } }

    public void readCSV() throws IOException {
        String row = new String();
        HashSet<BaseProduct> productsList = new HashSet<BaseProduct>();
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader("products.csv"));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace(); }
        try {
            row = csvReader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace(); }
        while (true) {
            try {
                if (!((row = csvReader.readLine()) != null)) break;
            } catch (IOException ioException) {
                ioException.printStackTrace(); }
            String[] data = row.split(",");
            BaseProduct product = new BaseProduct(data[0], Double.parseDouble(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
            productsList.add(product); }
        ArrayList<MenuItem> menuList = new ArrayList<MenuItem>(productsList);
        this.menuItems.addAll(menuList);
        this.menuItemsSerialization(this.menuItems); }

    public void readCsv() throws IOException {

        String fileName="products.csv";
        List<BaseProduct> importedProducts=new ArrayList<BaseProduct>();
        Stream<String> rows = Files.lines(Paths.get(fileName));
        importedProducts=rows.skip(1).distinct().map(line->{
            List<String> sir =Arrays.asList(line.split(","));
            return new BaseProduct(sir.get(0),Double.parseDouble(sir.get(1)),Integer.parseInt(sir.get(2)),Integer.parseInt(sir.get(3)),Integer.parseInt(sir.get(4)),Integer.parseInt(sir.get(5)),Integer.parseInt(sir.get(6)));
        }).collect(Collectors.toList());
        this.menuItems.addAll(importedProducts);
        this.menuItemsSerialization(this.menuItems);
        HashSet<BaseProduct> noDuplicates=new HashSet<BaseProduct>(importedProducts);
        System.out.println(noDuplicates.size());
        this.menuItems.addAll(noDuplicates);
        this.menuItemsSerialization(this.menuItems);
        // System.out.println(importedProducts.size());
    }
    public void addOrder(Order order, ArrayList<MenuItem> menuItemsOrder) {
        assert order != null : " The new order can't be null!";
        assert menuItems.size() >= 1 : " You need to add at least one item!";
        int oldSize = hashMapSize();
        this.orders.put(order, menuItemsOrder);
        this.ordersSerialization(this.orders);
        setChanged();
        String s = new String("");
        List<String> names = new ArrayList<String>();
        names = menuItemsOrder.stream().map(e -> e.getTitle()).collect(Collectors.toList());
        s = s + "businessLayer.Order id:" + order.getOrderID() + " Client id:" + order.getClientID() + "\n" + "order items:" + names + "\n";
        notifyObservers(s);
        assert oldSize == hashMapSize() - 1 : " The new size must be the old size + 1 !"; }
    public HashMap<Order, ArrayList<MenuItem>> ordersDeserialization() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("orders.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace(); }
        HashMap<Order, ArrayList<MenuItem>> ordersSerialized = null;
        try {
            ordersSerialized = (HashMap<Order, ArrayList<MenuItem>>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        return ordersSerialized; }

    public void ordersSerialization(HashMap<Order, ArrayList<MenuItem>> orders) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("orders.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace(); } }

    public void generateBill(User user, ArrayList<MenuItem> items, int orderId) {
        int sum = 0;
        for (MenuItem m : items)
            sum += m.price;
        String s = new String("");
        s = s + "Name:" + user.getUsername() + "\n" + "Client id:" + user.getClientId() + "\n" + "businessLayer.Order items:" + items + "\n" + "Total price:" + sum;
        try {
            FileWriter fw = new FileWriter("C:\\Users\\duma_\\Desktop\\AN2-facultate\\sem2\\TP\\PT2021_30226_Duma_Bianca_Assignment_4\\bill" + orderId + ".txt");
            fw.write(s);
            fw.close();
        } catch (Exception e) {
            System.out.println(e); } }

    public boolean emptyMenuItemsList() {
        return menuItems.size() == 0;
    }
    public int menuItemsSize() {
        return menuItems.size();
    }
    public int usersSize() {
        return users.size();
    }
    public int hashMapSize() {
        return orders.size();
    }

    public ArrayList<Order> generateRaportIntervalOrder(int a, int b) {
        Set<Order> ordersInterval = new HashSet<Order>();
        ordersInterval = orders.keySet();
        System.out.println(ordersInterval);
        ArrayList<Order> targetList = new ArrayList<Order>(ordersInterval);
        ArrayList<Order> filteredOrders = (ArrayList<Order>) targetList.stream().
                filter(c -> c.getOrderDate().getHours() >= a)
                .filter(c -> c.getOrderDate().getHours() <= b).
                        collect(Collectors.toList());
        return filteredOrders; }

    public ArrayList<MenuItem> generateProductsRaport(int a) {
        ArrayList<MenuItem> products = new ArrayList<MenuItem>();
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            products.addAll(entry.getValue()); }
        return (ArrayList<MenuItem>) products.stream().
                filter(t -> Collections.frequency(products, t) >= a).distinct().
                collect(Collectors.toList()); }

    public ArrayList<Integer> generateClientRaport(int a, int b) {
        ArrayList<Integer> clients = new ArrayList<Integer>();
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            clients.add(entry.getKey().getClientID()); }
        System.out.println(clients);
        ArrayList<Integer> smth = (ArrayList<Integer>) clients.stream().filter(t -> Collections.frequency(clients, t) >= a)
                .distinct().collect(Collectors.toList());
        return smth; }

    public Map<MenuItem, Integer> generateDateReport(int date) {
        List<MenuItem> products = new ArrayList<>();
        orders.keySet().stream().
                filter(order -> order.getOrderDate().getDay() == date).map(order -> orders.get(order)).
                forEach(products::addAll);
        Map<MenuItem, Integer> toReturn = new HashMap<>();
        products.stream().forEach(menuItem -> toReturn.put(menuItem, toReturn.get(menuItem) == null ? 1 : toReturn.get(menuItem) + 1));
        Set<MenuItem> productsSet = new HashSet<>();
        productsSet.addAll(products);
        return toReturn; }}
