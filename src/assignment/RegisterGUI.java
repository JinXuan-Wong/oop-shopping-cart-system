 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.text.JTextComponent;

public class RegisterGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPswField;
    private JTextField phoneField;
    private JTextArea addressArea;
    
    private JButton submitButton;
    private JButton resetButton;
    private JButton backButton;
    
    private JLabel showPasswordIcon;
    private JLabel showConfirmPasswordIcon;

    private ImageIcon eyeOpenIcon;
    private ImageIcon eyeClosedIcon;
    
    public ImageIcon cartIcon = new ImageIcon("cartIcon.png");//create an ImageIcon
    
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final String USERNAME_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,14}$";
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{1,8}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private static final String PHONE_PATTERN = "^01\\d{8,9}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);
    
    public RegisterGUI() {
        initComponents();
        layoutComponents();
        addComponentsToFrame();
    }
    
    private void initComponents() {
        titleLabel = new JLabel("REGISTER ACCOUNT");
        emailLabel = new JLabel("EMAIL:");
        usernameLabel = new JLabel("USERNAME:");
        passwordLabel = new JLabel("PASSWORD:");
        confirmPasswordLabel = new JLabel("CONFIRM PASSWORD:");
        addressLabel = new JLabel("ADDRESS:");
        phoneNumberLabel = new JLabel("PHONE NUMBER:");

        emailField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPswField = new JPasswordField();
        phoneField = new JTextField();
        addressArea = new JTextArea();

        submitButton = new JButton("SUBMIT");
        resetButton = new JButton("RESET");
        backButton = new BackButton(this);
        
        submitButton.addActionListener(this);
        resetButton.addActionListener(this);
        
        emailField.setToolTipText("Enter your email address (e.g., example@domain.com)");
        usernameField.setToolTipText("Enter a username between 6 and 14 characters long. Include uppercase, lowercase, and a digit.");
        passwordField.setToolTipText("Enter a password within 8 characters long. Include uppercase, lowercase, and a digit.");
        confirmPswField.setToolTipText("Enter the same password that you have typed before");
        phoneField.setToolTipText("Enter your phone number that start with '01' as 10/11 digits (e.g.0123456789).");
        addressArea.setToolTipText("Enter your full address including street, city, and zip code.");
        
        try {
            cartIcon = new ImageIcon("cartIcon.png");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading cart icon", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        eyeOpenIcon = new ImageIcon("eyeIcon.png");  
        eyeClosedIcon = new ImageIcon("eyeSlashIcon.png"); 
        showPasswordIcon = new JLabel(eyeClosedIcon);
        showPasswordIcon.setBounds(640, 340, 30, 30);
        showPasswordIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePasswordVisibility(passwordField, showPasswordIcon);
            }
        });

        showConfirmPasswordIcon = new JLabel(eyeClosedIcon);
        showConfirmPasswordIcon.setBounds(640, 410, 30, 30);
        showConfirmPasswordIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePasswordVisibility(confirmPswField, showConfirmPasswordIcon);
            }
        });
    }
    private void setupLabel(JLabel label, int yPosition) {
        label.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label.setBounds(150, yPosition, 400, 100);
    }
    
    private void setupTextField(JTextComponent textField, int yPosition) {
        textField.setPreferredSize(new Dimension(10, 20));
        textField.setBounds(330, yPosition, 300, 30);
        textField.setFont(new Font("MV Boli", Font.PLAIN, 15));
    }
    
    private void setupTextArea(JTextArea textArea, int yPosition, int width, int height) {
        textArea.setBounds(300, yPosition, width, height);
        textArea.setFont(new Font("MV Boli", Font.PLAIN, 15));
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    private void setupButton(JButton button, int xPosition, int yPosition) {
        button.setBounds(xPosition, yPosition, 100, 30);
        button.setFont(new Font("MV Boli", Font.PLAIN, 15));
        button.setForeground(Color.white);
        button.setBackground(new Color(138, 138, 138));
    }
    
    private void layoutComponents() {
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        titleLabel.setBounds(270, 100, 400, 30);

        setupLabel(emailLabel, 150);
        setupLabel(usernameLabel, 220);
        setupLabel(passwordLabel, 310);
        setupLabel(confirmPasswordLabel, 380);
        setupLabel(phoneNumberLabel, 450);
        setupLabel(addressLabel, 510);

        setupTextField(emailField, 180);
        setupTextField(usernameField, 250);
        setupTextField(passwordField, 340);
        setupTextField(confirmPswField, 410);
        setupTextField(phoneField, 480);
        setupTextArea(addressArea, 530, 300, 100);

        setupButton(submitButton, 250, 670);
        setupButton(resetButton, 500, 670); 
    }
    
    private void addComponentsToFrame() {
        this.setResizable(false); //prevent frame from being resize
        this.setSize(850, 750);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Online Shopping Cart");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setIconImage(cartIcon.getImage());
        this.getContentPane().setBackground(new Color(233,230,221)); //change color of background
    
        this.add(titleLabel);
        this.add(emailLabel);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(confirmPasswordLabel);
        this.add(phoneNumberLabel);
        this.add(addressLabel);
        this.add(emailField);
        this.add(usernameField);
        this.add(passwordField);
        this.add(confirmPswField);
        this.add(phoneField);
        this.add(addressArea);
        this.add(submitButton);
        this.add(resetButton);
        this.add(backButton);
        this.add(showPasswordIcon);
        this.add(showConfirmPasswordIcon);
    }
    
    private void togglePasswordVisibility(JPasswordField passwordField, JLabel iconLabel) {
        if (passwordField.getEchoChar() == '\u2022') {  
            passwordField.setEchoChar((char) 0);  //show the password 
            iconLabel.setIcon(eyeClosedIcon);  
        } else {
            passwordField.setEchoChar('\u2022');  //hide the password
            iconLabel.setIcon(eyeOpenIcon); 
        }
    }
    
    private void handleSubmit(){
        String email = emailField.getText();
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String passwordString = new String(password);
        char[] confirmPassword = confirmPswField.getPassword();
        String confirmPasswordString = new String(confirmPassword);
        String phone = phoneField.getText();
        String address = addressArea.getText();

        boolean emailMatch = emailPattern.matcher(email).matches();
        boolean usernameMatch = usernamePattern.matcher(username).matches();
        boolean passwordMatch = passwordPattern.matcher(passwordString).matches();
        boolean phoneMatch = phonePattern.matcher(phone).matches();
        
        if (email.isEmpty() || username.isEmpty() || passwordString.isEmpty() || confirmPasswordString.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all the details", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!passwordString.equals(confirmPasswordString)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!emailMatch) {
            JOptionPane.showMessageDialog(this, "Email is invalid.", "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else if (!usernameMatch) {
            JOptionPane.showMessageDialog(this, "Username must be between 6 and 14 characters long and include at least one uppercase letter, one lowercase letter, and one digit.", 
                    "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else if (!passwordMatch) {
            JOptionPane.showMessageDialog(this, "Password must be within 8 characters and include uppercase, lowercase, and a number.", "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else if (!phoneMatch) {
            JOptionPane.showMessageDialog(this, "Phone number is in invalid format. Hint: 0123456789", "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else {
            // Store user data using UserManager
            boolean registrationSuccess = UserManager.registerUser(email, username, passwordString, phone, address);
            if(registrationSuccess){
                JOptionPane.showMessageDialog(this, "Your account has been registered successfully!", "Register Status", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                // Open LoginFrame
                SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            }else{
                JOptionPane.showMessageDialog(this, "Username is already taken. Please choose a different username.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void handleReset(){
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPswField.setText("");
        phoneField.setText("");
        addressArea.setText("");
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){
            handleSubmit();
        } else if(e.getSource() == resetButton){
            handleReset();
        }
    }
}
