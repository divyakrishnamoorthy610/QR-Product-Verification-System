import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/product_verification_db";
        String username = "root";
        String password = "YOUR_MYSQL_PASSWORD";

        try {
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Database connected successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
