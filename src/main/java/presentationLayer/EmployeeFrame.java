package presentationLayer;

import javax.swing.*;
import java.awt.image.ImageObserver;
import businessLayer.*;
import presentationLayer.AdministratorFrame;
import presentationLayer.ClientFrame;
import presentationLayer.EmployeeFrame;
import presentationLayer.LoginFrame;
import java.util.*;
/**
 * @Author: Duma Bianca
 * @Since: May 18, 2021
 * Employee interface
 */
public class EmployeeFrame extends JFrame implements Observer {
    private JPanel mainPanel=new JPanel();
    private JTextArea infoArea=new JTextArea(60,60);
    public EmployeeFrame()
    {
        this.setTitle("Employee operations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,810);
        mainPanel.add(infoArea);
        this.setContentPane(mainPanel);
    }

    @Override
    public void update(Observable o, Object arg) {

        String s=new String("");
        s=infoArea.getText()+s+arg.toString();
        infoArea.setText(s);
        this.revalidate();
    }
}
