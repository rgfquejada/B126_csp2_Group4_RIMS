
package com.group4.rims.model;

public class Ingredient {
    
    private int ingredientId;
    private String ingredientName;
    private int stockQuantity;
    private String unit;
    private int reorderLevel;
    private int supplierId;
    
    public Ingredient (){
        
    }
    public Ingredient(int ingredientId, String ingredientName,int stockQuantity, 
                        String unit, int reorderLevel, int supplierId){
    
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
        this.reorderLevel = reorderLevel;
        this.supplierId = supplierId;
    }
    public int getIngredientId(){
        return ingredientId;
    }
    public void setIngredientId(int ingredientId){
        this.ingredientId = ingredientId;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public int getReorderLevel(){
        return reorderLevel;
    }
    public void setReorderLevel(int reorderLevel){
        this.reorderLevel = reorderLevel;
    }
    public int getSupplierId(){
        return supplierId;
    }
    public void setSupplierId(int supplierId){
        this.supplierId = supplierId;
    }
}
