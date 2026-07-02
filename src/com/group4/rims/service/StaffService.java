package com.group4.rims.service;

import com.group4.rims.model.StaffUser;
import com.group4.rims.repository.StaffRepo;
import java.util.List;

public class StaffService {

    private final StaffRepo staffRepo;

    public StaffService(StaffRepo staffRepo) {
        this.staffRepo = staffRepo;
    }

    public StaffUser findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid staff ID.");
        }

        return staffRepo.findById(id);
    }

    public StaffUser findByUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        return staffRepo.findByUsername(username);
    }

    public List<StaffUser> findAll() {
        return staffRepo.findAll();
    }

    public void save(StaffUser staffUser) {

        if (staffUser == null) {
            throw new IllegalArgumentException("Staff user cannot be null.");
        }

        if (staffUser.getUserName() == null || staffUser.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        if (staffUser.getPassword() == null || staffUser.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        if (staffUser.getFullName() == null || staffUser.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required.");
        }

        if (staffUser.getRole() == null || staffUser.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("Role is required.");
        }

        staffRepo.save(staffUser);
    }

    public void update(StaffUser staffUser) {

        if (staffUser == null) {
            throw new IllegalArgumentException("Staff user cannot be null.");
        }

        if (staffUser.getStaffId() <= 0) {
            throw new IllegalArgumentException("Invalid staff ID.");
        }

        if (staffUser.getUserName() == null || staffUser.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        if (staffUser.getPassword() == null || staffUser.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        if (staffUser.getFullName() == null || staffUser.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required.");
        }

        if (staffUser.getRole() == null || staffUser.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("Role is required.");
        }

        staffRepo.update(staffUser);
    }

    public void delete(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid staff ID.");
        }

        staffRepo.delete(id);
    }
}