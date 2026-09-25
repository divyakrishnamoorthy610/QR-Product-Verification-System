import javax.swing.*;

public class AdminDashboard {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Admin Dashboard");

        JLabel title = new JLabel("ADMIN DASHBOARD");
        title.setBounds(130, 30, 200, 30);

        JButton addButton = new JButton("Add Product");
        addButton.setBounds(120, 80, 160, 40);

        JButton viewButton = new JButton("View Products");
        viewButton.setBounds(120, 130, 160, 40);

        JButton updateButton = new JButton("Update Product");
        updateButton.setBounds(120, 180, 160, 40);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(120, 230, 160, 40);

        JButton verifyButton = new JButton("Verify Product");
        verifyButton.setBounds(120, 280, 160, 40);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(120, 330, 160, 40);


        // Add Product button
        addButton.addActionListener(e -> {
            AddProduct.main(new String[]{});
        });


        // View Products button
        viewButton.addActionListener(e -> {
            ViewProducts.main(new String[]{});
        });


        // Update Product button
        updateButton.addActionListener(e -> {
            UpdateProduct.main(new String[]{});
        });


        // Delete Product button
        deleteButton.addActionListener(e -> {
            DeleteProduct.main(new String[]{});
        });


        // Verify Product button
        verifyButton.addActionListener(e -> {
            ProductVerification.main(new String[]{});
        });


        // Logout button
        logoutButton.addActionListener(e -> {
            frame.dispose();
            Login.main(new String[]{});
        });


        frame.add(title);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(verifyButton);
        frame.add(logoutButton);

        frame.setSize(400, 430);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
