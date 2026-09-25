import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewProducts {

    public static void main(String[] args) {

        JFrame frame = new JFrame("View Products");

        String[] columns = {
            "Product ID",
            "Product Name",
            "Serial Number",
            "Company",
            "Price",
            "Status"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        String url = "jdbc:mysql://localhost:3306/product_verification_db";
        String dbUsername = "root";
        String dbPassword = "YOUR_MYSQL_PASSWORD";

        String sql = "SELECT * FROM product";

        try {

            Connection con = DriverManager.getConnection(
                url,
                dbUsername,
                dbPassword
            );

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String productId = rs.getString("product_id");
                String productName = rs.getString("product_name");
                String serialNumber = rs.getString("serial_number");
                String company = rs.getString("company");
                double price = rs.getDouble("price");
                String status = rs.getString("status");

                model.addRow(new Object[]{
                    productId,
                    productName,
                    serialNumber,
                    company,
                    price,
                    status
                });
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                frame,
                "Database Error: " + ex.getMessage()
            );
        }

        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
