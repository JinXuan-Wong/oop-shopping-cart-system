/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    // List to store registered users
    private static final List<User> users = new ArrayList<>();
    private static User currentUser; // Tracks the logged-in user
    
    public static User getCurrentUser() {
        return currentUser; // Return the current logged-in user
    }
    
    public static void setCurrentUser(User user){
        currentUser = user;
    }
    
    //Check if username is available
    public static boolean isUsernameAvailable(String username){
        for(User user: users){
            if(user.getUsername().equals(username)){
                return false; //Username already exists
            }
        }
        return true; //Username available
    }
    
    //Register a new user
    public static boolean registerUser(String email, String username, String password, String phoneNumber, String address){
        if(isUsernameAvailable(username)){
            users.add(new User(email, username, password, phoneNumber, address));
            return true; //Registration sucessful
        }
        return false; //Registration failed (username already exists)
    }
    
    // Get user by username
    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }
    
    //Validate user credentials
    public static boolean validateCredentials(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // Credentials are correct
            }
        }
        return false; // Invalid credentials
    }

    // Logout the current user
    public static void logoutUser() {
        currentUser = null; // Clear the current user but keep the user list in memory
    }
    
    // Update user's password
    public static boolean updatePassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                return true; // Password updated successfully
            }
        }
        return false; // User not found
    }
}
