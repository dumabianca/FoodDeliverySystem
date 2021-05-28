package presentationLayer;
import businessLayer.*;
import presentationLayer.AdministratorFrame;
import presentationLayer.ClientFrame;
import presentationLayer.EmployeeFrame;
import presentationLayer.LoginFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
public class Controller {
    private LoginFrame loginFrame;
    private DeliveryService deliveryService;
    private AdministratorFrame administratorFrame;
    private ClientFrame clientFrame;
    private EmployeeFrame employeeFrame;
    public Controller(LoginFrame loginFrame, DeliveryService deliveryService,AdministratorFrame administratorFrame,EmployeeFrame employeeFrame) {
        this.loginFrame=loginFrame;
        this.deliveryService=deliveryService;
        this.administratorFrame=administratorFrame;
        this.employeeFrame=employeeFrame;
        this.loginFrame.addListenerRegister(new ActionListenerRegister());
        this.loginFrame.addListenerLogin(new ActionListenerLogin());
        this.administratorFrame.addListenerImportButton(new ActionListener() {
            @Override
            /**
             * Overidded method used  for importing the menuItems from the CSV file by calling readCSV() method
             * @param e Unused.
             * @return Nothing.
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    deliveryService.readCsv();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }}
        });
        this.administratorFrame.addListenerItemButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                administratorFrame.selectedItems.add((MenuItem) administratorFrame.getSelectedRow(administratorFrame.table,administratorFrame.model));
                administratorFrame.setTable(administratorFrame.secondModel,administratorFrame.secondTable);
                administratorFrame.secondModel.setData(administratorFrame.selectedItems);
            }
        });
        this.administratorFrame.addListenerCreateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> selectedItems=new ArrayList<MenuItem>();
                selectedItems=(ArrayList<MenuItem>) administratorFrame.secondModel.getData();
                MenuItem newMenuItem=new CompositeProduct(selectedItems);
                System.out.println(newMenuItem);
                ((CompositeProduct)newMenuItem).setTitle(administratorFrame.getNameCreatedItem());
                System.out.println(administratorFrame.getNameCreatedItem());
                deliveryService.addMenuItem(newMenuItem);
                administratorFrame.secondTable.repaint(); }
        });
        this.administratorFrame.addListenerAddButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=administratorFrame.getAddedName();
                double rating=Double.parseDouble(administratorFrame.getAddedRating());
                int price=Integer.parseInt(administratorFrame.getAddedPrice());
                int sodium=Integer.parseInt(administratorFrame.getAddedSodium());
                int fats=Integer.parseInt(administratorFrame.getAddedFats());
                int proteins=Integer.parseInt(administratorFrame.getAddedProteins());
                int calories=Integer.parseInt(administratorFrame.getAddedCalories());
                MenuItem newBaseProduct=new BaseProduct(name,rating,calories,proteins,fats,sodium,price);
                deliveryService.addMenuItem(newBaseProduct);
                administratorFrame.table.repaint(); }
        });
        this.administratorFrame.addListenerDeleteButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem toDelete=(MenuItem) administratorFrame.getSelectedRow(administratorFrame.table, administratorFrame.model);
                deliveryService.deleteMenuItem(toDelete);
                administratorFrame.table.repaint(); }
        });
        this.administratorFrame.addListenerViewCompositeButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem m=(MenuItem) administratorFrame.getSelectedRow(administratorFrame.table,administratorFrame.model);
                if(m instanceof CompositeProduct) {
                    administratorFrame.secondModel.setData(((CompositeProduct) m).getMenuItems());
                    administratorFrame.setTable(administratorFrame.secondModel,administratorFrame.secondTable);
                }}
        });
        this.administratorFrame.addListenerClearButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                administratorFrame.secondModel.setData(new ArrayList<MenuItem>());
                administratorFrame.secondTable.repaint(); }
        });
        this.administratorFrame.addListenerModifyButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fieldToModify=administratorFrame.getComboText(administratorFrame.comboBox);
                String modifiedvalue=administratorFrame.getModifiedValue();
                MenuItem menuItem=(MenuItem) administratorFrame.getSelectedRow(administratorFrame.table, administratorFrame.model);
                System.out.println(menuItem);
                if(fieldToModify.equals("name")) {
                    menuItem.setTitle(modifiedvalue); }
                else if(fieldToModify.equals("rating")) {
                    menuItem.setRating(Double.parseDouble(modifiedvalue)); }
                else if(fieldToModify.equals("price")) {
                    menuItem.setPrice(Integer.parseInt(modifiedvalue)); }
                else if(fieldToModify.equals("fats")) {
                    menuItem.setFats(Integer.parseInt(modifiedvalue)); }
                else if(fieldToModify.equals("proteins")) {
                    menuItem.setProteins(Integer.parseInt(modifiedvalue)); }
                else if(fieldToModify.equals("sodium")) {
                    menuItem.setSodium(Integer.parseInt(modifiedvalue)); }
                System.out.println(menuItem);
                deliveryService.menuItemsSerialization(DeliveryService.menuItems);
                administratorFrame.table.repaint(); }
        });
        this.administratorFrame.addListenerRaportButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=administratorFrame.getComboText(administratorFrame.comboRaports);
                int val1=administratorFrame.getValue1Report();
                int val2=administratorFrame.getValue2Report();
                if(s.equals("Raport1")){
                    FileWriter fw = null;
                    try {
                        fw = new FileWriter("C:\\Users\\duma_\\Desktop\\AN2-facultate\\sem2\\TP\\PT2021_30226_Duma_Bianca_Assignment_4\\raport1.txt");
                        fw.write(deliveryService.generateRaportIntervalOrder(val1,val2).toString());
                        fw.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace(); } }
                else if(s.equals("Raport2")) {
                    FileWriter fw = null;
                    try {
                        fw = new FileWriter("C:\\Users\\duma_\\Desktop\\AN2-facultate\\sem2\\TP\\PT2021_30226_Duma_Bianca_Assignment_4\\raport2.txt");
                        fw.write(deliveryService.generateProductsRaport(val1).toString());
                        fw.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace(); } }
                else if(s.equals("Raport3")) {
                    FileWriter fw = null;
                    try {
                        fw = new FileWriter("C:\\Users\\duma_\\Desktop\\AN2-facultate\\sem2\\TP\\PT2021_30226_Duma_Bianca_Assignment_4\\raport3.txt");
                        fw.write(deliveryService.generateClientRaport(val1,val2).toString());
                        fw.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace(); } }
                else {
                    FileWriter fw = null;
                    try {
                        fw = new FileWriter("C:\\Users\\duma_\\Desktop\\AN2-facultate\\sem2\\TP\\PT2021_30226_Duma_Bianca_Assignment_4\\raport4.txt");
                        fw.write(deliveryService.generateDateReport(val1).toString());
                        fw.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace(); }
                } }
        }); }
    public class ActionListenerFilterButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String filterItem= clientFrame.getComboBox();
            String name=new String("0");
            double field1=0;
            double field2=0;
            if(filterItem.equals("name"))
                name=clientFrame.getField1();
            else {
                field1=Double.parseDouble(clientFrame.getField1());
                field2=Double.parseDouble(clientFrame.getField2()); }
            final double interval1=field1;
            final double interval2=field2;
            if(!name.equals("0")){
                final String nameField=new String(name);
                List<MenuItem> filtered =DeliveryService.getMenuItems()
                        .stream()
                        .filter(c -> c.getTitle().contains(nameField))
                        .collect(Collectors.toList());
                clientFrame.setTable(clientFrame.model,clientFrame.table);
                clientFrame.model.setData(filtered);}
            else if(filterItem.equals("rating")) {
                List<MenuItem> filtered = DeliveryService.getMenuItems()
                        .stream()
                        .filter(c -> c.getRating()>=interval1).filter(c -> c.getRating()<=interval2  )
                        .collect(Collectors.toList());
                clientFrame.setTable(clientFrame.model,clientFrame.table);
                clientFrame.model.setData(filtered);}
            else if(filterItem.equals("calories")) {
                List<MenuItem> filtered =DeliveryService.getMenuItems()
                        .stream()
                        .filter(c -> c.getCalories()>=interval1).filter(c -> c.getCalories()<=interval2  )
                        .collect(Collectors.toList());
                clientFrame.setTable(clientFrame.model,clientFrame.table);
                clientFrame.model.setData(filtered);}
            else if(filterItem.equals("proteins")) {
                List<MenuItem> filtered =DeliveryService.getMenuItems()
                        .stream()
                        .filter(c -> c.getProteins()>=interval1).filter(c -> c.getProteins()<=interval2  )
                        .collect(Collectors.toList());
                clientFrame.setTable(clientFrame.model,clientFrame.table);
                clientFrame.model.setData(filtered);}
            else if(filterItem.equals("fats")) {
                List<MenuItem> filtered =DeliveryService.getMenuItems()
                        .stream()
                        .filter(c -> c.getFats()>=interval1).filter(c -> c.getFats()<=interval2  )
                        .collect(Collectors.toList());
                clientFrame.setTable(clientFrame.model,clientFrame.table);
                clientFrame.model.setData(filtered);}
            else if(filterItem.equals("sodium")) {
                List<MenuItem> filtered =DeliveryService.getMenuItems()
                        .stream()
                        .filter(c -> c.getSodium()>=interval1).filter(c -> c.getSodium()<=interval2  )
                        .collect(Collectors.toList());
                clientFrame.setTable(clientFrame.model,clientFrame.table);
                clientFrame.model.setData(filtered);}
            else if(filterItem.equals("price")) {
                List<MenuItem> filtered =DeliveryService.getMenuItems()
                        .stream()
                        .filter(c -> c.getPrice()>=interval1).filter(c -> c.getPrice()<=interval2  )
                        .collect(Collectors.toList());
                clientFrame.setTable(clientFrame.model,clientFrame.table);
                clientFrame.model.setData(filtered);} }}

    public void addClientlisteners(ClientFrame frame) {
        frame.addListenerAddItemButton(new ActionListenerAddItemButtonClient());
        frame.addListenerClearButton(new ActionListenerClearButtonClient());
        frame.addListenerPlaceOrderButton(new ActionListenerPlaceOrder());
        frame.addListenerFilterButton(new ActionListenerFilterButton());
        frame.addListenerRemoveFilterButton(new ActionListenerRemoveFilterButton());}

    public class ActionListenerRemoveFilterButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            clientFrame.model.setData(DeliveryService.menuItems);
            clientFrame.field1.setText("");
            clientFrame.field2.setText(""); }}

    public class ActionListenerRegister implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username=loginFrame.getUsername();
            String password=loginFrame.getPassword();
            User newUser=null;
            if(username.equals("admin")==true)
                newUser=new User(username,password, UserType.administrator);
            else if(username.equals("employee")==true)
                newUser=new User(username,password, UserType.employee);
            else   newUser=new User(username,password, UserType.client);
            deliveryService.addUser(newUser); }}

    public class ActionListenerLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username=loginFrame.getUsername();
            String password=loginFrame.getPassword();
            int ok=0;
            for(int i=0;i<deliveryService.getUsers().size();i++) {
                if(deliveryService.getUsers().get(i).getPassword().equals(password) && deliveryService.getUsers().get(i).getUsername().equals(username))
                {
                    if(deliveryService.getUsers().get(i).getType()== UserType.client){
                        clientFrame=new ClientFrame();
                        addClientlisteners(clientFrame);
                        clientFrame.client=deliveryService.getUsers().get(i);
                        clientFrame.setVisible(true);}
                    else if(deliveryService.getUsers().get(i).getType()== UserType.administrator){
                        administratorFrame.setVisible(true);
                    }
                    else if(deliveryService.getUsers().get(i).getType()== UserType.employee)
                        employeeFrame.setVisible(true);
                    ok=1;
                } }
            if(ok==0)
                JOptionPane.showMessageDialog(null,"Incorrect username or password"); }}

    public class ActionListenerAddItemButtonClient implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            clientFrame.selectedItems.add((MenuItem) clientFrame.getSelectedRow(clientFrame.table,clientFrame.model));
            clientFrame.setTable(clientFrame.secondModel,clientFrame.secondTable);
            clientFrame.secondModel.setData(clientFrame.selectedItems); }}

    public class ActionListenerClearButtonClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientFrame.selectedItems=new ArrayList<MenuItem>();
            clientFrame.secondModel.setData(new ArrayList<MenuItem>());
            clientFrame.secondTable.repaint(); }}

    public class ActionListenerPlaceOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<MenuItem> menuItems=(ArrayList<MenuItem>) clientFrame.secondModel.getData();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Date date=new Date(dtf.format(now));
            Order order=new Order(clientFrame.client.getClientId(),date);
            deliveryService.addObserver(employeeFrame);
            deliveryService.addOrder(order,menuItems);
            deliveryService.generateBill(clientFrame.client,menuItems, order.getOrderID()); }}}


