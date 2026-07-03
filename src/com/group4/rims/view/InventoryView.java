
package com.group4.rims.view;

import com.group4.rims.controller.IngredientController;
import com.group4.rims.model.Ingredient;
import java.util.List;
import java.util.Scanner;

public class InventoryView {
    private final IngredientController ingredientController;
    private final Scanner scanner;

    public InventoryView(IngredientController ingredientController, Scanner scanner) {
        this.ingredientController = ingredientController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- INVENTORY STOCK CONTROL ---");
            System.out.println("1. Current Stock Levels");
            System.out.println("2. Run Critical Low Stock Alert Check");
            System.out.println("3. Quick Restock Ingredient");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select action: ");

            int action = readIntegerInput();
            switch (action) {
                case 1:
                    handleStockStatus();
                    break;
                case 2:
                    handleLowStockAlerts();
                    break;
                case 3:
                    handleQuickRestock();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid entry. Please choose 1-4.");
                    break;
            }
        }
    }

    private void handleStockStatus() {
        List<Ingredient> list = ingredientController.getAllIngredients();
        System.out.println("\n=======================================================");
        System.out.printf("%-5s | %-20s | %-12s | %-8s\n", "ID", "INGREDIENT NAME", "CURRENT STOCK", "UNIT");
        System.out.println("=======================================================");
        
        if (list.isEmpty()) {
            System.out.println("               No inventory items logged.              ");
        } else {
            for (Ingredient i : list) {
                System.out.printf("%-5d | %-20s | %-12d | %-8s\n",
                        i.getIngredientId(), i.getIngredientName(), i.getStockQuantity(), i.getUnit());
            }
        }
        System.out.println("=======================================================");
    }

    private void handleLowStockAlerts() {
        List<Ingredient> list = ingredientController.getAllIngredients();
        System.out.println("\n=====================================================================");
        System.out.println("                  ⚠️ CRITICAL LOW STOCK WARNINGS ⚠️                 ");
        System.out.println("=====================================================================");
        System.out.printf("%-5s | %-20s | %-10s | %-12s | %-8s\n", "ID", "NAME", "CURRENT", "REORDER LVL", "STATUS");
        System.out.println("=====================================================================");

        boolean zeroAlerts = true;
        for (Ingredient i : list) {

            if (i.getStockQuantity() <= i.getReorderLevel()) {
                zeroAlerts = false;
                String flag = i.getStockQuantity() == 0 ? "OUT OF STOCK" : "LOW STOCK";
                System.out.printf("%-5d | %-20s | %-10d | %-12d | %-8s\n",
                        i.getIngredientId(), i.getIngredientName(), i.getStockQuantity(), i.getReorderLevel(), flag);
            }
        }

        if (zeroAlerts) {
            System.out.println("            ✨ All tracked stock items are at healthy levels.            ");
        }
        System.out.println("=====================================================================");
    }

    private void handleQuickRestock() {
        System.out.print("\nEnter Ingredient ID to restock: ");
        int id = readIntegerInput();
        Ingredient existing = ingredientController.getIngredientById(id);
        
        if (existing == null) {
            return; 
        }

        System.out.println("Current Stock for '" + existing.getIngredientName() + "' is: " + existing.getStockQuantity() + " " + existing.getUnit());
        System.out.print("Enter quantity amount to ADD into inventory storage: ");
        int restockAmount = readIntegerInput();

        if (restockAmount <= 0) {
            System.out.println("[ERROR] Restock addition amount must be greater than zero.");
            return;
        }

        int newStockTotal = existing.getStockQuantity() + restockAmount;
        existing.setStockQuantity(newStockTotal);

        ingredientController.updateIngredient(existing);
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
