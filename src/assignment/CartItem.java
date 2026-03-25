/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

public class CartItem {
    private Product product;
    private int quantity;
    private boolean isChecked; //Cart item checked to proceed paymenr
    
    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.isChecked = true; //by default will tick(checked) items in cart
    }
    
    public CartItem(){
        this.product = null;
        this.quantity = 0;
        this.isChecked = true;
    }
    
    public Product getProduct(){
        return product;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void increaseQuantity(){
        this.quantity++;
    }
    
    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
    
    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }
    
    public boolean getChecked(){
        return isChecked;
    }
    
    public void setChecked(boolean checked){
        this.isChecked = checked;
    }
    
    @Override
    public String toString() {
        return "Product: " + product.getName() 
               + "\nQuantity: " + quantity 
               + "\nChecked: " + (isChecked ? "Yes" : "No") 
               + "\nTotal Price: RM " + String.format("%.2f", getTotalPrice());
    }
}
