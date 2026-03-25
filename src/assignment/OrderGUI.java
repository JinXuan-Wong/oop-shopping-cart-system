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

public class OrderGUI extends JPanel implements ActionListener {
    private MyFrame parentFrame;
    private ShoppingCart cart;
    private CartItem[] items;
    private User currentUser;
    private double grandTotal = 0.0;
    private double discountApplied = 0.0;
    private double sstAmount = 0.0;
    private List<CartItem> selectedItems = new ArrayList<>();
    
    public JScrollPane orderScrollPanel;
    private JPanel orderPanel;
    private JLabel addressLabel;
    private JLabel subtotalLabel;
    private JLabel deliveryFeeLabel;
    private JLabel discountLabel;
    private JLabel sstLabel;
    private JLabel grandTotalLabel;
    private JRadioButton tngRadio;
    private JRadioButton fpxRadio;
    private JRadioButton codRadio;
    private JButton confirmOrderBtn;
    
    private static final double DELIVERY_FEE = 5.00;
    private static final double SST_RATE = 0.08; // SST rate of 8%
    private static final String[] DISCOUNT_CODES = {"DISCOUNT20", "DISCOUNT10", "FREEDELIVERY", "NO"};
    private static final double[] DISCOUNT_PERCENTAGES = {0.20, 0.10, 0.00, 0.00};
    
    public OrderGUI(User user, ShoppingCart cart, MyFrame parentFrame){
        this.parentFrame = parentFrame;
        this.currentUser = user;
        this.cart = cart;
        items = cart.getItems(); //Get items from shopping cart
        
        for (CartItem item : items) {
            if (item.getQuantity() >= 1 && item.getChecked()) {
                //create a new array for selected items in cart
                selectedItems.add(item);
            }
        }
        
        orderPanel = new JPanel();
        orderPanel.setBounds(155,0,690,750);
        orderPanel.setBackground(new Color(233,230,221));
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        
        // Panel for order details
        JPanel orderDetailsPanel = new JPanel();
        orderDetailsPanel.setLayout(new GridLayout(selectedItems.size() + 1, 4, 10, 10));
        orderDetailsPanel.setBackground(new Color(233, 230, 221));
        
        // Adding headers
        orderDetailsPanel.add(new JLabel("Item Name"));
        orderDetailsPanel.add(new JLabel("Unit Price"));
        orderDetailsPanel.add(new JLabel("Quantity"));
        orderDetailsPanel.add(new JLabel("Subtotal"));
        
        double subtotal = 0.0;
        for (CartItem item : selectedItems) {
            orderDetailsPanel.add(new JLabel(item.getProduct().getName()));
            orderDetailsPanel.add(new JLabel("RM " + String.format("%.2f", item.getProduct().getPrice())));
            orderDetailsPanel.add(new JLabel(String.valueOf(item.getQuantity())));
            double itemSubtotal = item.getTotalPrice();
            orderDetailsPanel.add(new JLabel("RM " + String.format("%.2f", itemSubtotal)));
            subtotal += itemSubtotal;
        }
        
        discountApplied = subtotal;
        //Prompt for discount code if applicable
        if (subtotal >= 200) {
            grandTotal = applyDiscount(subtotal);
        } else {
            grandTotal = subtotal; //No discount applied
        }
        discountApplied -= grandTotal;
        
        //Delivery Fee
        grandTotal += DELIVERY_FEE;
        
        //SST Tax
        sstAmount = subtotal * SST_RATE;
        grandTotal += sstAmount;
        
        // Payment methods
        tngRadio = new JRadioButton("Touch N Go");
        fpxRadio = new JRadioButton("FPX");
        codRadio = new JRadioButton("Cash On Delivery");
        
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(tngRadio);
        paymentGroup.add(fpxRadio);
        paymentGroup.add(codRadio);
        
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridLayout(3, 1));
        paymentPanel.setBackground(new Color(233, 230, 221));
        paymentPanel.add(tngRadio);
        paymentPanel.add(fpxRadio);
        paymentPanel.add(codRadio);
        
        addressLabel = new JLabel("Address: " + currentUser.getAddress());
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        subtotalLabel = new JLabel("Subtotal: RM " + String.format("%.2f", subtotal));
        subtotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        deliveryFeeLabel = new JLabel("Delivery Fee: + RM " + String.format("%.2f", DELIVERY_FEE)); // Example delivery fee
        deliveryFeeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        discountLabel = new JLabel("Discount: - RM " + String.format("%.2f", discountApplied)); // New label for discount
        discountLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        sstLabel = new JLabel("SST (8%): + RM " + String.format("%.2f", sstAmount));
        sstLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        grandTotalLabel = new JLabel("Grand Total: RM " + String.format("%.2f", grandTotal));
        grandTotalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        confirmOrderBtn = new JButton("Place Order");
        confirmOrderBtn.setBackground(new Color(245, 114, 81));
        confirmOrderBtn.setFont(new Font("MV Boli", Font.BOLD, 14));
        confirmOrderBtn.addActionListener(this);
        
