import javax.swing.*;
import java.awt.*;

public class exit {
    public exit(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel();
        label.setText("<html>Thank you for using Online Reservation System!! </html>");
        label.setBounds(30,200,900,200);
        label.setForeground(Color.decode("#FCA311"));
//        label.setBackground(Color.decode("#14213D"));
        label.setFont(new Font("Dialog",Font.BOLD,60));
//        label.setHorizontalAlignment(SwingUtilities.CENTER);

        frame.add(label);
        frame.getContentPane().setBackground(Color.black);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new exit();
    }
}
