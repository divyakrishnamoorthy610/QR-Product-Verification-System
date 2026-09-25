import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRGenerator {

    public static void main(String[] args) {

        try {

            String data = "SN1001";

            String filePath = "../QR_SN1001.png";

            BitMatrix matrix = new MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    300,
                    300
            );

            Path path = FileSystems.getDefault().getPath(filePath);

            MatrixToImageWriter.writeToPath(
                    matrix,
                    "PNG",
                    path
            );

            System.out.println("QR Code generated successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
