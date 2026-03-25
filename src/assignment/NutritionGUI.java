/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import javax.swing.*;
import java.awt.*;

public class NutritionGUI extends JFrame{
    public JScrollPane nutritionScrollPane;
    private JPanel nutritionPanel;
    private JLabel nutritionLabel;
    private JLabel nutritionTitle;
    
    public NutritionGUI(String infoString){
        ImageIcon nutriIcon = new ImageIcon("nutritionIcon.png");
        
        nutritionPanel = new JPanel();
        nutritionPanel.setLayout(new BorderLayout());
        
        nutritionTitle = new JLabel("Nutritional Facts", JLabel.CENTER);
        nutritionTitle.setVerticalAlignment(JLabel.TOP);
        nutritionTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
        
        nutritionLabel = new JLabel();
        nutritionLabel.setVerticalAlignment(JLabel.TOP); // Align text to the top
        nutritionLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
        nutritionLabel.setText(formatInfoString(infoString)); // Display nutritional info
        
        nutritionPanel.add(nutritionTitle,BorderLayout.NORTH);
        nutritionPanel.add(nutritionLabel,BorderLayout.CENTER);
        
        nutritionScrollPane = new JScrollPane(nutritionPanel);
        nutritionScrollPane.setBounds(10, 10, 650, 550); // Adjust size as needed
        
        // Disable horizontal scrolling
        nutritionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Set vertical scroll speed
        JScrollBar verticalScrollBar = nutritionScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20); // Adjust value for vertical scroll speed
        verticalScrollBar.setBlockIncrement(60); // Adjust value for vertical block scroll speed
    
        this.setResizable(false); //prevent frame from being resize
        this.setSize(700, 650);
        this.setLayout(null);
        this.setTitle("Nutritional Facts");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        this.setIconImage(nutriIcon.getImage());
        this.getContentPane().setBackground(new Color(233,230,221)); //change color of background
        this.add(nutritionScrollPane);
    }
    
    public void showGUI() {
        this.setVisible(true); // Display the GUI
    }
    
    private String formatInfoString(String infoString) {
        return "<html>" + infoString.replace("\n", "<br>") + "</html>";
    }
}
