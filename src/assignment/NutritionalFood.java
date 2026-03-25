/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public interface NutritionalFood {
    int DEFAULT_SERVING_SIZE = 100;
    double DEFAULT_CALORIES_PER_GRAM = 4.0;
    double DEFAULT_SUGAR_CONTENT_PER_GRAM = 0.5;
    double DEFAULT_PROTEIN_CONTENT_PER_GRAM = 0.2;
    
    public abstract int getCalories();
    public abstract int getSugarContent();
    public abstract int getProteinContent();
}
