import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Admin Login");

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);

        JTextField userField = new JTextField();
        userField.setBounds(150, 50, 150, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 100, 150, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);


        loginButton.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            String url = "jdbc:mysql://localhost:3306/product_verification_db";
            String dbUsername = "root";
            String dbPassword = "YOUR_MYSQL_PASSWORD";

            try {

                Connection con = DriverManager.getConnection(
                        url,
                        dbUsername,
                        dbPassword
                );

                String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Login Successful!"
                    );

                    // Close Login window
                    frame.dispose();

                    // Open Admin Dashboard
                    AdminDashboard.main(new String[]{});

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid Username or Password"
                    );
                }

                rs.close();
                pst.close();
                con.close();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Database Error: " + ex.getMessage()
                );
            }
        });


        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);

        frame.setSize(400, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
