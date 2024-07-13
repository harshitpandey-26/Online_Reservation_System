import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class bookTicket implements ActionListener {

    JButton saveButton;
    JFrame frame;
    JLabel pnrLabel;
    int pnrNumber;

    JComboBox box;

    JTextField namTextfield;
    JTextField trainTextfield;
    JTextField dateTextfield;
    JTextField startTextfield;
    JTextField destTextfield;

    private Jdbc connector;
    private static bookTicket instance;
    private String[][] data;
    public bookTicket(){
        frame = new JFrame();

        connector = new Jdbc();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        Random ran = new Random();
        pnrNumber = ran.nextInt(999999) + 111111;

        JLabel headLabel = new JLabel();
        headLabel.setBounds(0, 10, 1000, 100);
        headLabel.setText("Book Your Journey");
        headLabel.setFont(new Font("Augustine", Font.BOLD, 80));
        headLabel.setForeground(Color.decode("#FCA311"));
        headLabel.setHorizontalAlignment(SwingUtilities.CENTER);

        JLabel nameLable = new JLabel();
        nameLable.setText("Enter your name: ");
        nameLable.setFont(new Font("Dialog", Font.BOLD, 30));
        nameLable.setForeground(Color.white);
        nameLable.setBounds(50, 150, 300, 50);

        namTextfield = new JTextField();
        namTextfield.setBounds(440,150,300,55);
        namTextfield.setBackground(Color.decode("#14213D"));
        namTextfield.setForeground(Color.decode("#FCA311"));
        namTextfield.setFont(new Font("Dialog",Font.BOLD,30));

        JLabel trainumLable = new JLabel();
        trainumLable.setText("Enter Train number: ");
        trainumLable.setFont(new Font("Dialog", Font.BOLD, 30));
        trainumLable.setForeground(Color.white);
        trainumLable.setBounds(50, 250, 300, 50);

        trainTextfield = new JTextField();
        trainTextfield.setBounds(440,250,300,55);
        trainTextfield.setBackground(Color.decode("#14213D"));
        trainTextfield.setForeground(Color.decode("#FCA311"));
        trainTextfield.setFont(new Font("Dialog",Font.BOLD,30));

        JLabel coachTypeLable = new JLabel();
        coachTypeLable.setText("Enter Coach Type: ");
        coachTypeLable.setFont(new Font("Dialog", Font.BOLD, 30));
        coachTypeLable.setForeground(Color.white);
        coachTypeLable.setBounds(50, 350, 300, 30);

        String[] arr = {"AC","NON-AC","SLEEPER","GENERAL"};
        box = new JComboBox(arr);
        box.setSelectedIndex(-1);
        box.setBounds(440,350,300,55);
        box.setBackground(Color.decode("#14213D"));
        box.setForeground(Color.decode("#FCA311"));
        box.setFont(new Font("Dialog",Font.BOLD,30));

        JLabel dateLable = new JLabel();
        dateLable.setText("Enter Date of Journey: ");
        dateLable.setFont(new Font("Dialog", Font.BOLD, 30));
        dateLable.setForeground(Color.white);
        dateLable.setBounds(50, 450, 500, 30);

        dateTextfield = new JTextField();
        dateTextfield.setBounds(440,450,300,55);
        dateTextfield.setBackground(Color.decode("#14213D"));
        dateTextfield.setForeground(Color.decode("#FCA311"));
        dateTextfield.setFont(new Font("Dialog",Font.BOLD,30));

        JLabel startLable = new JLabel();
        startLable.setText("Enter starting station: ");
        startLable.setFont(new Font("Dialog", Font.BOLD, 30));
        startLable.setForeground(Color.white);
        startLable.setBounds(50, 550, 500, 30);

        startTextfield = new JTextField();
        startTextfield.setBounds(440,550,300,55);
        startTextfield.setBackground(Color.decode("#14213D"));
        startTextfield.setForeground(Color.decode("#FCA311"));
        startTextfield.setFont(new Font("Dialog",Font.BOLD,30));

        JLabel destLable = new JLabel();
        destLable.setText("Enter destination station: ");
        destLable.setFont(new Font("Dialog", Font.BOLD, 30));
        destLable.setForeground(Color.white);
        destLable.setBounds(50, 650, 500, 30);

        destTextfield = new JTextField();
        destTextfield.setBounds(440,650,300,55);
        destTextfield.setBackground(Color.decode("#14213D"));
        destTextfield.setForeground(Color.decode("#FCA311"));
        destTextfield.setFont(new Font("Dialog",Font.BOLD,30));

        pnrLabel = new JLabel(""+pnrNumber);


        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Dialog", Font.BOLD, 30));
        saveButton.setForeground(Color.white);
        saveButton.setBackground(Color.decode("#FCA311"));
        saveButton.setBounds(780, 630, 200, 50);
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        frame.add(headLabel);
        frame.add(nameLable);
        frame.add(namTextfield);

        frame.add(trainumLable);
        frame.add(trainTextfield);

        frame.add(coachTypeLable);
        frame.add(box);
//        frame.add(coachTextfield);

        frame.add(dateLable);
        frame.add(dateTextfield);

        frame.add(startLable);
        frame.add(startTextfield);

        frame.add(destLable);
        frame.add(destTextfield);

        frame.add(saveButton);
        frame.getContentPane().setBackground(Color.black);
        frame.setVisible(true);
    }

    public String[][] getData() {
        try {
            Connection con = connector.getConnection();
            String query = "SELECT * FROM reservation";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<String[]> dataArrayList = new ArrayList<>();
            while (resultSet.next()) {
                String[] data = new String[7];
                data[0] = resultSet.getString("pnr_number");
                data[1] = resultSet.getString("passenger_name");
                data[2] = resultSet.getString("train_number");
                data[3] = resultSet.getString("coach_type");
                data[4] = resultSet.getString("date_of_journey");
                data[5] = resultSet.getString("start_station");
                data[6] = resultSet.getString("dest_station");
                dataArrayList.add(data);
            }

            String[][] data = new String[dataArrayList.size()][7];
            for (int i = 0; i < dataArrayList.size(); i++) {
                data[i] = dataArrayList.get(i);
            }

            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==saveButton){

            int pnr = Integer.valueOf(pnrLabel.getText());
            String passengerName = namTextfield.getText();
            int trainNumber = Integer.parseInt(trainTextfield.getText());
            String coachType = (String) box.getSelectedItem();
            String date = dateTextfield.getText();
            String startStat = startTextfield.getText();
            String desStat = destTextfield.getText();

            try {
                Connection con = connector.getConnection();

                String query = "INSERT INTO reservation(pnr_number, passenger_name, train_number, coach_type, date_of_journey, start_station, dest_station) VALUES (?,?,?,?,?,?,?)";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,pnr);
                preparedStatement.setString(2,passengerName);
                preparedStatement.setInt(3,trainNumber);
                preparedStatement.setString(4,coachType);
                preparedStatement.setString(5,date);
                preparedStatement.setString(6,startStat);
                preparedStatement.setString(7,desStat);

                int res = preparedStatement.executeUpdate();
                if (res>0){
                    JOptionPane.showMessageDialog(null,"Data Inserted Successfully!");
                    LandingPage l = new LandingPage();
                }else {
                    System.out.println("Data not inserted.");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
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

    public static void main(String[] args) {

//        new bookTicket();
    }
}
