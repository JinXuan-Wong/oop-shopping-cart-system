/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class Cereal extends Product implements NutritionalFood{
    private String flavorType;
    private boolean isWholeGrain;

    public Cereal(String name, double price, int index, String flavorType, boolean isWholeGrain){
        super("Cereal", name, price, index);
        this.flavorType = flavorType;
        this.isWholeGrain = isWholeGrain;
    }

    public String getFlavorType(){
        return flavorType;
    }
    
    public void setFlavorType(String flavorType){
        this.flavorType = flavorType;
    }

    public boolean isWholeGrain() {
        return isWholeGrain;
    }

    public void setIsWholeGrain(boolean isWholeGrain) {
        this.isWholeGrain = isWholeGrain;
    }
    

    @Override
    public int getCalories() {
        return (int) (DEFAULT_SERVING_SIZE * DEFAULT_CALORIES_PER_GRAM);
    }

    @Override
    public int getSugarContent() {
        return (int) (DEFAULT_SERVING_SIZE * DEFAULT_SUGAR_CONTENT_PER_GRAM); 
    }

    @Override
    public int getProteinContent() {
        return (int) (DEFAULT_SERVING_SIZE * DEFAULT_PROTEIN_CONTENT_PER_GRAM);
    }

    @Override
    public String toString() {
        return super.toString() 
                + "\n Flavor: " + flavorType 
                + "\n Whole Grain: " + (isWholeGrain ? "Yes" : "No")
                + "\n Calories: " + this.getCalories() + "kcal"
                + "\n Protein: " + this.getProteinContent() + "g"
                + "\n Sugar: " + this.getSugarContent() + "g";
    }
}
