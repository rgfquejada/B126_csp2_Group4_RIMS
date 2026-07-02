package com.group4.rims.service;

import com.group4.rims.model.Supplier;
import com.group4.rims.repository.SupplierRepo;
import java.util.List;

public class SupplierService {

    private final SupplierRepo supplierRepo;

    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public Supplier findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid supplier ID.");
        }

        return supplierRepo.findById(id);
    }

    public List<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public void save(Supplier supplier) {

        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null.");
        }

        if (supplier.getSupplierName() == null || supplier.getSupplierName().trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier name is required.");
        }

        if (supplier.getContactNumber() == null || supplier.getContactNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Contact number is required.");
        }

        if (supplier.getAddress() == null || supplier.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Address is required.");
        }

        supplierRepo.save(supplier);
    }

    public void update(Supplier supplier) {

        if (supplier == null) {
            throw new IllegalArgumentException("Supplier cannot be null.");
        }

        if (supplier.getSupplierId() <= 0) {
            throw new IllegalArgumentException("Invalid supplier ID.");
        }

        if (supplier.getSupplierName() == null || supplier.getSupplierName().trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier name is required.");
        }

        if (supplier.getContactNumber() == null || supplier.getContactNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Contact number is required.");
        }

        if (supplier.getAddress() == null || supplier.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Address is required.");
        }

        supplierRepo.update(supplier);
    }

    public void delete(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid supplier ID.");
        }

        supplierRepo.delete(id);
    }
}