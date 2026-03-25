/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class MyFrame extends JFrame implements ActionListener{
    private JButton cartButton;
    private JButton buyButton;
    private JButton ordHistButton;
    private JButton profileButton;
    private JLabel cartLabel;
    private JLabel buyLabel;
    private JLabel ordHistLabel;
    private JLabel profileLabel;
    private JPanel menupanel;
    private ProductGUI productGUI;
    private CartGUI cartGUI;
    private OrderHistoryGUI orderHistoryGUI;
    private User currentUser;
    private ShoppingCart cart; // Store the current user's cart
    private JScrollPane currentOrderScrollPanel; //OrderGUI created in CartGUI
    
    public MyFrame(User user){
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        this.currentUser = user;
        this.cart = user.getCart(); // Get the current user's cart
        cartButton = new JButton();
        buyButton = new JButton();
        ordHistButton = new JButton();
        profileButton = new JButton();
        cartLabel = new JLabel();
        buyLabel = new JLabel();
        ordHistLabel = new JLabel();
        profileLabel= new JLabel();
        menupanel = new JPanel();
        ImageIcon cartIcon = new ImageIcon("cartIcon.png");//create an ImageIcon
        ImageIcon buyIcon = new ImageIcon("shopBag.png");
        ImageIcon orderHistoryIcon = new ImageIcon("order.png");
        ImageIcon profileIcon = new ImageIcon("user-id.png");
        
        productGUI = new ProductGUI(cart); // Pass the cart to ProductGUI
        cartGUI = new CartGUI(this, cart, currentUser); // Pass the cart and user to ProductGUI
        orderHistoryGUI = new OrderHistoryGUI(currentUser); // Initialize OrderHistoryGUI
        
        menupanel.setBackground(new Color(245,114,81));
        menupanel.setBounds(0,0,150,750);
        menupanel.setLayout(null);
        menupanel.add(cartButton);
        menupanel.add(buyButton);
        menupanel.add(ordHistButton);
        menupanel.add(profileButton);
        
        cartLabel.setText("View Cart");
        cartLabel.setIcon(cartIcon);
        cartLabel.setHorizontalTextPosition(JLabel.CENTER);
        cartLabel.setVerticalTextPosition(JLabel.BOTTOM);
        cartLabel.setFont(new Font("MV Boli", Font.PLAIN, 13));
        cartLabel.setBounds(20, 20, 100, 100);
        
        buyLabel.setText("Product");
        buyLabel.setIcon(buyIcon);
        buyLabel.setHorizontalTextPosition(JLabel.CENTER);
        buyLabel.setVerticalTextPosition(JLabel.BOTTOM);
        buyLabel.setFont(new Font("MV Boli", Font.PLAIN, 13));
        
        ordHistLabel.setText("OrderHistory");
        ordHistLabel.setIcon(orderHistoryIcon);
        ordHistLabel.setHorizontalTextPosition(JLabel.CENTER);
        ordHistLabel.setVerticalTextPosition(JLabel.BOTTOM);
        ordHistLabel.setFont(new Font("MV Boli", Font.PLAIN, 10));
        
        profileLabel.setText("Profile"); // Set the text for profileLabel
        profileLabel.setIcon(profileIcon); // Set the icon for profileLabel
        profileLabel.setHorizontalTextPosition(JLabel.CENTER);
        profileLabel.setVerticalTextPosition(JLabel.BOTTOM);
        profileLabel.setFont(new Font("MV Boli", Font.PLAIN, 13));
        
        cartButton.setBounds(20, 20, 100, 100);
        cartButton.setBackground(new Color(233,230,221));
        cartButton.addActionListener(this);
        cartButton.add(cartLabel);
        
        buyButton.setBounds(20, 140, 100, 100);
        buyButton.setBackground(new Color(233,230,221));
        buyButton.addActionListener(this);
        buyButton.add(buyLabel);
        
        ordHistButton.setBounds(20, 260, 100, 100);
        ordHistButton.setBackground(new Color(233,230,221));
        ordHistButton.addActionListener(this);
        ordHistButton.add(ordHistLabel);
        
        profileButton.setBounds(20, 380, 100, 100);
        profileButton.setBackground(new Color(233,230,221));
        profileButton.addActionListener(this);
        profileButton.add(profileLabel);
        
        this.setResizable(false); //prevent frame from being resize
        this.setSize(850, 750);
        this.setLayout(null);
        this.setTitle("Online Shopping Cart");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setIconImage(cartIcon.getImage());
        this.getContentPane().setBackground(new Color(233,230,221)); //change color of background
        this.add(menupanel);
    }
    
    public void showOrderGUI(OrderGUI orderGUI) { //This method is called from CartGUI
        this.remove(cartGUI.cartPanel);
        this.add(orderGUI.orderScrollPanel);
        currentOrderScrollPanel = orderGUI.orderScrollPanel; 
        this.revalidate();
        this.repaint();
    }
    
    public void hideOrderGUI() { //This method is called from OrderGUI
        if (currentOrderScrollPanel != null) {
            this.remove(currentOrderScrollPanel); 
            currentOrderScrollPanel = null; // Reset the current panel tracker
        }
        this.revalidate();
        this.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        // Hide OrderGUI whenever another button is clicked
        hideOrderGUI();

        if(e.getSource() == cartButton){ //getSource()--> determine which button was clicked
            this.remove(productGUI.productsScrollPane);
            this.remove(productGUI.searchField);
            this.remove(productGUI.searchBtn);
            this.remove(orderHistoryGUI.getHistoryPanel());
            cartGUI.displayCartItems(); // Refresh cart items before displaying
            this.add(cartGUI.cartPanel);
            this.revalidate();
            this.repaint();
        }
        if(e.getSource() == buyButton){
            this.remove(cartGUI.cartPanel);
            this.remove(orderHistoryGUI.getHistoryPanel());
            this.add(productGUI.productsScrollPane);
            this.add(productGUI.searchField);
            this.add(productGUI.searchBtn);
            this.revalidate();
            this.repaint();
        }
        if(e.getSource() == ordHistButton){
            this.remove(cartGUI.cartPanel);
            this.remove(productGUI.productsScrollPane);
            this.remove(productGUI.searchField);
            this.remove(productGUI.searchBtn);
            orderHistoryGUI.updateOrderHistory(currentUser); // Update order history before showing
            this.add(orderHistoryGUI.getHistoryPanel());
            this.revalidate();
            this.repaint();
        }
        if (e.getSource() == profileButton) {
            this.dispose(); // Close the current frame
            SwingUtilities.invokeLater(() -> new UserProfileGUI(currentUser).setVisible(true)); // Pass the user object to UserProfileGUI
        }
    }
}