        orderPanel.add(orderDetailsPanel);
        orderPanel.add(paymentPanel);
        orderPanel.add(addressLabel);
        orderPanel.add(subtotalLabel);
        orderPanel.add(deliveryFeeLabel);
        orderPanel.add(discountLabel);
        orderPanel.add(sstLabel);
        orderPanel.add(grandTotalLabel);
        orderPanel.add(confirmOrderBtn);
        orderPanel.revalidate();
        orderPanel.repaint();
        
        // Wrap the productsPanel with a JScrollPane
        orderScrollPanel = new JScrollPane(orderPanel);
        orderScrollPanel.setBounds(160, 20, 670, 650);
        
        // Disable horizontal scrolling
        orderScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Set vertical scroll speed
        JScrollBar verticalScrollBar = orderScrollPanel.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20); // Adjust value for vertical scroll speed
        verticalScrollBar.setBlockIncrement(60); // Adjust value for vertical block scroll speed
    }
    
    private double applyDiscount(double total) {
        boolean validCode = false;
        while(!validCode){
            // Prompt user for a discount code
            String discountCode = JOptionPane.showInputDialog(this, "Enter discount code (if any /if no enter 'NO'):");
            if (discountCode.equalsIgnoreCase("NO")) {
                return total; // Return original total if no discount applied
            }
            // Check if the discount code is valid
            for (int i = 0; i < DISCOUNT_CODES.length; i++) {
                if (DISCOUNT_CODES[i].equalsIgnoreCase(discountCode)) {
                    validCode = true;
                    
                    if(DISCOUNT_CODES[i].equalsIgnoreCase("FREEDELIVERY")){
                        return total - DELIVERY_FEE;
                    } else {
                        return total * (1 - DISCOUNT_PERCENTAGES[i]);
                    }
                }
            }
            // If no valid code, inform the user
            JOptionPane.showMessageDialog(this, "Invalid discount code. Please try again", "Discount Code", JOptionPane.INFORMATION_MESSAGE);
        }      
        return total; 
    }
    
    class PhoneNumberValidator{
        public static boolean isValidPhoneNumber(String phoneNumber) {
            // Remove non-numeric characters
            phoneNumber = phoneNumber.replaceAll("\\D", "");
            
            // Check if phone number starts with '0' and has a length of 10 or 11 digits
            if(!phoneNumber.startsWith("01") || phoneNumber.length() != 10 && phoneNumber.length() != 11) {
                return false;
            }
            return true;
        }
    }
    
    class PINValidator {
        // Validate that the PIN is exactly 6 digits
        public static boolean isValidPIN(String pin) {
            return pin != null && pin.matches("\\d{6}"); // 6 digits
        }
    }
    
    class CardValidator {
        public static boolean isValidVisaCard(String cardNumber) {
            cardNumber = cardNumber.replaceAll("\\D","");  //Remove any non-digit characters
            cardNumber = cardNumber.trim();
            
            // Check if cardNumber is not empty and contains only digits
            if (cardNumber.isEmpty() || !cardNumber.matches("\\d+")) {
                throw new IllegalArgumentException("Card number must contain only digits.");
            }

            // Check if cardNumber starts with '4' and length of 13 or 16
            if (!cardNumber.startsWith("4") || (cardNumber.length() != 13 && cardNumber.length() != 16)) {
                throw new IllegalArgumentException("Invalid Visa card number. Card number should be 13 or 16 digits and start with '4'.");
            }
            
            return true;
        }
        
        public static String validateExpiryDate(String expiryDate) {
            // Check if expiry date matches the format MM/YY
            if (!expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                return "Expiry date format is invalid. Please enter in MM/YY format.";
            }

            // Extract month and year from expiry date
            String[] parts = expiryDate.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]) + 2000; // Convert YY to YYYY

            // Get the current month and year
            java.util.Calendar current = java.util.Calendar.getInstance();
            int currentYear = current.get(java.util.Calendar.YEAR);
            int currentMonth = current.get(java.util.Calendar.MONTH) + 1;

            // Check if the expiry date is not in the past
            if (year < currentYear || (year == currentYear && month < currentMonth)) {
                return "The expiry date has already passed. Please check and enter a valid expiry date.";
            }

            return ""; // No error
        }
        
        public static boolean isValidCVV(String cvv) {
            // Check if CVV is 3 or 4 digits
            return cvv.matches("\\d{3,4}");
        }
    }
    
    private boolean showTNGInputDialog() throws Exception {
        try{
            // Prompt for phone number
            String phoneNumber = JOptionPane.showInputDialog(this, "Enter your phone number:");
            if(phoneNumber == null){
                throw new Exception("Payment cancelled by user.");
            }

            if (PhoneNumberValidator.isValidPhoneNumber(phoneNumber)) {
                // Prompt for 6-digit PIN
                JPasswordField pinField = new JPasswordField(6);
                int option = JOptionPane.showConfirmDialog(this, pinField, "Enter Your TNG 6-digit PIN:", 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(option == JOptionPane.OK_OPTION){
                    String pin = new String(pinField.getPassword());
                    if (PINValidator.isValidPIN(pin)) { // Validate that it is a 6-digit number
                        JOptionPane.showMessageDialog(this, "PIN validated successfully.");
                        return true; //Payment successful
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid PIN. Please enter a 6-digit PIN.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return false; //Payment failed
                    }
                } else {
                    // User canceled the operation
                    throw new Exception("Payment cancelled by user.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid phone number. Please enter a valid phone number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false; // Payment failed
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Payment Cancelled", JOptionPane.INFORMATION_MESSAGE);
            return false; 
        }
    }
    
    private boolean showCardInputDialog(){
        boolean valid = false;
        while (!valid) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Enter Credit/Debit Card Information:"));
            JTextField cardNumberField = new JTextField();
            JTextField expiryDateField = new JTextField();
            JTextField cvvField = new JTextField();
            JTextField nameOnCardField = new JTextField();
            
            cardNumberField.setToolTipText("Enter Visa card number starting with '4'. Should be 13 or 16 digits. E.g. 41111 2222 3333 4444");
            expiryDateField.setToolTipText("Expiry date in MM/YY format, e.g. 12/26 ");
            cvvField.setToolTipText("Enter the 3 or 4-digit CVV number located on the back of your card.");
            nameOnCardField.setToolTipText("Enter the name exactly as it appears on your card.");

            panel.add(new JLabel("Card Number(e.g.41111 2222 3333):"));
            panel.add(cardNumberField);
            panel.add(new JLabel("Expiry Date (MM/YY):"));
            panel.add(expiryDateField);
            panel.add(new JLabel("CVV:"));
            panel.add(cvvField);
            panel.add(new JLabel("Name on Card:"));
            panel.add(nameOnCardField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Credit/Debit Card Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                String cvv = cvvField.getText();
                String nameOnCard = nameOnCardField.getText();
                
                try {
                    // Validate card number
                    if(cardNumber.isEmpty() || cvv.isEmpty() || cvv.isEmpty() || nameOnCard.isEmpty()){
                        throw new IllegalArgumentException("All fields must be filled.");
                    }
                    if (!CardValidator.isValidVisaCard(cardNumber)) {
                        throw new IllegalArgumentException("Card number is invalid.");
                    }
                    String expiryDateError = CardValidator.validateExpiryDate(expiryDate);
                    if (!expiryDateError.isEmpty()) {
                        throw new IllegalArgumentException(expiryDateError);
                    }
                    if (!CardValidator.isValidCVV(cvv)) {
                        throw new IllegalArgumentException("CVV is invalid. Please enter a 3 or 4-digit number.");
                    }
                    
                    valid = true; //Exit loop if all validation pass
                    JOptionPane.showMessageDialog(this, "Card details are valid. Proceeding with order.", "Validation Successful", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                } 
            } else {
                // User cancelled the dialog
                break;
            }
        }
        return false; // Payment failed if not valid
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmOrderBtn) {
            // Handle order confirmation
            String paymentMethod = "";
            boolean paymentSuccessful = false;
            
            try{
                if (tngRadio.isSelected()) {
                    paymentMethod = "Touch N Go";
                    paymentSuccessful = showTNGInputDialog();
                } else if (fpxRadio.isSelected()) {
                    paymentMethod = "FPX";
                    paymentSuccessful = showCardInputDialog();
                } else if (codRadio.isSelected()) {
                    paymentMethod = "Cash On Delivery";
                    paymentSuccessful = true;
                }
                
                if(paymentMethod.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please select a payment method before check out.");
                }else if(paymentSuccessful){
                    //Successfully create a order
                    Order order = new Order(currentUser.getUsername(), selectedItems, discountApplied, sstAmount, grandTotal, paymentMethod);
                    currentUser.addOrder(order); // Add order to user's history

                    // Notify user of successful order
                    JOptionPane.showMessageDialog(this, "Order confirmed! Thank you for shopping with us.");

                    //Clear the selectedItems bought from user's shopping cart
                    for(CartItem item : selectedItems){
                        cart.deleteProduct(item.getProduct());
                    }

                    //Remove OrderGUI from MyFrame
                    parentFrame.hideOrderGUI();

                }else{
                    JOptionPane.showMessageDialog(this, "Payment failed. Please try again.", "Payment Error", JOptionPane.ERROR_MESSAGE);
                } 
            } catch (Exception ex) {
                // Handle exception thrown by showTNGInputDialog() method
                JOptionPane.showMessageDialog(parentFrame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
