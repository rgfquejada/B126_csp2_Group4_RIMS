package com.group4.rims.view;

import com.group4.rims.controller.IngredientController;
import com.group4.rims.controller.InventoryController; // IMPORTED
import com.group4.rims.model.Ingredient;
import com.group4.rims.model.InventoryTransaction; // IMPORTED
import java.util.List;
import java.util.Scanner;

public class InventoryView {
    private final IngredientController ingredientController;
    private final InventoryController inventoryController; 
    private final Scanner scanner;

    
    public InventoryView(IngredientController ingredientController, InventoryController inventoryController, Scanner scanner) {
        this.ingredientController = ingredientController;
        this.inventoryController = inventoryController;
        this.scanner = scanner;
    }

    public InventoryView(IngredientController ingredientController, Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- INVENTORY STOCK CONTROL ---");
            System.out.println("1. Current Stock Levels");
            System.out.println("2. Run Critical Low Stock Alert Check");
            System.out.println("3. Quick Restock Ingredient");
            System.out.println("4. View Historical Transaction Logs"); 
            System.out.println("5. Back to Main Menu");
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
                    handleViewTransactions(); 
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid entry. Please choose 1-5.");
                    break;
            }
        }
    }

    private void handleStockStatus() {
        List<Ingredient> list = ingredientController.getAllIngredients();
        System.out.println("\n=======================================================");
        System.out.printf("%-5s | %-25s | %-12s | %-8s\n", "ID", "INGREDIENT NAME", "CURRENT STOCK", "UNIT");
        System.out.println("=======================================================");
        
        if (list.isEmpty()) {
            System.out.println("               No inventory items logged.              ");
        } else {
            for (Ingredient i : list) {
                System.out.printf("%-5d | %-25s | %-12d | %-8s\n",
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
        System.out.printf("%-5s | %-25s | %-10s | %-12s | %-8s\n", "ID", "NAME", "CURRENT", "REORDER LVL", "STATUS");
        System.out.println("=====================================================================");

        boolean zeroAlerts = true;
        for (Ingredient i : list) {
            if (i.getStockQuantity() <= i.getReorderLevel()) {
                zeroAlerts = false;
                String flag = i.getStockQuantity() == 0 ? "OUT OF STOCK" : "LOW STOCK";
                System.out.printf("%-5d | %-25s | %-10d | %-12d | %-8s\n",
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

        System.out.print("Enter Staff User ID authorizing this restock: ");
        int staffId = readIntegerInput();
        
        System.out.print("Enter optional remarks (or press enter for default): ");
        String remarks = scanner.nextLine().trim();
        if (remarks.isEmpty()) {
            remarks = "Quick restock addition of " + restockAmount + " " + existing.getUnit();
        }

        int newStockTotal = existing.getStockQuantity() + restockAmount;
        existing.setStockQuantity(newStockTotal);
        ingredientController.updateIngredient(existing);

        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setIngredientId(existing.getIngredientId());
        transaction.setStaffId(staffId);
        transaction.setTransactionType("RESTOCK"); 
        transaction.setQuantity(restockAmount);
        transaction.setTransactionDate(20260704); 
        transaction.setRemarks(remarks);


        inventoryController.addTransaction(transaction);
    }

 
    private void handleViewTransactions() {
        List<InventoryTransaction> list = inventoryController.getAllTransactions();
        System.out.println("\n======================================================================================");
        System.out.printf("%-6s | %-20s | %-8s | %-16s | %-8s | %-20s\n", 
                "TX ID", "INGREDIENT ID", "STAFF ID", "TYPE", "QTY", "REMARKS");
        System.out.println("======================================================================================");
        
        if (list.isEmpty()) {
            System.out.println("                         No inventory transactions logged.                          ");
        } else {
            for (InventoryTransaction tx : list) {
                System.out.printf("%-6d | %-20d | %-8d | %-16s | %-8d | %-20s\n",
                        tx.getTransactionId(), tx.getIngredientId(), tx.getStaffId(), 
                        tx.getTransactionType(), tx.getQuantity(), tx.getRemarks());
            }
        }
        System.out.println("======================================================================================");
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
