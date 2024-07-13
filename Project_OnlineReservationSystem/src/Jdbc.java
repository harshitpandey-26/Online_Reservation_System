import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {

    public static final String url = "jdbc:mysql://localhost:3306/demo";
    public static final String username = "root";
    public static final String password = "electron_2005";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.getMessage();
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("connection successful");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }



}
