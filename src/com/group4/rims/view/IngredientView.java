
package com.group4.rims.view;

import com.group4.rims.controller.IngredientController;
import com.group4.rims.model.Ingredient;
import java.util.List;
import java.util.Scanner;

public class IngredientView {

    private final IngredientController ingredientController;
    private final Scanner scanner;

    public IngredientView(IngredientController ingredientController, Scanner scanner) {
        this.ingredientController = ingredientController;
        this.scanner = scanner;
    }

 
    public void displayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- INGREDIENTS MANAGEMENT ---");
            System.out.println("1. View All Ingredients");
            System.out.println("2. Find Ingredient by ID");
            System.out.println("3. Add New Ingredient");
            System.out.println("4. Update Existing Ingredient");
            System.out.println("5. Delete An Ingredient");
            System.out.println("6. Back to Main Menu");
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
                    System.out.println("\n[ERROR] Invalid entry. Please choose 1-6.");
                    break;
            }
        }
    }

    private void handleViewAll() {
        List<Ingredient> list = ingredientController.getAllIngredients();
        System.out.println("\n==========================================================================");
        System.out.printf("%-5s | %-20s | %-10s | %-8s | %-12s | %-10s\n",
                "ID", "NAME", "STOCK QTY", "UNIT", "REORDER LVL", "SUPPLIER");
        System.out.println("==========================================================================");

        if (list.isEmpty()) {
            System.out.println("                      No database records found.                         ");
        } else {
            for (Ingredient i : list) {
                System.out.printf("%-5d | %-20s | %-10d | %-8s | %-12d | %-10d\n",
                        i.getIngredientId(), i.getIngredientName(), i.getStockQuantity(),
                        i.getUnit(), i.getReorderLevel(), i.getSupplierId());
            }
        }
        System.out.println("==========================================================================");
    }

    private void handleFindById() {
        System.out.print("\nEnter Ingredient ID to search: ");
        int id = readIntegerInput();
        Ingredient i = ingredientController.getIngredientById(id);

        if (i != null) {
            System.out.println("\n--- RECORD FOUND ---");
            System.out.println("ID: " + i.getIngredientId());
            System.out.println("Name: " + i.getIngredientName());
            System.out.println("Stock Level: " + i.getStockQuantity() + " (" + i.getUnit() + ")");
            System.out.println("Reorder Trigger Point: " + i.getReorderLevel());
            System.out.println("Associated Supplier ID: " + i.getSupplierId());
        }
    }

    private void handleAdd() {
        System.out.println("\n--- ENTER NEW INGREDIENT DETAILS ---");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Initial Stock Quantity: ");
        int stock = readIntegerInput();
        System.out.print("Unit of Measurement (e.g. kg, pcs): ");
        String unit = scanner.nextLine().trim();
        System.out.print("Safety Reorder Level: ");
        int reorder = readIntegerInput();
        System.out.print("Supplier Profile ID: ");
        int supplierId = readIntegerInput();

        Ingredient ing = new Ingredient(0, name, stock, unit, reorder, supplierId);
        ingredientController.addIngredient(ing);
    }

    private void handleUpdate() {
        System.out.print("\nEnter target Ingredient ID to modify: ");
        int id = readIntegerInput();
        Ingredient existing = ingredientController.getIngredientById(id);
        if (existing == null) {
            return;
        }

        System.out.println("Press [Enter] to keep existing value: '" + existing.getIngredientName() + "'");
        System.out.print("New Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = existing.getIngredientName();
        }

        System.out.print("New Stock level [" + existing.getStockQuantity() + "]: ");
        String stockStr = scanner.nextLine().trim();
        int stock = stockStr.isEmpty() ? existing.getStockQuantity() : Integer.parseInt(stockStr);

        System.out.print("New Measurement Unit [" + existing.getUnit() + "]: ");
        String unit = scanner.nextLine().trim();
        if (unit.isEmpty()) {
            unit = existing.getUnit();
        }

        System.out.print("New Safety Reorder Point [" + existing.getReorderLevel() + "]: ");
        String reorderStr = scanner.nextLine().trim();
        int reorder = reorderStr.isEmpty() ? existing.getReorderLevel() : Integer.parseInt(reorderStr);

        System.out.print("New Supplier ID Assignment [" + existing.getSupplierId() + "]: ");
        String suppStr = scanner.nextLine().trim();
        int supplierId = suppStr.isEmpty() ? existing.getSupplierId() : Integer.parseInt(suppStr);

        Ingredient updated = new Ingredient(id, name, stock, unit, reorder, supplierId);
        ingredientController.updateIngredient(updated);
    }

    private void handleDelete() {
        System.out.print("\nEnter target ID to permanently remove: ");
        int id = readIntegerInput();
        ingredientController.deleteIngredient(id);
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Invalid input. Please enter a valid number: ");
            }
        }
    }
}
