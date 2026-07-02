
package com.group4.rims.controller;

import com.group4.rims.service.RecipeIngredientService;
import com.group4.rims.model.RecipeIngredient;
import java.util.List;

public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;

    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }


    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        try {
            if (recipeIngredient == null) {
                System.out.println("Error: Recipe ingredient details cannot be empty.");
                return;
            }
            recipeIngredientService.save(recipeIngredient);
            System.out.println("Success: Ingredient mapping added to recipe record successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public List<RecipeIngredient> getAllRecipeIngredients() {
        return recipeIngredientService.findAll();
    }


    public RecipeIngredient getRecipeIngredientById(int id) {
        try {
            RecipeIngredient recipeItem = recipeIngredientService.findById(id);
            if (recipeItem == null) {
                System.out.println("Warning: No recipe record found with tracking ID: " + id);
            }
            return recipeItem;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

 
    public void updateRecipeIngredient(RecipeIngredient recipeIngredient) {
        try {
            if (recipeIngredient == null) {
                System.out.println("Error: Update target cannot be empty.");
                return;
            }
            if (recipeIngredientService.findById(recipeIngredient.getRecipeId()) == null) {
                System.out.println("Error: Cannot update. Recipe ID does not exist.");
                return;
            }
            recipeIngredientService.update(recipeIngredient);
            System.out.println("Success: Recipe component record updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public void deleteRecipeIngredient(int id) {
        try {
            if (recipeIngredientService.findById(id) == null) {
                System.out.println("Error: Deletion failed. Recipe link matching ID not found.");
                return;
            }
            recipeIngredientService.delete(id);
            System.out.println("Success: Recipe ingredient configuration trace deleted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}