package assignment;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BackButton extends JButton {
    public BackButton(JFrame currentFrame) {
        super();
        ImageIcon originalIcon = new ImageIcon("back-button.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH); 
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        this.setIcon(scaledIcon);
        this.setToolTipText("Return to Login Page");
        this.setFont(new Font("MV Boli", Font.PLAIN, 15));
        
        this.setFocusPainted(false); 
        this.setBorderPainted(false); 
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        
        this.setBounds(750, 630, 50, 50); 

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand
                setForeground(new Color(200, 200, 200)); // Light gray color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor()); // Reset cursor
                setForeground(Color.WHITE); // Reset color
            }
        });
        
        this.addActionListener((ActionEvent e) -> {
            currentFrame.dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        });
    }
}