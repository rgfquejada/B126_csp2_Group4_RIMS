package com.group4.rims.service;

import com.group4.rims.model.Ingredient;
import com.group4.rims.repository.IngredientRepo;
import java.util.List;

public class IngredientService {

    private final IngredientRepo ingredientRepo;

    public IngredientService(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    public Ingredient findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ingredient ID.");
        }

        return ingredientRepo.findById(id);
    }

    public List<Ingredient> findAll() {
        return ingredientRepo.findAll();
    }

    public void save(Ingredient ingredient) {

        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null.");
        }

        if (ingredient.getIngredientName() == null || ingredient.getIngredientName().trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name is required.");
        }

        if (ingredient.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }

        if (ingredient.getReorderLevel() < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }

        if (ingredient.getUnit() == null || ingredient.getUnit().trim().isEmpty()) {
            throw new IllegalArgumentException("Unit is required.");
        }

        ingredientRepo.save(ingredient);
    }

    public void update(Ingredient ingredient) {

        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null.");
        }

        if (ingredient.getIngredientId() <= 0) {
            throw new IllegalArgumentException("Invalid ingredient ID.");
        }

        if (ingredient.getIngredientName() == null || ingredient.getIngredientName().trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name is required.");
        }

        if (ingredient.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }

        if (ingredient.getReorderLevel() < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }

        if (ingredient.getUnit() == null || ingredient.getUnit().trim().isEmpty()) {
            throw new IllegalArgumentException("Unit is required.");
        }

        ingredientRepo.update(ingredient);
    }

    public void delete(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ingredient ID.");
        }

        ingredientRepo.delete(id);
    }
}