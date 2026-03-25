/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class Bar extends Product implements NutritionalFood {
    private int proteinContent;
    private boolean isGlutenFree;

    public Bar(String name, double price, int index, int proteinContent, boolean isGlutenFree) {
        super("Protein Bar", name, price, index);
        this.proteinContent = proteinContent;
        this.isGlutenFree = isGlutenFree;
    }

    @Override
    public int getProteinContent() {
        return proteinContent;
    }

    public void setProteinContent(int proteinContent) {
        this.proteinContent = proteinContent;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }
    
    public void setGlutenFree(boolean isGlutenFree){
        this.isGlutenFree = isGlutenFree;
    }

    @Override
    public int getCalories() {
        return (int) (30 * DEFAULT_CALORIES_PER_GRAM);
    }

    @Override
    public int getSugarContent() {
        return (int) (30 * DEFAULT_SUGAR_CONTENT_PER_GRAM);
    }

    @Override
    public String toString() {
        return super.toString() 
                + "\n Gluten-Free: " + (isGlutenFree ? "Yes" : "No")
                + "\n Calories: " + this.getCalories() + "kcal"
                + "\n Protein: " + proteinContent + "g"
                + "\n Sugar: " + this.getSugarContent() + "g";
    } 
}

