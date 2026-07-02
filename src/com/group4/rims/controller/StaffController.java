
package com.group4.rims.controller;

import com.group4.rims.service.StaffService;
import com.group4.rims.model.StaffUser;
import java.util.List;

public class StaffController {

    private final StaffService staffService;


    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    public void addStaffUser(StaffUser staffUser) {
        try {
            if (staffUser == null) {
                System.out.println("Error: Staff details cannot be empty.");
                return;
            }
            staffService.save(staffUser);
            System.out.println("Success: Staff member '" + staffUser.getFullName() + "' successfully registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public List<StaffUser> getAllStaffUsers() {
        return staffService.findAll();
    }


    public StaffUser getStaffUserById(int id) {
        try {
            StaffUser user = staffService.findById(id);
            if (user == null) {
                System.out.println("Warning: No user found with account ID: " + id);
            }
            return user;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public StaffUser getStaffUserByUsername(String username) {
        try {
            StaffUser user = staffService.findByUsername(username);
            if (user == null) {
                System.out.println("Warning: No user found with username: " + username);
            }
            return user;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void updateStaffUser(StaffUser staffUser) {
        try {
            if (staffUser == null) {
                System.out.println("Error: Update target cannot be empty.");
                return;
            }

            if (staffService.findById(staffUser.getStaffId()) == null) {
                System.out.println("Error: Cannot update. Staff ID does not exist.");
                return;
            }
            staffService.update(staffUser);
            System.out.println("Success: Staff user configuration updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public void deleteStaffUser(int id) {
        try {
            if (staffService.findById(id) == null) {
                System.out.println("Error: Deletion failed. Account matching ID not found.");
                return;
            }
            staffService.delete(id);
            System.out.println("Success: User profile deleted from local access logs.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public boolean verifyLoginCredentials(String username, String password) {
        try {
            StaffUser user = staffService.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Login Success: Welcome " + user.getFullName() + " [" + user.getRole() + "]");
                return true;
            }
            System.out.println("Login Failed: Incorrect username or password.");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Login Error: " + e.getMessage());
            return false;
        }
    }
}