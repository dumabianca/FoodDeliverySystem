package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
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
 * Administrator interface
 */
public class AdministratorFrame extends JFrame {
    private JButton importButton = new JButton("IMPORT PRODUCTS");
    private JButton addItemButton = new JButton("ADD ITEM");
    private JButton createItemButton = new JButton("CREATE ITEM");
    private JButton addProductButton = new JButton("ADD PRODUCT");
    private JButton deleteProductButton = new JButton("DELETE PRODUCT");
    private JButton viewCompositeProductButton = new JButton("VIEW COMPOSITE PRODUCT");
    private JButton modifyButton = new JButton("MODIFY");
    public JComboBox comboBox = new JComboBox(new String[]{"name", "rating", "calories", "proteins", "fats", "sodium", "price"});
    private JTextField modifiedValue = new JTextField(15);
    private JButton clearButton = new JButton("CLEAR");
    private JTextField addNameField = new JTextField("Name...");
    private JTextField addRatingField = new JTextField("Rating..");
    private JTextField addCaloriesField = new JTextField("Calories..");
    private JTextField addProteinsField = new JTextField("Proteins..");
    private JTextField addFatsField = new JTextField("Fats..");
    private JTextField addSodiumField = new JTextField("Sodium..");
    private JTextField addPriceField = new JTextField("Price..");
    public JTable table = new JTable();

    private JScrollPane tablePanel = new JScrollPane(table);
    public BeanPropertyTableModel<MenuItem> model = new BeanPropertyTableModel<MenuItem>(MenuItem.class);
    public BeanPropertyTableModel<MenuItem> secondModel = new BeanPropertyTableModel<MenuItem>(MenuItem.class);
    private JTextField nameCreatedField = new JTextField("Type the name of the new businessLayer.MenuItem");
    JPanel mainPanel = new JPanel();
    public ArrayList<MenuItem> selectedItems = new ArrayList<MenuItem>();
    public JTable secondTable = new JTable();
    private JScrollPane secondTablePanel = new JScrollPane(secondTable);

    private JButton generateReportButton = new JButton("GENERATE REPORT");
    public JComboBox comboRaports = new JComboBox(new String[]{"Raport1", "Raport2", "Raport3", "Raport4"});
    private JTextField value1Raport = new JTextField(5);
    private JTextField value2Raport = new JTextField(5);

    public AdministratorFrame() throws IOException {
        this.setTitle("Administrator operations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel importPanel = new JPanel();
        importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.Y_AXIS));
        importButton.setBackground(new Color( 6, 60, 59));
        importButton.setForeground(Color.white);
        importPanel.add(importButton);
        importPanel.setAlignmentX(CENTER_ALIGNMENT);

        mainPanel.add(importPanel);

        mainPanel.add(tablePanel);
        model.setData(DeliveryService.getMenuItems());
        this.setTable(model, table);

        JPanel addDeletePanel = new JPanel();
        addProductButton.setBackground(new Color(3, 204, 172));
        addProductButton.setForeground(Color.white);
        addDeletePanel.add(addProductButton);
        addDeletePanel.add(addNameField);
        addDeletePanel.add(addPriceField);
        addDeletePanel.add(addSodiumField);
        addDeletePanel.add(addFatsField);
        addDeletePanel.add(addProteinsField);
        addDeletePanel.add(addRatingField);
        addDeletePanel.add(addCaloriesField);
        JPanel modifyPanel = new JPanel();
        modifyPanel.add(comboBox);
        modifyPanel.add(modifiedValue);
        modifyButton.setBackground(new Color(3, 204, 172));
        modifyButton.setForeground(Color.white);
        modifyPanel.add(modifyButton);
        deleteProductButton.setBackground(new Color(3, 204, 172));
        deleteProductButton.setForeground(Color.white);
        addDeletePanel.add(deleteProductButton);
        mainPanel.add(addDeletePanel);
        mainPanel.add(modifyPanel);
        JPanel createNewItemPanel = new JPanel();


        addItemButton.setForeground(Color.white);
        createItemButton.setForeground(Color.white);
        clearButton.setForeground(Color.white);
        addItemButton.setBackground(new Color( 6, 60, 59));
        createItemButton.setBackground(new Color( 6, 60, 59));
        clearButton.setBackground(new Color( 6, 60, 59));
        viewCompositeProductButton.setForeground(Color.white);
        viewCompositeProductButton.setBackground(new Color(3, 204, 172));
        createNewItemPanel.add(addItemButton);
        createNewItemPanel.add(createItemButton);
        createNewItemPanel.add(nameCreatedField);
        createNewItemPanel.add(clearButton);
        createNewItemPanel.add(viewCompositeProductButton);
        createNewItemPanel.add(secondTablePanel);

        mainPanel.add(createNewItemPanel);

        JPanel raportPanel = new JPanel();
        raportPanel.add(comboRaports);
        raportPanel.add(value1Raport);
        raportPanel.add(value2Raport);
        generateReportButton.setForeground(Color.white);
        generateReportButton.setBackground(new Color(3, 204, 172));
        raportPanel.add(generateReportButton);
        mainPanel.add(raportPanel);
        this.setContentPane(mainPanel);
    }

    public void addListenerImportButton(ActionListener l) {
        importButton.addActionListener(l);
    }

    public void addListenerItemButton(ActionListener l) {
        addItemButton.addActionListener(l);
    }

    public void addListenerCreateButton(ActionListener l) {
        createItemButton.addActionListener(l);
    }

    public void addListenerAddButton(ActionListener l) {
        addProductButton.addActionListener(l);
    }

    public void addListenerViewCompositeButton(ActionListener l) {
        viewCompositeProductButton.addActionListener(l);
    }

    public void addListenerClearButton(ActionListener l) {
        clearButton.addActionListener(l);
    }

    public void addListenerDeleteButton(ActionListener l) {
        deleteProductButton.addActionListener(l);
    }

    public void addListenerModifyButton(ActionListener l) {
        modifyButton.addActionListener(l);
    }

    public void addListenerRaportButton(ActionListener l) {
        generateReportButton.addActionListener(l);
    }

    public void setTable(BeanPropertyTableModel<MenuItem> model, JTable t) {
        t.setModel(model);
    }

    public Object getSelectedRow(JTable t, BeanPropertyTableModel<MenuItem> m) {
        return m.getObjectRow(t.getSelectedRow());
    }

    public int getValue1Report() {
        return Integer.parseInt(value1Raport.getText());
    }

    public int getValue2Report() {
        if (!value2Raport.getText().equals(""))
            return Integer.parseInt(value2Raport.getText());
        return 0;
    }

    public String getModifiedValue() {
        return this.modifiedValue.getText();
    }

    public String getComboText(JComboBox j) {
        return (String) j.getSelectedItem();
    }

    public String getAddedName() {
        return this.addNameField.getText();
    }

    public String getAddedPrice() {
        return this.addPriceField.getText();
    }

    public String getAddedRating() {
        return this.addRatingField.getText();
    }

    public String getAddedSodium() {
        return this.addSodiumField.getText();
    }

    public String getAddedProteins() {
        return this.addProteinsField.getText();
    }

    public String getAddedFats() {
        return this.addFatsField.getText();
    }

    public String getAddedCalories() {
        return this.addCaloriesField.getText();
    }

    public String getNameCreatedItem() {
        return nameCreatedField.getText();
    }
}

