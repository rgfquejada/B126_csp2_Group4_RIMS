package com.group4.rims.service;

import com.group4.rims.model.RecipeIngredient;
import com.group4.rims.repository.RecipeIngredientRepo;
import java.util.List;

public class RecipeIngredientService {

    private final RecipeIngredientRepo recipeIngredientRepo;

    public RecipeIngredientService(RecipeIngredientRepo recipeIngredientRepo) {
        this.recipeIngredientRepo = recipeIngredientRepo;
    }

    public RecipeIngredient findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid recipe ID.");
        }

        return recipeIngredientRepo.findById(id);
    }

    public List<RecipeIngredient> findAll() {
        return recipeIngredientRepo.findAll();
    }

    public void save(RecipeIngredient recipeIngredient) {

        if (recipeIngredient == null) {
            throw new IllegalArgumentException("Recipe ingredient cannot be null.");
        }

        if (recipeIngredient.getMenuId() <= 0) {
            throw new IllegalArgumentException("Invalid menu ID.");
        }

        if (recipeIngredient.getIngredientId() <= 0) {
            throw new IllegalArgumentException("Invalid ingredient ID.");
        }

        if (recipeIngredient.getQuantityNeeded() <= 0) {
            throw new IllegalArgumentException("Quantity needed must be greater than zero.");
        }

        recipeIngredientRepo.save(recipeIngredient);
    }

    public void update(RecipeIngredient recipeIngredient) {

        if (recipeIngredient == null) {
            throw new IllegalArgumentException("Recipe ingredient cannot be null.");
        }

        if (recipeIngredient.getRecipeId() <= 0) {
            throw new IllegalArgumentException("Invalid recipe ID.");
        }

        if (recipeIngredient.getMenuId() <= 0) {
            throw new IllegalArgumentException("Invalid menu ID.");
        }

        if (recipeIngredient.getIngredientId() <= 0) {
            throw new IllegalArgumentException("Invalid ingredient ID.");
        }

        if (recipeIngredient.getQuantityNeeded() <= 0) {
            throw new IllegalArgumentException("Quantity needed must be greater than zero.");
        }

        recipeIngredientRepo.update(recipeIngredient);
    }

    public void delete(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid recipe ID.");
        }

        recipeIngredientRepo.delete(id);
    }
}