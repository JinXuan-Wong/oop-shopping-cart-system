/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class Shake extends Product implements NutritionalFood {
    private int calories;
    private int servingSize;

    public Shake(String name, double price, int index, int calories, int servingSize) {
        super("Shake", name, price, index);
        this.calories = calories;
        this.servingSize = servingSize;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    @Override
    public int getSugarContent() {
        return (int) (servingSize * DEFAULT_SUGAR_CONTENT_PER_GRAM);
    }

    @Override
    public int getProteinContent() {
        return (int) (servingSize * DEFAULT_PROTEIN_CONTENT_PER_GRAM);
    }

    @Override
    public String toString() {
        return super.toString() 
                + "\n Serving Size: " + servingSize + "ml"
                + "\n Calories: " + calories + "kcal"
                + "\n Protein: " + this.getProteinContent() + "g"
                + "\n Sugar: " + this.getSugarContent() + "g";
    }
}


