/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private static int orderCount = 0; //To generate unique order IDs
    private int orderId;
    private String username;
    private List<CartItem> orderedItems;
    private double discountAmount;
    private double sstAmount;
    private double totalAmount;
    private String paymentMethod;
    private Date orderDate;
    
    public Order(String username, List<CartItem> orderedItems, double discountAmount, double sstAmount, double totalAmount, String paymentMethod) {
        this.orderId = ++orderCount;
        this.username = username;
        this.orderedItems = new ArrayList<>();
        for (CartItem item : orderedItems) {
            // Create a new CartItem with the same product and quantity
            this.orderedItems.add(new CartItem(item.getProduct(), item.getQuantity()));
        }
        this.discountAmount = discountAmount;
        this.sstAmount = sstAmount;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.orderDate = new Date(); // Set the current date and time
    }
    
    public Order(){
        this.orderId = ++orderCount;
        this.username = "";
        this.orderedItems = null;
        this.totalAmount = 0;
        this.discountAmount = 0;
        this.sstAmount = 0;
        this.paymentMethod = "";
        this.orderDate = new Date();
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public List<CartItem> getOrderedItems() {
        return orderedItems;
    }
    
    public double getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(double discountAmount){
        this.discountAmount = discountAmount;
    }

    public double getSstAmount() {
        return sstAmount;
    }
    
    public void setSstAmount(double sstAmount){
        this.sstAmount = sstAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate){
        this.orderDate = orderDate;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderId).append("\n");
        orderDetails.append("Order Date: ").append(sdf.format(orderDate)).append("\n");
        orderDetails.append("Payment Method: ").append(paymentMethod).append("\n");
        orderDetails.append("Ordered Items:\n");
        for (CartItem item : orderedItems) {
            orderDetails.append(" - ").append(item.getProduct().getName());
            orderDetails.append(" (Unit Price: RM ").append(String.format("%.2f", item.getProduct().getPrice()));
            orderDetails.append(", Quantity: ").append(String.valueOf(item.getQuantity()));
            orderDetails.append(", Subtotal: RM ").append(String.format("%.2f", item.getTotalPrice()));
            orderDetails.append(")\n");
        }
        orderDetails.append("Discount: RM ").append(String.format("%.2f", discountAmount)).append("\n"); // Display discount amount
        orderDetails.append("SST (8%): RM ").append(String.format("%.2f", sstAmount)).append("\n"); // Display SST amount
        orderDetails.append("Total Amount: RM ").append(String.format("%.2f", totalAmount)).append("\n");
        
        return orderDetails.toString();
    }
}
