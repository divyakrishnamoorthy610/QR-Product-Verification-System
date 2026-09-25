import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class AddProduct {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Add Product");

        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setBounds(40, 30, 120, 30);

        JTextField idField = new JTextField();
        idField.setBounds(160, 30, 180, 30);

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setBounds(40, 70, 120, 30);

        JTextField nameField = new JTextField();
        nameField.setBounds(160, 70, 180, 30);

        JLabel companyLabel = new JLabel("Company:");
        companyLabel.setBounds(40, 110, 120, 30);

        JTextField companyField = new JTextField();
        companyField.setBounds(160, 110, 180, 30);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(40, 150, 120, 30);

        JTextField priceField = new JTextField();
        priceField.setBounds(160, 150, 180, 30);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(40, 190, 120, 30);

        JTextField statusField = new JTextField();
        statusField.setBounds(160, 190, 180, 30);

        JButton addButton = new JButton("Add Product");
        addButton.setBounds(130, 240, 140, 35);


        addButton.addActionListener(e -> {

            String productId = idField.getText();
            String productName = nameField.getText();
            String company = companyField.getText();
            String price = priceField.getText();
            String status = statusField.getText();

            // Generate unique Serial Number
            String serialNumber = "SN-" +
                    UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();


            String url =
                    "jdbc:mysql://localhost:3306/product_verification_db";

            String dbUsername = "root";

            // Enter your MySQL password here
            String dbPassword = "YOUR_MYSQL_PASSWORD";


            String sql = "INSERT INTO product " +
                    "(product_id, product_name, serial_number, company, price, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";


            try {

                Connection con = DriverManager.getConnection(
                        url,
                        dbUsername,
                        dbPassword
                );

                PreparedStatement pst =
                        con.prepareStatement(sql);


                // Insert product into MySQL
                pst.setString(1, productId);
                pst.setString(2, productName);
                pst.setString(3, serialNumber);
                pst.setString(4, company);
                pst.setDouble(5, Double.parseDouble(price));
                pst.setString(6, status);

                pst.executeUpdate();


                // Generate QR Code
                String qrData = serialNumber;

                String filePath =
                        "../QR_" + productId + ".png";

                BitMatrix matrix =
                        new MultiFormatWriter().encode(
                                qrData,
                                BarcodeFormat.QR_CODE,
                                300,
                                300
                        );

                Path path =
                        FileSystems.getDefault()
                                .getPath(filePath);

                MatrixToImageWriter.writeToPath(
                        matrix,
                        "PNG",
                        path
                );


                JOptionPane.showMessageDialog(
                        frame,
                        "Product Added Successfully!\n\n" +
                        "Serial Number: " + serialNumber +
                        "\nQR Code Generated Successfully!"
                );


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

        frame.add(companyLabel);
        frame.add(companyField);

        frame.add(priceLabel);
        frame.add(priceField);

        frame.add(statusLabel);
        frame.add(statusField);

        frame.add(addButton);


        frame.setSize(400, 320);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
