/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class LoginFrame extends JFrame implements ActionListener{
    private JLabel titleLabel;
    private JLabel userLabel;
    private JLabel pwdLabel;
    
    private JButton loginbtn;
    private JButton registerbtn;
    private JButton resetPswBtn;
    private JTextField usertextField;
    private JPasswordField pwdtextField;
    
    public ImageIcon cartIcon = new ImageIcon("cartIcon.png");//create an ImageIcon
    
    private static final String USERNAME_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{1,8}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    
    private JButton showPasswordBtn;  // New button to show/hide password
    private boolean isPasswordVisible = false; // Track the password visibility state
    
    private boolean loginSuccessful = true; 
    private static User loggedInUser; 
    
   
     public LoginFrame(){
       initComponents();
       layoutComponents();
       addComponentsToFrame();
    }
     
    private void initComponents(){
        titleLabel = new JLabel("WELCOME TO SNAP SNACKS E-SHOP");
        userLabel = new JLabel("USERNAME: ");
        pwdLabel = new JLabel("PASSWORD: ");
        usertextField = new JTextField();
        pwdtextField = new JPasswordField();
        loginbtn = new JButton("Sign In");
        registerbtn = new JButton("Sign Up");
        resetPswBtn= new JButton("Forget Password");
        showPasswordBtn = new JButton();  // New button for showing password
        showPasswordBtn.setIcon(new ImageIcon("eyeIcon.png"));  // Load an eye icon
        showPasswordBtn.setToolTipText("Show/Hide Password");
        showPasswordBtn.addActionListener(this);

        
        registerbtn.addActionListener(this);
        resetPswBtn.addActionListener(this);
        loginbtn.addActionListener(this);
        
        usertextField.setToolTipText("Enter a username between 6 and 14 characters long. Include uppercase, lowercase, and a digit.");
        pwdtextField.setToolTipText("Enter a password within 8 characters long. Include uppercase, lowercase, and a digit.");
    }
    
    private void layoutComponents() {
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 28));
        titleLabel.setBounds(150, 50, 900, 200); 

        setupLabel(userLabel, 250);  
        setupLabel(pwdLabel, 400);   

        setupTextField(usertextField, 250); 
        setupTextField(pwdtextField, 400);   

        setupButton(loginbtn, 160, 500, 100, 30);    
        setupButton(registerbtn, 320, 500, 100, 30); 
        setupButton(resetPswBtn, 470, 500, 160, 30); 
        
        showPasswordBtn.setBounds(560, 400, 30, 30);  // Position it beside the password field
        showPasswordBtn.setBorder(null);
        showPasswordBtn.setBackground(new Color(233,230,221));  // Match the frame background color
    }

    private void setupLabel(JLabel label, int yPosition) {
        label.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label.setBounds(180, yPosition, 100, 30);
    }

    private void setupTextField(JTextComponent textField, int yPosition) {
        textField.setPreferredSize(new Dimension(10, 20));
        textField.setBounds(350, yPosition, 200, 30); // Adjusted position and size
        textField.setFont(new Font("MV Boli", Font.PLAIN, 15));
    }

    
    private void setupButton(JButton button, int xPosition, int yPosition,int width, int height) {
        button.setBounds(xPosition, yPosition, width, height);
        button.setFont(new Font("MV Boli", Font.PLAIN, 15));
        button.setForeground(Color.white);
        button.setBackground(new Color(138, 138, 138));
    }
    
    private void addComponentsToFrame() {
        this.setResizable(false);
        this.setSize(850, 750);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Online Shopping Cart");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setIconImage(cartIcon.getImage());
        this.getContentPane().setBackground(new Color(233,230,221)); 
    
        this.add(titleLabel);
        this.add(userLabel);
        this.add(pwdLabel);
        this.add(usertextField);
        this.add(pwdtextField);
        this.add(loginbtn);
        this.add(registerbtn);
        this.add(resetPswBtn);
        this.add(showPasswordBtn);
    }
 
    public void displayLogin(){
        this.getContentPane().removeAll();
        
        this.add(titleLabel);
        this.add(userLabel);
        this.add(pwdLabel);
        this.add(usertextField);
        this.add(pwdtextField);
        this.add(loginbtn);
        this.add(registerbtn);
        this.add(resetPswBtn);
        
        this.revalidate();
        this.repaint();
    }
    
    private void login(){
        String username = usertextField.getText();
        char[] password = pwdtextField.getPassword();
        String passwordString = new String(password); // Convert char[] to String
        loginbtn.setEnabled(true);
        boolean usernameMatch = usernamePattern.matcher(username).matches();
        boolean passwordMatch = passwordPattern.matcher(passwordString).matches();
            
        if (username.isEmpty() || passwordString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", 
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            loginSuccessful = false;
        } 
        else if (!usernameMatch) {
            JOptionPane.showMessageDialog(this, "Username must be between 6 and 14 characters long and include at least one uppercase letter, one lowercase letter, and one digit.", 
                    "Validation Status", JOptionPane.ERROR_MESSAGE);
            loginSuccessful = false;
        }
        else if(!passwordMatch){
            JOptionPane.showMessageDialog(this, "Password must within 8 characters and include uppercase, lowercase and number", 
                    "Validation Status", JOptionPane.ERROR_MESSAGE);
            loginSuccessful = false;
        }
        else if(UserManager.validateCredentials(username, passwordString)){
            loggedInUser = UserManager.getUser(username); // Retrieve the logged-in user and their cart
            
            if(loggedInUser != null){
                JOptionPane.showMessageDialog(this, "You are logged in successfully!", 
                    "Login Status", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); //Close the login frame
                new MyFrame(loggedInUser).setVisible(true);
                loginSuccessful = true;
            }else{
                JOptionPane.showMessageDialog(this, "Logged in failed! User not found.", 
                    "Login Status", JOptionPane.ERROR_MESSAGE);
                loginSuccessful = false;
            }  
        }
        else {
            JOptionPane.showMessageDialog(this, "Logged in failed! Please try again", 
                    "Login Status", JOptionPane.INFORMATION_MESSAGE);
            loginSuccessful = false;
        }
    }
    
     private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            pwdtextField.setEchoChar('*');  // Hide password (use echo char)
            showPasswordBtn.setIcon(new ImageIcon("eyeIcon.png"));  
        } else {
            pwdtextField.setEchoChar((char) 0);  // Show password (no echo char)
            showPasswordBtn.setIcon(new ImageIcon("eyeSlashIcon.png")); 
        }
        isPasswordVisible = !isPasswordVisible;  // Toggle state
    }
    
     public boolean getLoginStatus(){
        return loginSuccessful;
    }
     
    public static User getLoggedInUser(){
        return loggedInUser;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginbtn){
            login();
        } else if(e.getSource() == registerbtn) {
            this.dispose();
            SwingUtilities.invokeLater(() -> new RegisterGUI().setVisible(true));
        } else if(e.getSource()== resetPswBtn){
            this.dispose();
            SwingUtilities.invokeLater(() -> new ResetPswGUI().setVisible(true));
        }else if (e.getSource() == showPasswordBtn) {
            togglePasswordVisibility(); 
        }
    }
}
