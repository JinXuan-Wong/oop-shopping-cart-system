/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class ShoppingCart {
    private static final int MAX_ITEMS = 14;
    //get array of all products sold
    private ProductInventory inventory = new ProductInventory();
    private Product[] products = inventory.getProducts(); 
    private CartItem[] items;
    
    public ShoppingCart(){
        //create and initialize all the products sold into cart array with 
        //initial value of 0
        items = new CartItem[MAX_ITEMS];  
        for(int i = 0; i < MAX_ITEMS; i++){
            items[i] = new CartItem(products[i], 0); 
        }
    }
    
    public CartItem[] getItems(){
        return items;
    }
    
    public void addProduct(Product productSelected){
        for(CartItem item : items){ //use foreach loop to find the corresponding item
            if(item.getProduct().getName().equals(productSelected.getName())){
                if(item.getQuantity() < 50){
                    item.increaseQuantity();
                } 
            }
        }
    }
    
    public void removeProduct(Product productSelected){
        for(CartItem item : items){ 
            if(item.getProduct().getName().equals(productSelected.getName())){
                item.decreaseQuantity();
            }
        }
    }
    
    public void deleteProduct(Product productSelected){
        for(CartItem item : items){ 
            if(item.getProduct().getName().equals(productSelected.getName())){
                item.setQuantity(0);
            }
        }
    }
}
