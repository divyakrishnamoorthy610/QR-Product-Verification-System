import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateProduct {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Update Product");

        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setBounds(40, 30, 120, 30);

        JTextField idField = new JTextField();
        idField.setBounds(160, 30, 180, 30);

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setBounds(40, 70, 120, 30);

        JTextField nameField = new JTextField();
        nameField.setBounds(160, 70, 180, 30);

        JLabel serialLabel = new JLabel("Serial Number:");
        serialLabel.setBounds(40, 110, 120, 30);

        JTextField serialField = new JTextField();
        serialField.setBounds(160, 110, 180, 30);

        JLabel companyLabel = new JLabel("Company:");
        companyLabel.setBounds(40, 150, 120, 30);

        JTextField companyField = new JTextField();
        companyField.setBounds(160, 150, 180, 30);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(40, 190, 120, 30);

        JTextField priceField = new JTextField();
        priceField.setBounds(160, 190, 180, 30);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(40, 230, 120, 30);

        JTextField statusField = new JTextField();
        statusField.setBounds(160, 230, 180, 30);

        JButton updateButton = new JButton("Update Product");
        updateButton.setBounds(125, 280, 150, 35);

        updateButton.addActionListener(e -> {

            String productId = idField.getText();
            String productName = nameField.getText();
            String serialNumber = serialField.getText();
            String company = companyField.getText();
            String price = priceField.getText();
            String status = statusField.getText();

            String url = "jdbc:mysql://localhost:3306/product_verification_db";
            String dbUsername = "root";
            String dbPassword = "YOUR_MYSQL_PASSWORD";

            String sql = "UPDATE product SET " +
                    "product_name = ?, " +
                    "serial_number = ?, " +
                    "company = ?, " +
                    "price = ?, " +
                    "status = ? " +
                    "WHERE product_id = ?";

            try {

                Connection con = DriverManager.getConnection(
                        url,
                        dbUsername,
                        dbPassword
                );

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, productName);
                pst.setString(2, serialNumber);
                pst.setString(3, company);
                pst.setDouble(4, Double.parseDouble(price));
                pst.setString(5, status);
                pst.setString(6, productId);

                int rows = pst.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Product Updated Successfully!"
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

        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(serialLabel);
        frame.add(serialField);

        frame.add(companyLabel);
        frame.add(companyField);

        frame.add(priceLabel);
        frame.add(priceField);

        frame.add(statusLabel);
        frame.add(statusField);

        frame.add(updateButton);

        frame.setSize(400, 370);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
