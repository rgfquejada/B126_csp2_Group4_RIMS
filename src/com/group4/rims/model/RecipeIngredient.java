
package com.group4.rims.model;

public class RecipeIngredient {
    
    private int recipeId;
    private int menuId;
    private int ingredientId;
    private double quantityNeeded;

    public RecipeIngredient() {
    }

    public RecipeIngredient(int recipeId, int menuId, int ingredientId, 
                            double quantityNeeded) {
        
        this.recipeId = recipeId;
        this.menuId = menuId;
        this.ingredientId = ingredientId;
        this.quantityNeeded = quantityNeeded;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getQuantityNeeded() {
        return quantityNeeded;
    }

    public void setQuantityNeeded(double quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }
    
}
