
package com.group4.rims.controller;

import com.group4.rims.service.IngredientService;
import com.group4.rims.model.Ingredient;
import java.util.List;

public class IngredientController {
    
    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    public void addIngredient(Ingredient ingredient) {
        try {
            if (ingredient == null) {
                System.out.println("Error: Ingredient details cannot be empty.");
                return;
            }

            ingredientService.save(ingredient);
            System.out.println("Success: Ingredient '" + ingredient.getIngredientName() + "' successfully registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public List<Ingredient> getAllIngredients() {
        return ingredientService.findAll();
    }

 
    public Ingredient getIngredientById(int id) {
        try {
            Ingredient item = ingredientService.findById(id);
            if (item == null) {
                System.out.println("Warning: No ingredient found with tracking ID: " + id);
            }
            return item;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void updateIngredient(Ingredient ingredient) {
        try {
            if (ingredient == null) {
                System.out.println("Error: Update target cannot be empty.");
                return;
            }
 
            if (ingredientService.findById(ingredient.getIngredientId()) == null) {
                System.out.println("Error: Cannot update. Target ingredient does not exist.");
                return;
            }

            ingredientService.update(ingredient);
            System.out.println("Success: Ingredient metadata modified successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public void deleteIngredient(int id) {
        try {

            if (ingredientService.findById(id) == null) {
                System.out.println("Error: Deletion failed. ID match not found.");
                return;
            }
            ingredientService.delete(id);
            System.out.println("Success: Target item permanently deleted from records.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
