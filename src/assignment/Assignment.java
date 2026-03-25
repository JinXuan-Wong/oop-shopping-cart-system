/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import javax.swing.*;

public class Assignment {
    public static void main(String[] args) {
        ProductInventory inventory = new ProductInventory();
        // Pre-register users
        UserManager.registerUser("test@example.com", "Username1", 
                "User123", "0123456789", "123, Test Street, Taman Test123, 12300 Kuala Lumpur"); 
        UserManager.registerUser("try@example.com", "Username2", 
                "User456", "0125678234", "123, Test Street1, Taman Test124, 12300 Kuala Lumpur"); 
        
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        
        // Run the event dispatch thread to wait for login action
        SwingUtilities.invokeLater(() -> {
            boolean loginSuccessful = loginFrame.getLoginStatus();
            if (loginSuccessful) {
                User currentUser = LoginFrame.getLoggedInUser();
                if (currentUser != null) {
                    MyFrame mainFrame = new MyFrame(currentUser);
                    mainFrame.setVisible(true);
                }
            }
        });
    } 
}
