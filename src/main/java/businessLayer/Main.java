package businessLayer;

import presentationLayer.AdministratorFrame;
import presentationLayer.Controller;
import presentationLayer.EmployeeFrame;
import presentationLayer.LoginFrame;
import businessLayer.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LoginFrame l=new LoginFrame();
        DeliveryService d= null;
        d = new DeliveryService();
        AdministratorFrame a=new AdministratorFrame();
        EmployeeFrame e=new EmployeeFrame();
        Controller c=new Controller(l,d,a,e);
        l.setVisible(true);

    }
}
