/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public abstract class Product {
    private static int nextIndex = 1;
    private String category;
    private String name;
    private double price;
    private int index; 
    
    public Product(String category, String name, double price, int index){
        this.category = category;
        this.name = name;
        this.price = price;
        this.index = index;
        nextIndex++;
    }
    
    public Product(String name, double price) {
        this("Other", name, price, nextIndex); //If not given index, automatically assign
    }
    
    public Product(){
        this("", "", 0, nextIndex);
    }
    
    public String getCategory(){
        return category;
    }
    
    public void setCategory(String category){
        this.category = category;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setPrice(double price){
        try {
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
            this.price = price;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public int getIndex(){ 
        return index;
    }
    
    @Override
    public String toString(){
        return " Name: " + name 
                + "\n Price: RM " + String.format("%.2f", price);
    }
}
