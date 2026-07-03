
package com.group4.rims.view;

import com.group4.rims.controller.StaffController;
import com.group4.rims.model.StaffUser;
import java.util.List;
import java.util.Scanner;

public class StaffView {
    private final StaffController staffController;
    private final Scanner scanner;

    public StaffView(StaffController staffController, Scanner scanner) {
        this.staffController = staffController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- STAFF & PERSONNEL ADMINISTRATION ---");
            System.out.println("1. View Active Staff Roster");
            System.out.println("2. Find Staff Member by ID");
            System.out.println("3. Register New Employee Account");
            System.out.println("4. Modify Staff Account Metadata");
            System.out.println("5. Remove Employee Profile");
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
                    System.out.println("\n[ERROR] Invalid option entry. Choose values 1-6.");
                    break;
            }
        }
    }

    private void handleViewAll() {
        List<StaffUser> list = staffController.getAllStaffUsers();
        System.out.println("\n==============================================================================");
        System.out.printf("%-5s | %-15s | %-25s | %-20s\n", "ID", "USERNAME", "FULL NAME", "ASSIGNED ROLE");
        System.out.println("==============================================================================");
        
        if (list.isEmpty()) {
            System.out.println("                       No administrative staff profiles loaded.               ");
        } else {
            for (StaffUser u : list) {
                System.out.printf("%-5d | %-15s | %-25s | %-20s\n",
                        u.getStaffId(), u.getUserName(), u.getFullName(), u.getRole());
            }
        }
        System.out.println("==============================================================================");
    }

    private void handleFindById() {
        System.out.print("\nEnter target Staff Account ID to fetch: ");
        int id = readIntegerInput();
        StaffUser u = staffController.getStaffUserById(id);
        
        if (u != null) {
            System.out.println("\n--- PERSONNEL PROFILE FOUND ---");
            System.out.println("Account Tracking ID: " + u.getStaffId());
            System.out.println("System Access Username: " + u.getUserName());
            System.out.println("Employee Full Name: " + u.getFullName());
            System.out.println("Authorization Role Level: " + u.getRole());
        }
    }

    private void handleAdd() {
        System.out.println("\n--- REGISTER NEW ADMINISTRATIVE SYSTEM USER ---");
        System.out.print("Set Login Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Set Account Password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter Employee Full Name: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Enter Enterprise Role (e.g., Manager, Chef, Cashier): ");
        String role = scanner.nextLine().trim();

        StaffUser newUser = new StaffUser(0, username, password, fullName, role);
        staffController.addStaffUser(newUser);
    }

    private void handleUpdate() {
        System.out.print("\nEnter target Staff Account ID to update: ");
        int id = readIntegerInput();
        StaffUser existing = staffController.getStaffUserById(id);
        if (existing == null) {
            return;
        }

        System.out.println("Press [Enter] without text to preserve existing record parameters.");
        System.out.print("New Username [" + existing.getUserName() + "]: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            username = existing.getUserName();
        }

        System.out.print("New Password [********]: ");
        String password = scanner.nextLine().trim();
        if (password.isEmpty()) {
            password = existing.getPassword();
        }

        System.out.print("New Full Name [" + existing.getFullName() + "]: ");
        String fullName = scanner.nextLine().trim();
        if (fullName.isEmpty()) {
            fullName = existing.getFullName();
        }

        System.out.print("New Assigned Role [" + existing.getRole() + "]: ");
        String role = scanner.nextLine().trim();
        if (role.isEmpty()) {
            role = existing.getRole();
        }

        StaffUser updated = new StaffUser(id, username, password, fullName, role);
        staffController.updateStaffUser(updated);
    }

    private void handleDelete() {
        System.out.print("\nEnter target Staff Account ID for permanent removal: ");
        int id = readIntegerInput();
        staffController.deleteStaffUser(id);
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Invalid format input. Enter a clean whole number: ");
            }
        }
    }
}
