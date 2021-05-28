package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import businessLayer.*;
import businessLayer.MenuItem;
import presentationLayer.AdministratorFrame;
import presentationLayer.ClientFrame;
import presentationLayer.EmployeeFrame;
import presentationLayer.LoginFrame;
/**
 * @Author: Duma Bianca
 * @Since: May 18, 2021
 * Client interface
 */
public class ClientFrame extends JFrame {
    public User client;
    private JButton placeOrderButton=new JButton("PLACE ORDER");
    private JButton addItemButton= new JButton("ADD ITEM");
    private JButton clearButton=new JButton("CLEAR");
    private JButton filterButton=new JButton("FILTER");
    private JButton removefilterButton=new JButton("REMOVE FILTERS");
    private JButton finishButton=new JButton("FINISH");
    private JLabel filterLabel=new JLabel("FILTER");
    private JLabel nameLabel=new JLabel("NAME");
    private JLabel ratingLabel=new JLabel("RATING");
    private JLabel caloriesLabel=new JLabel("CALORIES");
    private JLabel proteinsLabel=new JLabel("PROTEINS");
    private JLabel fatsLabel=new JLabel("FATS");
    private JLabel sodiumLabel=new JLabel("SODIUM");
    private JLabel priceLabel=new JLabel("PRICE");
    private JComboBox comboBox=new JComboBox(new String[]{"name","rating","calories","proteins","fats","sodium","price"});
    public JTextField field1=new JTextField(10);
    public JTextField field2=new JTextField(10);

    private JPanel mainPanel=new JPanel();
    public JTable table=new JTable();
    private JScrollPane tablePanel =new JScrollPane(table);
    public JTable secondTable=new JTable();
    private JScrollPane secondTablePanel =new JScrollPane(secondTable);
    public ArrayList<MenuItem> selectedItems=new ArrayList<MenuItem>();
    BeanPropertyTableModel<MenuItem> model = new BeanPropertyTableModel<MenuItem>(MenuItem.class);
    BeanPropertyTableModel<MenuItem> secondModel = new BeanPropertyTableModel<MenuItem>(MenuItem.class);
    public ClientFrame()
    {

        this.setTitle("Client operations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,810);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(tablePanel);
        model.setData(DeliveryService.getMenuItems());
        this.setTable(model,table);
        JPanel filterPanel=new JPanel();
        // filterPanel.setLayout(new BoxLayout(filterPanel,BoxLayout.Y_AXIS));
        filterPanel.add(comboBox);
        filterPanel.add(field1);
        filterPanel.add(field2);
        filterButton.setForeground(Color.white);
        filterButton.setBackground(new Color(3, 204, 172));
        filterPanel.add(filterButton);
        removefilterButton.setForeground(Color.white);
        removefilterButton.setBackground(new Color( 6, 60, 59));
        filterPanel.add(removefilterButton);
        mainPanel.add(filterPanel);

        JPanel orderPanel=new JPanel();
        addItemButton.setForeground(Color.white);
        addItemButton.setBackground(new Color(3, 204, 172));
        orderPanel.add(addItemButton);
        placeOrderButton.setForeground(Color.white);
        placeOrderButton.setBackground(new Color( 6, 60, 59));
        orderPanel.add(placeOrderButton);
        clearButton.setForeground(Color.white);
        clearButton.setBackground(new Color(3, 204, 172));
        orderPanel.add(clearButton);
        orderPanel.add(secondTablePanel);
        this.setTable(secondModel,secondTable);
        mainPanel.add(orderPanel);
        JPanel finishPanel=new JPanel();
        mainPanel.add(finishPanel);
        this.setContentPane(mainPanel);
    }
    public void addListenerClearButton(ActionListener l)
    {
        clearButton.addActionListener(l);
    }
    public void addListenerAddItemButton(ActionListener l)
    {
        addItemButton.addActionListener(l);
    }
    public void addListenerPlaceOrderButton(ActionListener l)
    {
        placeOrderButton.addActionListener(l);
    }
    public void addListenerFinishButton(ActionListener l)
    {
        finishButton.addActionListener(l);
    }
    public void addListenerFilterButton(ActionListener l)
    {
        filterButton.addActionListener(l);
    }
    public void addListenerRemoveFilterButton(ActionListener l)
    {
        removefilterButton.addActionListener(l);
    }
    public String getField1()
    {
        return field1.getText();
    }
    public String getField2()
    {
        return field2.getText();
    }

    public void setTable(BeanPropertyTableModel<MenuItem> model, JTable t)
    {
        t.setModel(model);
    }
    public Object getSelectedRow(JTable t, BeanPropertyTableModel<MenuItem> m)
    {
        return m.getObjectRow(t.getSelectedRow());
    }
    public String getComboBox()
    {
        return  (String) this.comboBox.getSelectedItem();
    }
    public User getClient() {
        return client;
    }
}
