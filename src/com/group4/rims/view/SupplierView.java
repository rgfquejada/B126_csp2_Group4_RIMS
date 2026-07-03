
package com.group4.rims.view;

import com.group4.rims.controller.SupplierController;
import com.group4.rims.model.Supplier;
import java.util.List;
import java.util.Scanner;

public class SupplierView {
    private final SupplierController supplierController;
    private final Scanner scanner;

    public SupplierView(SupplierController supplierController, Scanner scanner) {
        this.supplierController = supplierController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- SUPPLIER VENDOR NETWORK ---");
            System.out.println("1. View Active Supplier Profiles");
            System.out.println("2. Find Supplier by ID");
            System.out.println("3. Register New Vendor Profile");
            System.out.println("4. Modify Supplier Information");
            System.out.println("5. Remove Supplier Record");
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
                    System.out.println("\n[ERROR] Invalid choice option. Use entries 1-6.");
                    break;
            }
        }
    }

    private void handleViewAll() {
        List<Supplier> list = supplierController.getAllSuppliers();
        System.out.println("\n==========================================================================================");
        System.out.printf("%-5s | %-25s | %-15s | %-35s\n", "ID", "SUPPLIER NAME", "CONTACT NUMBER", "OFFICE ADDRESS");
        System.out.println("==========================================================================================");
        
        if (list.isEmpty()) {
            System.out.println("                         No active supplier registry tracking logs found.                   ");
        } else {
            for (Supplier s : list) {
                System.out.printf("%-5d | %-25s | %-15s | %-35s\n",
                        s.getSupplierId(), s.getSupplierName(), s.getContactNumber(), s.getAddress());
            }
        }
        System.out.println("==========================================================================================");
    }

    private void handleFindById() {
        System.out.print("\nEnter target Supplier Tracking ID to fetch: ");
        int id = readIntegerInput();
        Supplier s = supplierController.getSupplierById(id);
        
        if (s != null) {
            System.out.println("\n--- SUPPLIER PROFILE RECORD ---");
            System.out.println("Supplier ID: " + s.getSupplierId());
            System.out.println("Company Name: " + s.getSupplierName());
            System.out.println("Contact Number: " + s.getContactNumber());
            System.out.println("Business Address: " + s.getAddress());
        }
    }

    private void handleAdd() {
        System.out.println("\n--- REGISTER NEW VENDOR NETWORK SUPPLIER ---");
        System.out.print("Enter Supplier Company Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Office Contact Number: ");
        String contact = scanner.nextLine().trim();
        System.out.print("Enter Corporate Street Address: ");
        String address = scanner.nextLine().trim();

        Supplier newSupplier = new Supplier(0, name, contact, address);
        supplierController.addSupplier(newSupplier);
    }

    private void handleUpdate() {
        System.out.print("\nEnter target Supplier Tracking ID to update: ");
        int id = readIntegerInput();
        Supplier existing = supplierController.getSupplierById(id);
        if (existing == null) {
            return;
        }

        System.out.println("Press [Enter] with empty inputs to preserve current registry details.");
        System.out.print("New Company Name [" + existing.getSupplierName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = existing.getSupplierName();
        }

        System.out.print("New Contact Number [" + existing.getContactNumber() + "]: ");
        String contact = scanner.nextLine().trim();
        if (contact.isEmpty()) {
            contact = existing.getContactNumber();
        }

        System.out.print("New Business Address [" + existing.getAddress() + "]: ");
        String address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            address = existing.getAddress();
        }

        Supplier updated = new Supplier(id, name, contact, address);
        supplierController.updateSupplier(updated);
    }

    private void handleDelete() {
        System.out.print("\nEnter target Supplier Tracking ID for profile removal: ");
        int id = readIntegerInput();
        supplierController.deleteSupplier(id);
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Invalid formatting choice. Input an integer option selection: ");
            }
        }
    }
}
