package com.group4.rims.service;

import com.group4.rims.model.InventoryTransaction;
import com.group4.rims.repository.InventoryTransactionRepo;
import java.util.List;

public class InventoryService {

    private final InventoryTransactionRepo inventoryRepo;

    public InventoryService(InventoryTransactionRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public InventoryTransaction findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid transaction ID.");
        }

        return inventoryRepo.findById(id);
    }

    public List<InventoryTransaction> findAll() {
        return inventoryRepo.findAll();
    }

    public void save(InventoryTransaction transaction) {

        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }

        if (transaction.getIngredientId() <= 0) {
            throw new IllegalArgumentException("Invalid ingredient ID.");
        }

        if (transaction.getStaffId() <= 0) {
            throw new IllegalArgumentException("Invalid staff ID.");
        }

        if (transaction.getTransactionType() == null ||
            transaction.getTransactionType().trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction type is required.");
        }

        if (transaction.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        inventoryRepo.save(transaction);
    }

    public void update(InventoryTransaction transaction) {

        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }

        if (transaction.getTransactionId() <= 0) {
            throw new IllegalArgumentException("Invalid transaction ID.");
        }

        if (transaction.getIngredientId() <= 0) {
            throw new IllegalArgumentException("Invalid ingredient ID.");
        }

        if (transaction.getStaffId() <= 0) {
            throw new IllegalArgumentException("Invalid staff ID.");
        }

        if (transaction.getTransactionType() == null ||
            transaction.getTransactionType().trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction type is required.");
        }

        if (transaction.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        inventoryRepo.update(transaction);
    }

    public void delete(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid transaction ID.");
        }

        inventoryRepo.delete(id);
    }
}