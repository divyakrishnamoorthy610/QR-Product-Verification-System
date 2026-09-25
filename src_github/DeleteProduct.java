import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteProduct {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Delete Product");

        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setBounds(50, 50, 100, 30);

        JTextField idField = new JTextField();
        idField.setBounds(150, 50, 180, 30);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(130, 110, 150, 35);

        deleteButton.addActionListener(e -> {

            String productId = idField.getText();

            String url = "jdbc:mysql://localhost:3306/product_verification_db";
            String dbUsername = "root";
            String dbPassword = "YOUR_MYSQL_PASSWORD";

            String sql = "DELETE FROM product WHERE product_id = ?";

            try {

                Connection con = DriverManager.getConnection(
                        url,
                        dbUsername,
                        dbPassword
                );

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, productId);

                int rows = pst.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Product Deleted Successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Product ID Not Found!"
                    );
                }

                pst.close();
                con.close();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Error: " + ex.getMessage()
                );
            }
        });

        frame.add(idLabel);
        frame.add(idField);
        frame.add(deleteButton);

        frame.setSize(400, 220);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
