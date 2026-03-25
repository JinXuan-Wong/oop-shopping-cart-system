/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class ProductInventory {
    private Product[] products;
    
    public ProductInventory(){
        initializeProducts();
    }
    
    private void initializeProducts(){
        products = new Product[]{
            new Cereal("Vanilla Cereal", 18.0, 0, "Vanilla", true),
            new Cereal("Chocolate Cereal", 18.0, 1, "Chocolate", false),
            new Cereal("Mix Bundle Cereal", 35.0, 2, "Vanilla and Chocolate", true),
            
            new Bar("Almond Butter Bar", 7.0, 3, 10, true),
            new Bar("Choc Chip Bar", 7.0, 4, 12, true),
            new Bar("Choc Mint Bar", 7.0, 5, 11, false),
            new Bar("Dark Choc Bar", 7.0, 6, 9, true),
            new Bar("Variety Bars (4)", 25.0, 7, 10, true),
            
            new Spread("Almond Spread", 18.0, 8, "Almond", 5),
            new Spread("PB Spread", 18.0, 9, "Peanut", 6),
            new Spread("Dark Choc Spread", 18.0, 10, "Cocoa, Hazelnut", 4),
            new Spread("Set Spread", 50.0, 11, "Almond, Peanut, Hazelnut, Cocoa", 10),
            
            new Shake("Coffee Shake", 13.0, 12, 120, 250),
            new Shake("Chocolate Shake", 13.0, 13, 130, 250)
        };
    }
    
    public Product[] getProducts(){
        return products;
    }
}
