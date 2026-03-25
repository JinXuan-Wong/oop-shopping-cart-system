/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class CartGUI extends JPanel implements ActionListener {
    private MyFrame parentFrame;
    private ShoppingCart cart; //ShoppingCart is an array of CartItem
    private User currentUser;
    private CartItem[] items;
    public JPanel cartPanel;
    private JPanel checkoutPanel;
    private JLabel totalPriceLabel;
    private JButton[] minusBtn;
    private JButton[] plusBtn;
    private JButton[] deleteBtn;
    private JCheckBox[] itemCheckBox;
    private JButton checkoutBtn;
    private JButton nutritionBtn;
    private ImageIcon checkIcon;
    private ImageIcon uncheckIcon;
    
    public CartGUI(MyFrame parentFrame, ShoppingCart cart, User user){
        this.parentFrame = parentFrame;
        this.currentUser = user;
        this.cart = cart; // Use the user's cart
        items = cart.getItems(); //Get items from shopping cart
        
        itemCheckBox = new JCheckBox[items.length];  //create and initialize the checkbox
        checkIcon = new ImageIcon("check.png");
        uncheckIcon = new ImageIcon("uncheck.png");
        for(int i = 0; i < items.length; i++){
            itemCheckBox[i] = new JCheckBox();
            itemCheckBox[i].setSelected(items[i].getChecked()); //update the checkbox whether checked or not
            itemCheckBox[i].setIcon(uncheckIcon);
            itemCheckBox[i].setSelectedIcon(checkIcon);
            itemCheckBox[i].setBackground(new Color(233,230,221));
            itemCheckBox[i].setFocusable(false);
        }
        
        cartPanel = new JPanel();
        cartPanel.setBounds(155,0,690,750);
        cartPanel.setBackground(new Color(233,230,221));
        
        checkoutPanel = new JPanel();
        totalPriceLabel = new JLabel("Total: RM " + String.format("%.2f", calculateTotalPrice()));
        totalPriceLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        
        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBackground(new Color(245,114,81));
        checkoutBtn.setFont(new Font("MV Boli", Font.BOLD, 14));
        checkoutBtn.addActionListener(this);
        
        nutritionBtn = new JButton("Nutrition Info");
        nutritionBtn.setBackground(new Color(245,114,81));
        nutritionBtn.setFont(new Font("MV Boli", Font.BOLD, 14));
        nutritionBtn.addActionListener(this);
        
        checkoutPanel.setLayout(new GridLayout(0,3,40,0));
        checkoutPanel.setBackground(new Color(233,230,221));
        checkoutPanel.add(totalPriceLabel);
        checkoutPanel.add(checkoutBtn);
        checkoutPanel.add(nutritionBtn);
        
        displayCartItems();
    }
    
    public void displayCartItems(){
        cartPanel.removeAll(); //Clear existing items first
        minusBtn = new JButton[items.length];
        plusBtn = new JButton[items.length];
        deleteBtn = new JButton[items.length];
        
        for(int i = 0; i < items.length; i++){
            if(items[i].getQuantity() > 0){ //Only display items that exist (more than 0)
                JPanel itemRowPanel = new JPanel();
                itemRowPanel.setLayout(new GridLayout(1,4,20,0));
                itemRowPanel.setBounds(160,20,600,40);
                itemRowPanel.setBackground(new Color(233,230,221));
                
                itemCheckBox[i].setText(items[i].getProduct().getName());
                itemCheckBox[i].setFocusable(false);
                //JLabel nameLabel = new JLabel(items[i].getProduct().getName());
                JLabel priceLabel = new JLabel("RM " + String.format("%.2f", items[i].getProduct().getPrice()));
                JPanel quantityPanel = new JPanel(); //holding the minusBtn, quantity, and plusBtn
                JLabel quantityLabel = new JLabel(String.valueOf(items[i].getQuantity())); //Changes to String value (only accept String arg)
                minusBtn[i] = new JButton("-");
                plusBtn[i] = new JButton("+");
                JPanel totalPanel = new JPanel(); //holding the subtotal and deleteBtn
                ImageIcon deleteIcon = new ImageIcon("trash.png");
                JLabel subtotalLabel = new JLabel("RM " + String.format("%.2f", items[i].getTotalPrice()));
                deleteBtn[i] = new JButton(deleteIcon);
                
                itemCheckBox[i].setFont(new Font("Arial", Font.PLAIN, 13));
                priceLabel.setFont(new Font("Arial", Font.PLAIN, 13));
                quantityLabel.setHorizontalAlignment(JLabel.CENTER);
                quantityLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
                quantityPanel.setLayout(new GridLayout(1,3));
                quantityPanel.add(minusBtn[i]);
                quantityPanel.add(quantityLabel);
                quantityPanel.add(plusBtn[i]);
                quantityPanel.setBackground(new Color(233,230,221));
                
                subtotalLabel.setHorizontalAlignment(JLabel.CENTER);
                subtotalLabel.setFont(new Font("Arial", Font.PLAIN, 13));
                totalPanel.setLayout(new GridLayout(1,2,10,0));
                totalPanel.add(subtotalLabel);
                totalPanel.add(deleteBtn[i]);
                totalPanel.setBackground(new Color(233,230,221));
                
                itemRowPanel.add(itemCheckBox[i]);
                itemRowPanel.add(priceLabel);
                itemRowPanel.add(quantityPanel);
                itemRowPanel.add(totalPanel);
                
                minusBtn[i].setFont(new Font("MV Boli", Font.BOLD, 20));
                minusBtn[i].setBackground(new Color(196,173,157));
                plusBtn[i].setFont(new Font("MV Boli", Font.BOLD, 20));
                plusBtn[i].setBackground(new Color(196,173,157));
                deleteBtn[i].setBackground(new Color(196,173,157));
                itemCheckBox[i].addActionListener(this);
                minusBtn[i].addActionListener(this);
                plusBtn[i].addActionListener(this);
                deleteBtn[i].addActionListener(this);
                
                cartPanel.add(itemRowPanel);
            }
        }
        
        totalPriceLabel.setText("Total: RM " 
                + String.format("%.2f", calculateTotalPrice()));
        cartPanel.add(checkoutPanel);

        cartPanel.revalidate();
        cartPanel.repaint();
    }
    
    private double calculateTotalPrice() {
        double total = 0.0;
        for (int i = 0; i < items.length; i++) {
            if (itemCheckBox[i].isSelected()) {
                total += items[i].getTotalPrice();
            }
        }
        return total;
    }
    
    private void showNutritionalInfo(){
        int totalCalories = 0;
        int totalProtein = 0;
        int totalSugar = 0;
        
        StringBuilder info = new StringBuilder("Nutritional Information:\n");
        
        for(int i = 0; i < items.length; i++){
            if(items[i].getQuantity() > 0 && itemCheckBox[i].isSelected()){
                Product product = items[i].getProduct();
                if (product instanceof NutritionalFood) {
                    NutritionalFood nutritionalProduct = (NutritionalFood) product;
                    
                    int quantity = items[i].getQuantity();
                
                    info.append(product.toString());
                    info.append("\nQuantity: ").append(quantity);
                    info.append("\n\n------------------------\n");
                            
                    totalCalories += nutritionalProduct.getCalories() * quantity;
                    totalProtein += nutritionalProduct.getProteinContent() * quantity;
                    totalSugar += nutritionalProduct.getSugarContent() * quantity;
                }
            }
        }
        
        if (totalCalories > 0) {
            info.append("Total Calories: ").append(totalCalories).append(" kcal\n");
        }
        if (totalProtein > 0) {
            info.append("Total Protein: ").append(totalProtein).append("g\n");
        }
        if (totalSugar > 0) {
            info.append("Total Sugar: ").append(totalSugar).append("g\n");
        }
        
        NutritionGUI nutritionGUI = new NutritionGUI(info.toString());
        nutritionGUI.showGUI();
    }
    
    @Override 
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        boolean isAtLeastOneSelected = false;
        boolean isCartEmpty = true;

        for (int i = 0; i < items.length; i++) {
            handleProductActions(source, i);

            if (items[i].getQuantity() > 0) {
                isCartEmpty = false;
            }

            if (itemCheckBox[i].isSelected() && items[i].getQuantity() > 0) {
                isAtLeastOneSelected = true;
            }
        }

        if (source == checkoutBtn || source == nutritionBtn) {
            checkCartAndProceed(isCartEmpty, isAtLeastOneSelected, source);
        }

        displayCartItems(); // Refresh UI
    }

    private void handleProductActions(Object source, int i) {
        if (source == minusBtn[i]) { 
            cart.removeProduct(items[i].getProduct());
        } else if (source == plusBtn[i]) {
            if (items[i].getQuantity() < 50) {
                cart.addProduct(items[i].getProduct());
            } else {
                JOptionPane.showMessageDialog(this, "You cannot add more than 50 units of " 
                    + items[i].getProduct().getName(), "Limit Reached", JOptionPane.WARNING_MESSAGE);
            }
        } else if (source == deleteBtn[i]) {
            cart.deleteProduct(items[i].getProduct());
        } else if (source == itemCheckBox[i]) {
            items[i].setChecked(itemCheckBox[i].isSelected());
        }
    }

    private void checkCartAndProceed(boolean isCartEmpty, boolean isAtLeastOneSelected, Object source) {
        if (isCartEmpty) {
            JOptionPane.showMessageDialog(this, "Your shopping cart is empty. Please add items before proceeding to checkout or check nutritional facts.", 
                    "Empty Cart", JOptionPane.WARNING_MESSAGE);
        } else if (!isAtLeastOneSelected) {
            JOptionPane.showMessageDialog(this, "Please select at least one item to proceed to checkout or check nutritional facts.", 
                    "No Items Selected", JOptionPane.WARNING_MESSAGE);
        } else {
            if (source == checkoutBtn) {
                OrderGUI orderGUI = new OrderGUI(currentUser, cart, parentFrame);
                parentFrame.showOrderGUI(orderGUI);
            } else {
                showNutritionalInfo();
            }
        }    
    }
}
