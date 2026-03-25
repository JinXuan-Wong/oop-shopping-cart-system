/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class ResetPswGUI extends JFrame implements ActionListener{
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel pswLabel;
    private JLabel confirmpswLabel;
    private JTextField usernameField;
    private JPasswordField confirmPswField;
    private JPasswordField pswField;
    private JButton submitBtn;
    private JButton resetBtn;
    private JButton backBtn;
    public ImageIcon cartIcon = new ImageIcon("cartIcon.png");
    private JLabel pswVisibilityIcon;
    private JLabel CpswVisibilityIcon;
    private ImageIcon eyeOpenIcon;
    private ImageIcon eyeClosedIcon;
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{1,8}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    
    public ResetPswGUI() {
        initComponents();
        layoutComponents();
        addComponentsToFrame();
    }
    
    private void initComponents() {
        titleLabel = new JLabel("RESET PASSWORD");
        usernameLabel = new JLabel("USERNAME             : ");
        pswLabel = new JLabel("NEW PASSWORD       :");
        confirmpswLabel = new JLabel("CONFIRM PASSWORD  :");

        usernameField = new JTextField();
        pswField = new JPasswordField();
        confirmPswField = new JPasswordField();

        submitBtn = new JButton("SUBMIT");
        resetBtn = new JButton("RESET");
        backBtn = new BackButton(this);

        submitBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        
        usernameField.setToolTipText("Enter a username between 6 and 14 characters long. Include uppercase, lowercase, a digit, and a special character.");
        pswField.setToolTipText("Enter a password within 8 characters long. Include uppercase, lowercase, and a digit.");
        confirmPswField.setToolTipText("Enter the same password that you have typed before");
        
        eyeOpenIcon = new ImageIcon("eyeIcon.png");  
        eyeClosedIcon = new ImageIcon("eyeSlashIcon.png"); 
        
        pswVisibilityIcon = new JLabel(eyeClosedIcon);
        pswVisibilityIcon.setBounds(610, 400, 30, 30);  // Adjust position as needed
        pswVisibilityIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pswVisibilityIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                togglePasswordVisibility(pswField, pswVisibilityIcon);
            }
    });
        CpswVisibilityIcon = new JLabel(eyeClosedIcon);
        CpswVisibilityIcon.setBounds(610, 550, 30, 30);  // Adjust position as needed
        CpswVisibilityIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CpswVisibilityIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                togglePasswordVisibility(confirmPswField, CpswVisibilityIcon);
            }
    });
    }
    
    private void setupLabel(JLabel label, int yPosition) {
        label.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label.setBounds(180, yPosition, 400, 30);
    }

    private void setupTextField(JTextComponent textField, int yPosition) {
        textField.setPreferredSize(new Dimension(10, 20));
        textField.setBounds(400, yPosition, 200, 30);
        textField.setFont(new Font("MV Boli", Font.PLAIN, 15));
    }

    private void setupButton(JButton button, int xPosition, int yPosition, int width, int height) {
        button.setBounds(xPosition, yPosition, width, height);
        button.setFont(new Font("MV Boli", Font.PLAIN, 15));
        button.setForeground(Color.white);
        button.setBackground(new Color(138, 138, 138));
    }

    private void layoutComponents() {
        titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 28));
        titleLabel.setBounds(270, 100, 600, 70);

        setupLabel(usernameLabel, 250);
        setupLabel(pswLabel, 400);
        setupLabel(confirmpswLabel, 550);

        setupTextField(usernameField, 250);
        setupTextField(pswField, 400);
        setupTextField(confirmPswField, 550);

        setupButton(submitBtn, 200, 650, 100, 30);
        setupButton(resetBtn, 500, 650, 100, 30);
    }
    
    private void addComponentsToFrame() {
        this.setResizable(false);
        this.setSize(850, 750);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Online Shopping Cart");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(cartIcon.getImage());
        this.getContentPane().setBackground(new Color(233, 230, 221));

        this.add(titleLabel);
        this.add(usernameLabel);
        this.add(pswLabel);
        this.add(confirmpswLabel);
        this.add(usernameField);
        this.add(pswField);
        this.add(confirmPswField);
        this.add(submitBtn);
        this.add(resetBtn);
        this.add(backBtn);
        this.add(pswVisibilityIcon);
        this.add(CpswVisibilityIcon);
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
    
    private void changePassword(){
        String username = usernameField.getText();
        char[] password = pswField.getPassword();
        String passwordString = new String(password); // Convert char[] to String
        char[] cpassword = confirmPswField.getPassword();
        String cpasswordString = new String(cpassword);
        
        boolean passwordMatch = passwordPattern.matcher(passwordString).matches();

        if (username.isEmpty() || passwordString.isEmpty() || cpasswordString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all the details", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!passwordString.equals(cpasswordString)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else if (!passwordMatch) {
            JOptionPane.showMessageDialog(this, "Password must be within 8 characters and include uppercase, lowercase, and a number", "Validation Status", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean success = UserManager.updatePassword(username, passwordString);
            if(success){
                JOptionPane.showMessageDialog(this, "Your password has been reset successfully!", "Reset Password Status", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                // Open LoginFrame
                SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            } else {
                JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void handleReset(){
        usernameField.setText("");
        pswField.setText("");
        confirmPswField.setText("");
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            changePassword();
        } else if (e.getSource() == resetBtn) {
            handleReset();
        }
    }
}
