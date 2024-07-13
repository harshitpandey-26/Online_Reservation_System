import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cancelTicket implements ActionListener {

    JButton canButton;
    private Jdbc connector;
    JTextField canTextfield;

    public cancelTicket() {

        JFrame frame = new JFrame();

        connector = new Jdbc();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel cancelLabel = new JLabel();
        cancelLabel.setBounds(0, 10, 500, 100);
        cancelLabel.setText("Cancel Journey");
        cancelLabel.setFont(new Font("Augustine", Font.BOLD, 60));
        cancelLabel.setForeground(Color.decode("#FCA311"));
        cancelLabel.setHorizontalAlignment(SwingUtilities.CENTER);

        JLabel canLable = new JLabel();
        canLable.setText("Enter PNR number: ");
        canLable.setFont(new Font("Dialog", Font.BOLD, 30));
        canLable.setForeground(Color.white);
        canLable.setBounds(50, 150, 300, 50);

        canTextfield = new JTextField();
        canTextfield.setBounds(350, 150, 300, 50);
        canTextfield.setBackground(Color.decode("#14213D"));
        canTextfield.setForeground(Color.decode("#FCA311"));
        canTextfield.setFont(new Font("Dialog", Font.BOLD, 30));

        canButton = new JButton("Cancel");
        canButton.setFont(new Font("Dialog", Font.BOLD, 30));
        canButton.setForeground(Color.white);
        canButton.setBackground(Color.decode("#FCA311"));
        canButton.setBounds(460, 450, 200, 50);
        canButton.setFocusable(false);
        canButton.addActionListener(this);

        frame.add(cancelLabel);
        frame.add(canLable);
        frame.add(canTextfield);
        frame.add(canButton);
        frame.getContentPane().setBackground(Color.black);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new cancelTicket();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==canButton) {
            try {
                Connection con = connector.getConnection();
                int pnr = Integer.parseInt(canTextfield.getText());

                String query = "DELETE FROM reservation WHERE pnr_number = ? ";

                PreparedStatement preparedStatement = con.prepareStatement(query);

                preparedStatement.setInt(1, pnr);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Ticket Canceled Successfully");
                    new LandingPage();
                }
            } catch (SQLException e1) {
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
    }
}

