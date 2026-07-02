
package com.group4.rims.controller;

import com.group4.rims.service.InventoryService;
import com.group4.rims.model.InventoryTransaction;
import java.util.List;

public class InventoryController {

    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    public void addTransaction(InventoryTransaction transaction) {
        try {
            if (transaction == null) {
                System.out.println("Error: Transaction data cannot be empty.");
                return;
            }
            inventoryService.save(transaction);
            System.out.println("Success: Transaction log record #" + transaction.getTransactionId() + " successfully saved.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public List<InventoryTransaction> getAllTransactions() {
        return inventoryService.findAll();
    }


    public InventoryTransaction getTransactionById(int id) {
        try {
            InventoryTransaction log = inventoryService.findById(id);
            if (log == null) {
                System.out.println("Warning: No transaction found with ID: " + id);
            }
            return log;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void updateTransaction(InventoryTransaction transaction) {
        try {
            if (transaction == null) {
                System.out.println("Error: Update target cannot be empty.");
                return;
            }

            if (inventoryService.findById(transaction.getTransactionId()) == null) {
                System.out.println("Error: Cannot update. Transaction ID does not exist.");
                return;
            }
            inventoryService.update(transaction);
            System.out.println("Success: Transaction details updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public void deleteTransaction(int id) {
        try {
            if (inventoryService.findById(id) == null) {
                System.out.println("Error: Deletion failed. Transaction index matching ID not found.");
                return;
            }
            inventoryService.delete(id);
            System.out.println("Success: Transaction log trace deleted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
