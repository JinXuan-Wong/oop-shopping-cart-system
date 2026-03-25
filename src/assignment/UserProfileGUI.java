package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.text.JTextComponent;

public class UserProfileGUI extends JFrame implements ActionListener {
    private User user;
    private JLabel titleLabel, emailLabel, usernameLabel, passwordLabel, phoneNumberLabel, addressLabel, profilePicLabel;
    private JTextField emailField, usernameField, phoneNumberField;
    private JTextArea addressField;
    private JPasswordField passwordField;
    private JButton updateButton, showDetailsButton, homeButton, logoutButton, clearFormButton, uploadPicButton;
    public ImageIcon cartIcon = new ImageIcon("cartIcon.png");
    private String profilePicPath;
    
    private static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern usernamePattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,14}$");
    private static final Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{1,8}$");
    private static final Pattern phonePattern = Pattern.compile("^01\\d{8,9}$"); // Example for a 10-digit phone number

    public UserProfileGUI(User user) {
        this.user = user;
        initComponents();
        layoutComponents();
        addComponentsToFrame();
        loadProfilePicture();
    }

    private void initComponents() {
        titleLabel = new JLabel("USER ACCOUNT");
        profilePicLabel = new JLabel();
        emailLabel = new JLabel("Email:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        phoneNumberLabel = new JLabel("Phone Number:");
        addressLabel = new JLabel("Address:");
        
        emailField = new JTextField(user.getEmail());
        usernameField = new JTextField(user.getUsername());
        passwordField = new JPasswordField(user.getPassword());
        phoneNumberField = new JTextField(user.getPhoneNumber());
        addressField = new JTextArea(user.getAddress());
        
        updateButton = new JButton("Update Profile");
        showDetailsButton = new JButton("Show Details");
        homeButton = new JButton("Home");
        logoutButton = new JButton("Logout");
        clearFormButton = new JButton("Clear Form");
        uploadPicButton = new JButton("Upload Picture");

        updateButton.addActionListener(this);
        showDetailsButton.addActionListener(this);
        homeButton.addActionListener(this);
        logoutButton.addActionListener(this);
        clearFormButton.addActionListener(this);
        uploadPicButton.addActionListener(this);

        emailField.setToolTipText("Enter your email address (e.g., example@domain.com)");
        usernameField.setToolTipText("Enter a username between 6 and 14 characters long. Include uppercase, lowercase, and a digit.");
        passwordField.setToolTipText("Enter a password within 8 characters long. Include uppercase, lowercase, and a digit.");
        phoneNumberField.setToolTipText("Enter your phone number as 10 digits (e.g., 0123456789).");
        addressField.setToolTipText("Enter your full address including street, city, and zip code.");
        uploadPicButton.setToolTipText("Click to upload a profile picture.");

        // Set default profile picture
        setDefaultProfilePic();
    }
    
     private void setDefaultProfilePic() {
        ImageIcon defaultProfilePic = new ImageIcon(new ImageIcon("profilepic.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        profilePicLabel.setIcon(defaultProfilePic);
        profilePicLabel.setBounds(360, 67, 150, 150);
    }

    private void layoutComponents() {
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
        titleLabel.setBounds(270, 30, 400, 30);
        
        setupLabel(emailLabel, 250);
        setupLabel(usernameLabel, 300);
        setupLabel(passwordLabel, 350);
        setupLabel(phoneNumberLabel, 400);
        setupLabel(addressLabel, 450);

        setupTextField(emailField, 250);
        setupTextField(usernameField, 300);
        setupTextField(passwordField, 350);
        setupTextField(phoneNumberField, 400);
        setupTextArea(addressField, 450, 300, 100); // Adjusted width

        setupButton(uploadPicButton,330, 200, 180, 30 );
        setupButton(updateButton, 80, 590, 180, 40);
        setupButton(showDetailsButton, 300, 590, 180, 40);
        setupButton(homeButton, 520, 590, 180, 40);
        setupButton(clearFormButton, 170, 660, 180, 40);
        setupButton(logoutButton, 400, 660, 180, 40);   
    }

    private void setupLabel(JLabel label, int yPosition) {
        label.setFont(new Font("MV Boli", Font.PLAIN, 18));
        label.setBounds(100, yPosition, 150, 30);
    }

    private void setupTextField(JTextComponent textField, int yPosition) {
        textField.setBounds(300, yPosition, 300, 30);
        textField.setFont(new Font("MV Boli", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private void setupTextArea(JTextArea textArea, int yPosition, int width, int height) {
        textArea.setBounds(300, yPosition, width, height);
        textArea.setFont(new Font("MV Boli", Font.PLAIN, 15));
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    private void setupButton(JButton button, int xPosition, int yPosition, int width, int height) {
        button.setBounds(xPosition, yPosition, width, height);
        button.setFont(new Font("MV Boli", Font.PLAIN, 18));
        button.setForeground(Color.white);
        button.setBackground(new Color(100, 100, 100));
        button.setFocusPainted(false);
    }

    private void addComponentsToFrame() {
        this.setResizable(false);
        this.setSize(850, 750);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Online Shopping Cart");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(cartIcon.getImage());
        this.getContentPane().setBackground(new Color(245, 245, 245));

        this.add(titleLabel);
        this.add(profilePicLabel);
        this.add(emailLabel);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(phoneNumberLabel);
        this.add(addressLabel);
        this.add(emailField);
        this.add(usernameField);
        this.add(passwordField);
        this.add(phoneNumberField);
        this.add(addressField);
        this.add(uploadPicButton);
        this.add(updateButton);
        this.add(showDetailsButton);
        this.add(homeButton);
        this.add(logoutButton);
        this.add(clearFormButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateUserProfile();
        } else if (e.getSource() == showDetailsButton) {
            showUserDetails();
        } else if (e.getSource() == homeButton) {
            this.dispose();
            SwingUtilities.invokeLater(() -> new MyFrame(user).setVisible(true));
        } else if (e.getSource() == logoutButton) {
            UserManager.logoutUser();
            this.dispose();
            JOptionPane.showMessageDialog(this, "Logged out successfully!", "Logout", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true)); // Assuming you have a LoginFrame class
        } else if (e.getSource() == clearFormButton) {
            clearForm();
        } else if (e.getSource() == uploadPicButton) {
            uploadProfilePicture();
        }
    }

     private void updateUserProfile() {
        String email = emailField.getText();
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String passwordString = new String(password).trim();
        String phone = phoneNumberField.getText();
        String address = addressField.getText();
        
        boolean emailMatch = emailPattern.matcher(email).matches();
        boolean usernameMatch = usernamePattern.matcher(username).matches();
        boolean passwordMatch = passwordPattern.matcher(passwordString).matches();
        boolean phoneMatch = phonePattern.matcher(phone).matches();
      
        if (email.isEmpty() || username.isEmpty() || passwordString.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the details", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!emailMatch) {
            JOptionPane.showMessageDialog(this, "Email is invalid.", "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else if (!usernameMatch) {
            JOptionPane.showMessageDialog(this, "Username must be between 6 and 14 characters long and include at least one uppercase letter, one lowercase letter, and one digit.", 
                    "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else if (!passwordMatch) {
            JOptionPane.showMessageDialog(this, "Password must within 8 characters and include uppercase, lowercase and number", "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else if (!phoneMatch) {
            JOptionPane.showMessageDialog(this, "Phone number is invalid. It should start with '01' and be 10 or 11 digits long.", "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else {
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(passwordString);
            user.setPhoneNumber(phone);
            user.setAddress(address);

            JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void showUserDetails() {
        String userDetails = "Email: " + user.getEmail() +
                "\nUsername: " + user.getUsername() +
                "\nPassword: " + user.getPassword().replaceAll(" ", "*") +
                "\nPhone Number: " + user.getPhoneNumber() +
                "\nAddress: " + user.getAddress();
            
        JOptionPane.showMessageDialog(this, userDetails, "User Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        phoneNumberField.setText("");
        addressField.setText("");        
    }

   private void uploadProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            profilePicPath = selectedFile.getAbsolutePath();

            // Store the path in the user object
            user.setProfilePicPath(profilePicPath);

            ImageIcon profilePic = new ImageIcon(new ImageIcon(profilePicPath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            profilePicLabel.setIcon(profilePic);
            profilePicLabel.setBounds(360, 67, 150, 150); // Adjust the position and size as needed
        }
    }
   
   private void loadProfilePicture() {
        profilePicPath = user.getProfilePicPath(); // Load the saved path from the user object

        if (profilePicPath != null) { // Check if a picture was uploaded
            ImageIcon profilePic = new ImageIcon(new ImageIcon(profilePicPath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            profilePicLabel.setIcon(profilePic);
            profilePicLabel.setBounds(360, 67, 150, 150); // Adjust the position and size as needed
        }
    }

}
