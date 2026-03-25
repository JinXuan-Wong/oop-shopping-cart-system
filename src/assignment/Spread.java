/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class Spread extends Product implements NutritionalFood {
    private String nutType;
    private int sugarContent;

    public Spread(String name, double price, int index, String nutType, int sugarContent) {
        super("Spread", name, price, index);
        this.nutType = nutType;
        this.sugarContent = sugarContent;
    }

    public String getNutType() {
        return nutType;
    }

    public void setNutType(String nutType) {
        this.nutType = nutType;
    }

    @Override
    public int getCalories() {
        return (int) (250 * DEFAULT_CALORIES_PER_GRAM);
    }

    @Override
    public int getSugarContent() {
        return sugarContent;
    }
    
    public void setSugarContent(int sugarContent) {
        this.sugarContent = sugarContent;
    }

    @Override
    public int getProteinContent() {
        return (int) (250 * DEFAULT_PROTEIN_CONTENT_PER_GRAM);
    }

    @Override
    public String toString() {
        return super.toString() 
                + "\n Nut Type: " + nutType 
                + "\n Calories: " + this.getCalories() + "kcal"
                + "\n Protein: " + this.getProteinContent() + "g"
                + "\n Sugar: " + sugarContent + "g";
    }
}