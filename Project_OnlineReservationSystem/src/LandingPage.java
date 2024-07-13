import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class LandingPage implements ActionListener {

    private bookTicket bookTicketInstance;
    private Jdbc connector;
    JButton insertButton;
    JButton cancelButton;
    JButton showButton;
    JButton exitButton;
    JFrame frame;

    public LandingPage() {

        connector = new Jdbc();
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel headLabel = new JLabel();
        headLabel.setBounds(0, 10, 1000, 200);
        headLabel.setText("<html>Welcome to Online <br> Reservation System!!<html>");
        headLabel.setFont(new Font("Augustine", Font.BOLD, 80));
        headLabel.setForeground(Color.decode("#FCA311"));
        headLabel.setHorizontalAlignment(SwingUtilities.CENTER);

        insertButton = new JButton();
        insertButton.setText("Book Your Journey");
        insertButton.setFont(new Font("Dialog", Font.BOLD, 30));
        insertButton.setBackground(Color.decode("#14213D"));
        insertButton.setForeground(Color.decode("#FCA311"));
        insertButton.setBounds(300, 300, 400, 60);
        insertButton.setFocusable(false);
        insertButton.addActionListener(this);

        cancelButton = new JButton();
        cancelButton.setText("Cancel Your Journey");
        cancelButton.setFont(new Font("Dialog", Font.BOLD, 30));
        cancelButton.setBackground(Color.decode("#14213D"));
        cancelButton.setForeground(Color.decode("#FCA311"));
        cancelButton.setBounds(300, 400, 400, 60);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(this);

        showButton = new JButton();
        showButton.setText("Show Your Journey");
        showButton.setFont(new Font("Dialog", Font.BOLD, 30));
        showButton.setBackground(Color.decode("#14213D"));
        showButton.setForeground(Color.decode("#FCA311"));
        showButton.setBounds(300, 500, 400, 60);
        showButton.addActionListener(this);
        showButton.setFocusable(false);

        exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.setFont(new Font("Dialog", Font.BOLD, 30));
        exitButton.setBackground(Color.decode("#14213D"));
        exitButton.setForeground(Color.decode("#FCA311"));
        exitButton.setBounds(300, 600, 400, 60);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        frame.add(insertButton);
        frame.add(cancelButton);
        frame.add(showButton);
        frame.add(exitButton);
        frame.add(headLabel);
        frame.getContentPane().setBackground(Color.black);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            bookTicket bk = new bookTicket();
            frame.dispose();
        }
        if (e.getSource() == cancelButton) {
            cancelTicket can = new cancelTicket();
            frame.dispose();
        }

        if (e.getSource() == showButton) {
            try {
                if (bookTicketInstance == null) {
                    bookTicketInstance = new bookTicket();
                }
                String[][] data = bookTicketInstance.getData();
                String[] columns = {"PNR Number", "Passenger Name", "Train Number", "Coach Type", "Date of Journey", "Start Station", "Destination Station"};

                if (data != null) {
                    JTable table = new JTable(data, columns);
                    JFrame showFrame = new JFrame("Show Journey");
                    showFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Change here
                    showFrame.add(new JScrollPane(table));
                    showFrame.setSize(1000, 1000);
                    showFrame.setLocationRelativeTo(null);
                    showFrame.setVisible(true);

                    showFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            frame.setVisible(true); // Show the LandingPage frame again
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "No data found.");
                }
            } catch (Exception e1) {
                e1.getMessage();
            } finally {
                try {
                    Connection con = connector.getConnection();
                    con.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        if (e.getSource() == exitButton) {
            new exit();
        }
    }



    public static void main(String[] args) {
        new LandingPage();
    }
}
