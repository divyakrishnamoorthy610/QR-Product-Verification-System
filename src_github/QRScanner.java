import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRScanner {

    public static void main(String[] args) {

        JFrame frame = new JFrame("QR Scanner");

        JLabel title = new JLabel("PRODUCT VERIFICATION");
        title.setBounds(110, 30, 220, 30);

        JButton scanButton = new JButton("Scan QR Code");
        scanButton.setBounds(100, 80, 200, 40);

        scanButton.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {

                try {

                    File file = chooser.getSelectedFile();

                    // Read QR image
                    BufferedImage image = ImageIO.read(file);

                    // Convert image for ZXing
                    LuminanceSource source =
                            new BufferedImageLuminanceSource(image);

                    BinaryBitmap bitmap =
                            new BinaryBitmap(
                                    new HybridBinarizer(source)
                            );

                    // Decode QR
                    Result qrResult =
                            new MultiFormatReader().decode(bitmap);

                    String serialNumber = qrResult.getText();

                    JOptionPane.showMessageDialog(
                            frame,
                            "QR Code Scanned Successfully!\n\n" +
                            "Serial Number: " + serialNumber
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Could not read QR code."
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
