import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame implements ActionListener {
    JButton loginButton;
    JTextField usernameField;
    JPasswordField passwordField;
    private Jdbc connector;

    public login(){

        connector = new Jdbc();

        setSize(520,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(CommonConstants.Primary_color);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0,25,520,100);
        loginLabel.setForeground(CommonConstants.Text_color);
        loginLabel.setFont(new Font("Dialog",Font.BOLD,40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        //create username label.

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30,150,400,25);
        usernameLabel.setForeground(CommonConstants.Text_color);
        usernameLabel.setFont(new Font("Dialog",Font.PLAIN,18));

        //create username TextField.

        usernameField = new JTextField();
        usernameField.setBounds(30,185,450,55);
        usernameField.setBackground(CommonConstants.Seconadry_color);
        usernameField.setForeground(CommonConstants.Text_color);
        usernameField.setFont(new Font("Dialog",Font.PLAIN,24));

        add(usernameLabel);
        add(usernameField);

        //create password label.

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30,335,400,25);
        passwordLabel.setForeground(CommonConstants.Text_color);
        passwordLabel.setFont(new Font("Dialog",Font.PLAIN,18));

        //create password TextField.

        passwordField = new JPasswordField();
        passwordField.setBounds(30,365,450,55);
        passwordField.setBackground(CommonConstants.Seconadry_color);
        passwordField.setForeground(CommonConstants.Text_color);
        passwordField.setFont(new Font("Dialog",Font.PLAIN,24));

        add(passwordLabel);
        add(passwordField);

        // create login button.
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog",Font.BOLD,20));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(CommonConstants.Text_color);
        loginButton.setBounds(125,520,250,50);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);


        add(loginButton);

        setVisible(true);


    }

    public static void main(String[] args) {
        new login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton){
            try{

                Connection con = connector.getConnection();

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                String query = "SELECT * FROM user WHERE username = ? AND password = ?" ;
                PreparedStatement preparedStatement = con.prepareStatement(query);

                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    setVisible(false);
                    new LandingPage();
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                    setVisible(false);
                    dispose();
                }

            }
            catch(SQLException e1){
                System.out.println(e1.getMessage());
            }
            finally {
                try {
                    Connection con = connector.getConnection();
                    con.close();
                }
                catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }
}
