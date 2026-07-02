
package com.group4.rims.controller;

import com.group4.rims.service.SupplierService;
import com.group4.rims.model.Supplier;
import java.util.List;

public class SupplierController {

    private final SupplierService supplierService;


    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    public void addSupplier(Supplier supplier) {
        try {
            if (supplier == null) {
                System.out.println("Error: Supplier details cannot be empty.");
                return;
            }
            supplierService.save(supplier);
            System.out.println("Success: Supplier '" + supplier.getSupplierName() + "' successfully registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public List<Supplier> getAllSuppliers() {
        return supplierService.findAll();
    }


    public Supplier getSupplierById(int id) {
        try {
            Supplier vendor = supplierService.findById(id);
            if (vendor == null) {
                System.out.println("Warning: No supplier found with tracking ID: " + id);
            }
            return vendor;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void updateSupplier(Supplier supplier) {
        try {
            if (supplier == null) {
                System.out.println("Error: Update target cannot be empty.");
                return;
            }

            if (supplierService.findById(supplier.getSupplierId()) == null) {
                System.out.println("Error: Cannot update. Supplier ID does not exist.");
                return;
            }
            supplierService.update(supplier);
            System.out.println("Success: Supplier profile metadata updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public void deleteSupplier(int id) {
        try {
            if (supplierService.findById(id) == null) {
                System.out.println("Error: Deletion failed. Supplier matching ID not found.");
                return;
            }
            supplierService.delete(id);
            System.out.println("Success: Vendor registration trace dropped from tracking logs.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}