
package com.group4.rims.view;

import com.group4.rims.controller.RecipeIngredientController;
import com.group4.rims.model.RecipeIngredient;
import java.util.List;
import java.util.Scanner;

public class RecipeIngredientView {

    private final RecipeIngredientController recipeController;
    private final Scanner scanner;

    public RecipeIngredientView(RecipeIngredientController recipeController, Scanner scanner) {
        this.recipeController = recipeController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- RECIPE COMPONENT MAPPING ---");
            System.out.println("1. View All Recipe Allocations");
            System.out.println("2. Find Recipe Matrix by ID");
            System.out.println("3. Link Ingredient to Menu Item Formula");
            System.out.println("4. Modify Recipe Allocation Metadata");
            System.out.println("5. Sever Recipe Ingredient Link");
            System.out.println("6. Back to Main Dashboard");
            System.out.print("Select action: ");

            int action = readIntegerInput();
            switch (action) {
                case 1:
                    handleViewAll();
                    break;
                case 2:
                    handleFindById();
                    break;
                case 3:
                    handleAdd();
                    break;
                case 4:
                    handleUpdate();
                    break;
                case 5:
                    handleDelete();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid option choice. Select values 1-6.");
                    break;
            }
        }
    }

    private void handleViewAll() {
        List<RecipeIngredient> list = recipeController.getAllRecipeIngredients();
        System.out.println("\n==========================================================================");
        System.out.printf("%-10s | %-15s | %-15s | %-15s\n", "RECIPE ID", "MENU ITEM ID", "INGREDIENT ID", "QTY NEEDED");
        System.out.println("==========================================================================");

        if (list.isEmpty()) {
            System.out.println("                     No composite formula configurations set.              ");
        } else {
            for (RecipeIngredient r : list) {
                System.out.printf("%-10d | %-15d | %-15d | %-15.2f\n",
                        r.getRecipeId(), r.getMenuId(), r.getIngredientId(), r.getQuantityNeeded());
            }
        }
        System.out.println("==========================================================================");
    }

    private void handleFindById() {
        System.out.print("\nEnter target Recipe Tracking ID to search: ");
        int id = readIntegerInput();
        RecipeIngredient r = recipeController.getRecipeIngredientById(id);

        if (r != null) {
            System.out.println("\n--- RECIPE SPECIFICATION FOUND ---");
            System.out.println("Recipe Mapping Link ID: " + r.getRecipeId());
            System.out.println("Associated Menu ID   : " + r.getMenuId());
            System.out.println("Target Ingredient ID : " + r.getIngredientId());
            System.out.println("Volume Mass Metric   : " + r.getQuantityNeeded());
        }
    }

    private void handleAdd() {
        System.out.println("\n--- ATTACH REQUISITE QUANTITIES FOR DISH COMPOSITION ---");
        System.out.print("Target Menu Profile ID: ");
        int menuId = readIntegerInput();
        System.out.print("Source Storage Ingredient ID: ");
        int ingId = readIntegerInput();
        System.out.print("Required Yield Serving Measure: ");
        double qty = readDoubleInput();

        RecipeIngredient ri = new RecipeIngredient(0, menuId, ingId, qty);
        recipeController.addRecipeIngredient(ri);
    }

    private void handleUpdate() {
        System.out.print("\nEnter target Recipe tracking Link ID to modify: ");
        int id = readIntegerInput();
        RecipeIngredient existing = recipeController.getRecipeIngredientById(id);
        if (existing == null) {
            return;
        }

        System.out.println("Press [Enter] with blank responses to pass over value adjustments.");
        System.out.print("New Associated Menu ID [" + existing.getMenuId() + "]: ");
        String mStr = scanner.nextLine().trim();
        int menuId = mStr.isEmpty() ? existing.getMenuId() : Integer.parseInt(mStr);

        System.out.print("New Target Ingredient ID [" + existing.getIngredientId() + "]: ");
        String iStr = scanner.nextLine().trim();
        int ingId = iStr.isEmpty() ? existing.getIngredientId() : Integer.parseInt(iStr);

        System.out.print("New Serving Yield Constraint [" + existing.getQuantityNeeded() + "]: ");
        String qStr = scanner.nextLine().trim();
        double qty = qStr.isEmpty() ? existing.getQuantityNeeded() : Double.parseDouble(qStr);

        RecipeIngredient updated = new RecipeIngredient(id, menuId, ingId, qty);
        recipeController.updateRecipeIngredient(updated);
    }

    private void handleDelete() {
        System.out.print("\nEnter target Recipe link ID for tracking deletion: ");
        int id = readIntegerInput();
        recipeController.deleteRecipeIngredient(id);
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Format error context. Enter valid whole identification options: ");
            }
        }
    }

    private double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Numerical error context. Enter clean floating point configurations: ");
            }
        }
    }
}
