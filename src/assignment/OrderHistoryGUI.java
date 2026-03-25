/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import javax.swing.*;
import java.awt.*;

public class OrderHistoryGUI {
    private JPanel historyPanel;
    private JLabel historyLabel;
    private JScrollPane historyScrollPane;
    
    public OrderHistoryGUI(User user){
        historyPanel = new JPanel();
        historyPanel.setLayout(new BorderLayout());
        
        historyLabel = new JLabel();
        historyLabel.setVerticalAlignment(JLabel.TOP); // Align text to the top
        historyLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
        updateOrderHistory(user);
        
        historyPanel.add(new JLabel("Order History"));
        historyPanel.add(historyLabel,BorderLayout.CENTER);
        
        historyScrollPane = new JScrollPane(historyPanel);
        historyScrollPane.setBounds(160, 30, 670, 650); // Adjust size as needed
        
        // Disable horizontal scrolling
        historyScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Set vertical scroll speed
        JScrollBar verticalScrollBar = historyScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20); // Adjust value for vertical scroll speed
        verticalScrollBar.setBlockIncrement(60); // Adjust value for vertical block scroll speed
    }
    
    public void updateOrderHistory(User user) {
        // Update the order history with the new data
        historyLabel.setText("<html>" + getOrderHistoryText(user) + "</html>");
    }
    
    private String getOrderHistoryText(User user) {
        StringBuilder ordersDetails = new StringBuilder();
        
        if (user.getOrderHistory() == null || user.getOrderHistory().isEmpty()) {
            ordersDetails.append("No orders found.");
        } else {
            for (Order order : user.getOrderHistory()) {
                ordersDetails.append(order.toString().replace("\n", "<br>")); //Covert to HTML for GUI display
                ordersDetails.append("<br>-------------------------------------------<br>");
            }
        }
        return ordersDetails.toString();
    }
    
    public JScrollPane getHistoryPanel(){
        return historyScrollPane;
    }
}
