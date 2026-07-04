
package com.group4.rims.view;

import com.group4.rims.controller.MenuItemController;
import com.group4.rims.model.MenuItem;
import java.util.List;
import java.util.Scanner;

public class MenuItemView {

    private final MenuItemController menuItemController;
    private final Scanner scanner;

    public MenuItemView(MenuItemController menuItemController, Scanner scanner) {
        this.menuItemController = menuItemController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- RESTAURANT MENU ITEMS ---");
            System.out.println("1. View Full Menu Card");
            System.out.println("2. Find Menu Item by ID");
            System.out.println("3. Register New Menu Entry");
            System.out.println("4. Modify Menu Item Info");
            System.out.println("5. Remove Item from Menu");
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
                    System.out.println("\n[ERROR] Invalid option choice. Use entries 1-6.");
                    break;
            }
        }
    }

    private void handleViewAll() {
        List<MenuItem> list = menuItemController.getAllMenuItems();
        System.out.println("\n==================================================================================================================");
        System.out.printf("%-10s | %-45s | %-25s | %-12s | %-10s\n", "MENU ID", "ITEM NAME", "CATEGORY", "PRICE", "STATUS");
        System.out.println("==================================================================================================================");
        
        if (list.isEmpty()) {
            System.out.println("                      No restaurant menu dishes registered yet.          ");
        } else {
            for (MenuItem m : list) {
                System.out.printf("%-10d | %-45s | %-25s | ₱%-11.2f | %-10s\n",
                        m.getMenuId(), m.getMenuName(), m.getCategory(), m.getPrice(), m.getStatus());
            }
        }
        System.out.println("==================================================================================================================");
    }

    private void handleFindById() {
        System.out.print("\nEnter target Menu Item ID to view details: ");
        int id = readIntegerInput();
        MenuItem m = menuItemController.getMenuItemById(id);
        
        if (m != null) {
            System.out.println("\n--- MENU RECORD DETAIL ---");
            System.out.println("Menu Track ID   : " + m.getMenuId());
            System.out.println("Dish/Item Name  : " + m.getMenuName());
            System.out.println("Menu Category   : " + m.getCategory());
            System.out.println("Base Price      : ₱" + m.getPrice());
            System.out.println("Inventory Status: " + m.getStatus());
        }
    }

    private void handleAdd() {
        System.out.println("\n--- REGISTER NEW MENU ENTRY ---");
        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Set Retail Price: ₱");
        double price = readDoubleInput();
        System.out.print("Enter Category (e.g., Appetizer, Main Course, Dessert): ");
        String cat = scanner.nextLine().trim();
        System.out.print("Set Current Status (Available / Out of Stock): ");
        String status = scanner.nextLine().trim();

        MenuItem item = new MenuItem(0, name, price, cat, status);
        menuItemController.addMenuItem(item);
    }

    private void handleUpdate() {
        System.out.print("\nEnter target Menu Item ID to modify: ");
        int id = readIntegerInput();
        MenuItem existing = menuItemController.getMenuItemById(id);
        if (existing == null) {
            return;
        }

        System.out.println("Press [Enter] without typing text to bypass updates and keep old variables.");
        System.out.print("New Name [" + existing.getMenuName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = existing.getMenuName();
        }

        System.out.print("New Base Price [₱" + existing.getPrice() + "]: ");
        String priceStr = scanner.nextLine().trim();
        double price = priceStr.isEmpty() ? existing.getPrice() : Double.parseDouble(priceStr);

        System.out.print("New Category [" + existing.getCategory() + "]: ");
        String cat = scanner.nextLine().trim();
        if (cat.isEmpty()) {
            cat = existing.getCategory();
        }

        System.out.print("New Status [" + existing.getStatus() + "]: ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) {
            status = existing.getStatus();
        }

        MenuItem updated = new MenuItem(id, name, price, cat, status);
        menuItemController.updateMenuItem(updated);
    }

    private void handleDelete() {
        System.out.print("\nEnter target Menu ID to drop from system: ");
        int id = readIntegerInput();
        menuItemController.deleteMenuItem(id);
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Identification index error. Provide a clean whole number selection: ");
            }
        }
    }

    private double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Decimal formatting error. Input a valid numerical retail price: ");
            }
        }
    }
}
