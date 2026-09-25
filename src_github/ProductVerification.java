import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ProductVerification {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Product Verification");

        JLabel title = new JLabel("PRODUCT VERIFICATION");
        title.setBounds(110, 30, 220, 30);

        JButton scanButton = new JButton("Scan QR Code");
        scanButton.setBounds(100, 80, 200, 40);

        scanButton.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {

                try {

                    // Select QR image
                    File file = chooser.getSelectedFile();

                    // Read image
                    BufferedImage image = ImageIO.read(file);

                    // Convert image for ZXing
                    LuminanceSource source =
                            new BufferedImageLuminanceSource(image);

                    BinaryBitmap bitmap =
                            new BinaryBitmap(
                                    new HybridBinarizer(source)
                            );

                    // Read QR code
                    Result qrResult =
                            new MultiFormatReader().decode(bitmap);

                    // Get serial number from QR
                    String serialNumber = qrResult.getText();

                    // Database connection
                    String url =
                            "jdbc:mysql://localhost:3306/product_verification_db";

                    String dbUsername = "root";

                    String dbPassword = "YOUR_MYSQL_PASSWORD";

                    // Search product using serial number
                    String sql =
                            "SELECT * FROM product WHERE serial_number = ?";

                    Connection con =
                            DriverManager.getConnection(
                                    url,
                                    dbUsername,
                                    dbPassword
                            );

                    PreparedStatement pst =
                            con.prepareStatement(sql);

                    pst.setString(1, serialNumber);

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {

                        String productId =
                                rs.getString("product_id");

                        String productName =
                                rs.getString("product_name");

                        String company =
                                rs.getString("company");

                        double price =
                                rs.getDouble("price");

                        String status =
                                rs.getString("status");

                        JOptionPane.showMessageDialog(
                                frame,
                                "PRODUCT VERIFIED!\n\n" +
                                "Product ID: " + productId + "\n" +
                                "Product Name: " + productName + "\n" +
                                "Serial Number: " + serialNumber + "\n" +
                                "Company: " + company + "\n" +
                                "Price: " + price + "\n" +
                                "Status: " + status
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                frame,
                                "INVALID QR / PRODUCT NOT FOUND"
                        );
                    }

                    rs.close();
                    pst.close();
                    con.close();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Error: " + ex.getMessage()
                    );
                }
            }
        });

        frame.add(title);
        frame.add(scanButton);

        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
