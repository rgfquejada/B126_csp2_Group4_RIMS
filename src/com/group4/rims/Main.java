package com.group4.rims;

import com.group4.rims.config.DbConnection;
import com.group4.rims.controller.*;
import com.group4.rims.repository.*;
import com.group4.rims.service.*;
import com.group4.rims.view.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        Scanner scanner = null;

        try {
            // 1. Core Connection Infrastructure Setup
            connection = DbConnection.connect();
            scanner = new Scanner(System.in);

            System.out.println("[SYSTEM] Database handshake successful with local XAMPP engine.");
            System.out.println("[SYSTEM] Booting RIMS Operational Framework environment...\n");

            // 2. Repositories
            IngredientRepo ingredientRepo = new IngredientRepoImpl(connection);
            MenuItemRepo menuItemRepo = new MenuItemRepoImpl(connection);
            RecipeIngredientRepo recipeRepo = new RecipeIngredientRepoImpl(connection);
            StaffRepo staffRepo = new StaffRepoImpl(connection);
            SupplierRepo supplierRepo = new SupplierRepoImpl(connection);
            InventoryTransactionRepo inventoryRepo = new InventoryTransactionRepoImpl(connection);
            InventoryService inventoryService = new InventoryService(inventoryRepo);
            InventoryController inventoryController = new InventoryController(inventoryService);

            // 3. Services
            IngredientService ingredientService = new IngredientService(ingredientRepo);
            MenuItemService menuItemService = new MenuItemService(menuItemRepo);
            RecipeIngredientService recipeService = new RecipeIngredientService(recipeRepo);
            StaffService staffService = new StaffService(staffRepo);
            SupplierService supplierService = new SupplierService(supplierRepo);

            // 4. Controllers
            IngredientController ingredientController = new IngredientController(ingredientService);
            MenuItemController menuItemController = new MenuItemController(menuItemService);
            RecipeIngredientController recipeController = new RecipeIngredientController(recipeService);
            StaffController staffController = new StaffController(staffService);
            SupplierController supplierController = new SupplierController(supplierService);

            // 5. Views
            IngredientView ingredientView = new IngredientView(ingredientController, scanner);
            InventoryView inventoryView = new InventoryView(ingredientController, inventoryController, scanner);
            MenuItemView menuitemView = new MenuItemView(menuItemController, scanner);
            RecipeIngredientView recipeView = new RecipeIngredientView(recipeController, scanner);
            StaffView staffView = new StaffView(staffController, scanner);
            SupplierView supplierView = new SupplierView(supplierController, scanner);


            Dashboard dashboard = new Dashboard(
                    ingredientView,
                    inventoryView,
                    menuitemView,
                    recipeView,
                    staffView,
                    supplierView,
                    scanner
            );

            dashboard.start();

        } catch (Exception e) {
            System.err.println("\n[CRITICAL FAILURE] Cannot establish active pipe stream to local MySQL engine.");
            System.err.println("Diagnostic Stack Trace Message: " + e.getMessage());
            System.err.println("Remedy: Double-check if your XAMPP Apache and MySQL service flags are green.");
        } finally {
            //  Resource Allocation Drop Control
            System.out.println("\n[SYSTEM] Disengaging application layer runtimes...");
            if (scanner != null) {
                scanner.close();
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("[SYSTEM] Relational database stream link dropped cleanly.");
                } catch (SQLException e) {
                    System.err.println("[ERROR] Exception trace detected during server stream termination: " + e.getMessage());
                }
            }
            System.out.println("[SYSTEM] Process termination status verified. Exiting.");
        }
    }
}
