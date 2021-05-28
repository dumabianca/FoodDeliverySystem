package presentationLayer;

import businessLayer.DeliveryService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private JButton loginButton=new JButton("                                 Login                                ");
    private JButton registerButton=new JButton("                               Register                             ");
    private JTextField userNameField=new JTextField(27);
    private JTextField passwordField=new JTextField(27);
    private JLabel userNamelabel=new JLabel("\uD83D\uDC64");
    private JLabel passwordLabel=new JLabel("\uD83D\uDD11");
    BufferedImage image = ImageIO.read(new File(".\\loginn.PNG"));
    JLabel label = new JLabel(new ImageIcon(image));
    public LoginFrame() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login businessLayer.User");
        this.setSize(350,500);
        JPanel mainPanel=new JPanel();
        JPanel loginImagePanel=new JPanel();

        userNameField.setFont(new Font("Italic",Font.PLAIN,10));

        userNameField.setText("\uD83D\uDC64username");
        passwordField.setText("\uD83D\uDD11password");
        passwordField.setFont(new Font("Italic",Font.PLAIN,10));
        loginImagePanel.add(label);
        JPanel loginInfoPanel=new JPanel();
        loginInfoPanel.setLayout(new BoxLayout(loginInfoPanel,BoxLayout.Y_AXIS));
        loginInfoPanel.add(loginImagePanel);

        //loginInfoPanel.add(userNamelabel);
        loginInfoPanel.add(userNameField);
        loginInfoPanel.add(Box.createRigidArea(new Dimension(0,10)));
        //loginInfoPanel.add(passwordLabel);
        loginInfoPanel.add(passwordField);


        JPanel buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
        loginButton.setSize(330,10);
        loginButton.setBackground(new Color( 6, 60, 59));
        loginButton.setForeground(Color.white);
        registerButton.setBackground(new Color(3, 204, 172));
        registerButton.setForeground(Color.white);
        buttonsPanel.add(loginButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0,14)));

        buttonsPanel.add(registerButton);

        mainPanel.add(loginInfoPanel);

        mainPanel.add(buttonsPanel);

        this.setContentPane(mainPanel);
    }
    public String getUsername()
    {
        return userNameField.getText();
    }
    public String getPassword()
    {
        return passwordField.getText();
    }
    public void addListenerLogin(ActionListener l)
    {
        this.loginButton.addActionListener(l);
    }
    public void addListenerRegister(ActionListener l)
    {
        this.registerButton.addActionListener(l);
    }


}
