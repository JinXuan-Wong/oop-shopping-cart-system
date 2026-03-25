/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductGUI extends JPanel implements ActionListener {
    private ProductInventory inventory;
    private Product[] products;
    private JPanel productsPanel;
    public JScrollPane productsScrollPane;
    public JTextField searchField;
    public JButton searchBtn;
    private JButton[] addToCartButtons;
    private ShoppingCart cart;
    
    public ProductGUI(ShoppingCart cart) {
        this.cart = cart; // Use the user's cart
        inventory = new ProductInventory();
        products = inventory.getProducts();
        productsPanel = new JPanel();
        addToCartButtons = new JButton[products.length];
        searchField = new JTextField();
        searchBtn = new JButton();
        ImageIcon searchIcon = new ImageIcon("search.png");
        
        productsPanel.setLayout(new GridLayout(0, 3, 10, 10));
        productsPanel.setBounds(160,20,600,1000);
        productsPanel.setBackground(new Color(233,230,221));
        
        // Wrap the productsPanel with a JScrollPane
        productsScrollPane = new JScrollPane(productsPanel);
        productsScrollPane.setBounds(160, 60, 670, 650); // Adjust size as needed
        
        // Disable horizontal scrolling
        productsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Set vertical scroll speed
        JScrollBar verticalScrollBar = productsScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20); // Adjust value for vertical scroll speed
        verticalScrollBar.setBlockIncrement(60); // Adjust value for vertical block scroll speed
        
        //Search bar and button
        searchField.setPreferredSize(new Dimension(400,30));
        searchField.setBounds(180, 10, 400, 30);
        searchField.setFont(new Font("Consolas", Font.PLAIN, 15));
        searchBtn.setIcon(searchIcon);
        searchBtn.setBounds(580, 10, 30, 30);
        searchBtn.addActionListener(this);
        
        displayProducts(products); //Initially display all products
    }
    
    private void displayProducts(Product[] productsToShow) {
        productsPanel.removeAll(); //Clear existing items first
        
        for(int i = 0; i < productsToShow.length; i++){ 
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout());
            
            ImageIcon productImage = new ImageIcon("Product" + (productsToShow[i].getIndex() + 1) + ".png");
            JLabel imageLabel = new JLabel(productImage);
            JPanel imagePanel = new JPanel();
            imagePanel.add(imageLabel);
            
            JLabel infoLabel = new JLabel("<html>" + productsToShow[i].getName() 
                    + "<br>RM " + String.format("%.2f", productsToShow[i].getPrice()) 
                    + "</html>");
            infoLabel.setFont(new Font("Comic Sans", Font.PLAIN, 13));
            infoLabel.setHorizontalAlignment(JLabel.CENTER);
            
            addToCartButtons[i] = new JButton("Add to Cart");
            addToCartButtons[i].setActionCommand(String.valueOf(productsToShow[i].getIndex())); //Set the unique identifier (index) to correctly identify the item added to cart
            addToCartButtons[i].addActionListener(this);
            addToCartButtons[i].setFont(new Font("MV Boli", Font.BOLD, 13));
            addToCartButtons[i].setBackground(new Color(245,114,81));
            
            productPanel.add(imagePanel, BorderLayout.NORTH);
            productPanel.add(infoLabel, BorderLayout.CENTER);
            productPanel.add(addToCartButtons[i], BorderLayout.SOUTH);
            
            productsPanel.add(productPanel);      
        }
        productsPanel.revalidate();
        productsPanel.repaint();
    }
    
    private void searchProducts(){
        String query = searchField.getText().toLowerCase();
        List<Product> filteredProductsList = new ArrayList<Product>();
        
        for(Product product : products){
            if(product.getName().toLowerCase().contains(query) || 
                    product.getCategory().toLowerCase().contains(query)){
                filteredProductsList.add(product);
            }
        }
        
        Product[] filteredProducts = filteredProductsList.toArray(new Product[0]);
        displayProducts(filteredProducts);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchBtn){
            searchProducts();
        }else{
            int index = Integer.parseInt(e.getActionCommand());
            Product selectedProduct = products[index];
            CartItem[] cartItems = cart.getItems();
            
            //Find the corresponding CartItem in ShoppingCart
            CartItem cartItem = null;
            for (CartItem item : cartItems){
                if(item.getProduct().getName().equals(selectedProduct.getName())){
                    cartItem = item;
                }
            }
            
            if(cartItem != null){
                if(cartItem.getQuantity() < 50){
                    cart.addProduct(selectedProduct);
                    JOptionPane.showMessageDialog(this, products[index].getName() 
                            + " added to cart!");
                } else {
                    JOptionPane.showMessageDialog(this, "You cannot add more than 50 units of " 
                            + selectedProduct.getName(), "Limit Reached", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
