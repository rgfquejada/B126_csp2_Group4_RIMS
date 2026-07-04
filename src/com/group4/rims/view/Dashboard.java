package com.group4.rims.view;

import java.util.Scanner;

public class Dashboard {
    private final IngredientView ingredientView;
    private final InventoryView inventoryView;
    private final MenuItemView menuitemView;
    private final RecipeIngredientView recipeView;
    private final StaffView staffView;
    private final SupplierView supplierView;
    private final Scanner scanner;
    private boolean running;

    // The constructor parameters now line up exactly with the sequence in your Main class
    public Dashboard(IngredientView ingredientView, InventoryView inventoryView, MenuItemView menuitemView,
                     RecipeIngredientView recipeView, StaffView staffView, SupplierView supplierView, Scanner scanner) {
        this.ingredientView = ingredientView;
        this.inventoryView = inventoryView;
        this.menuitemView = menuitemView;
        this.recipeView = recipeView;
        this.staffView = staffView;
        this.supplierView = supplierView;
        this.scanner = scanner;
        this.running = true;
    }

    public void start() {
        System.out.println("=================================================");
        System.out.println("  RIMS - Restaurant Inventory Management System");
        System.out.println("=================================================");

        while (running) {
            displayMainMenu();
            int choice = readIntegerInput();
            routeMainMenu(choice);
        }
        
        System.out.println("\n[!] Exiting system. Goodbye!");
    }

    private void displayMainMenu() {
        System.out.println("\nMAIN MENU DASHBOARD:");
        System.out.println("1. Manage Ingredients");
        System.out.println("2. Restaurant Menu Items");
        System.out.println("3. Inventory Stock Control");
        System.out.println("4. Recipe Configuration Matrix");
        System.out.println("5. Staff & Accounts Directory");
        System.out.println("6. Supplier & Vendor Networks");
        System.out.println("0. Exit Application");
        System.out.print("Enter choice: ");
    }

    private void routeMainMenu(int choice) {
        switch (choice) {
            case 1:
                ingredientView.displayMenu();
                break;
            case 2:
                menuitemView.displayMenu();
                break;
            case 3:
                inventoryView.displayMenu();
                break;
            case 4:
                recipeView.displayMenu();
                break;
            case 5:
                staffView.displayMenu();
                break;
            case 6:
                supplierView.displayMenu(); 
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("\n[ERROR] Invalid choice. Enter a valid number (0-6).");
                break;
        }
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Invalid numerical formatting. Re-enter selection: ");
            }
        }
    }
}
