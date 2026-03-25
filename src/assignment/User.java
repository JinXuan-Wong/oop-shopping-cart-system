/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private ShoppingCart cart;
    private List<Order> orderHistory;
    private String profilePicPath;
    
    //constructor
    public User(String email, String username, String password, String phoneNumber, String address){
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cart = new ShoppingCart(); //Initialize the cart
        this.orderHistory = new ArrayList<>();
    }
    
    //cosntructor with no args
    public User(){
        this.email = "";
        this.username = "";
        this.password = "";
        this.phoneNumber = "";
        this.address = "";
        this.cart = new ShoppingCart();
    }
    
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    
    public ShoppingCart getCart() {
        return cart;
    }
    
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
    
    public void addOrder(Order order) {
        orderHistory.add(order);
    }
    
    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }
    
    @Override
    public String toString(){
        return "Email: " + this.email +
                "\n Username: " + this.username +
                "\n Phone number: " + this.phoneNumber +
                "\n Address: " + this.address; 
    }
}
