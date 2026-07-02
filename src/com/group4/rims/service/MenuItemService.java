package com.group4.rims.service;

import com.group4.rims.model.MenuItem;
import com.group4.rims.repository.MenuItemRepo;
import java.util.List;

public class MenuItemService {

    private final MenuItemRepo menuItemRepo;

    public MenuItemService(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    public MenuItem findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid menu item ID.");
        }

        return menuItemRepo.findById(id);
    }

    public List<MenuItem> findAll() {
        return menuItemRepo.findAll();
    }

    public void save(MenuItem menuItem) {

        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }

        if (menuItem.getMenuName() == null || menuItem.getMenuName().trim().isEmpty()) {
            throw new IllegalArgumentException("Menu name is required.");
        }

        if (menuItem.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (menuItem.getCategory() == null || menuItem.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required.");
        }

        if (menuItem.getStatus() == null || menuItem.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status is required.");
        }

        menuItemRepo.save(menuItem);
    }

    public void update(MenuItem menuItem) {

        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }

        if (menuItem.getMenuId() <= 0) {
            throw new IllegalArgumentException("Invalid menu item ID.");
        }

        if (menuItem.getMenuName() == null || menuItem.getMenuName().trim().isEmpty()) {
            throw new IllegalArgumentException("Menu name is required.");
        }

        if (menuItem.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (menuItem.getCategory() == null || menuItem.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required.");
        }

        if (menuItem.getStatus() == null || menuItem.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status is required.");
        }

        menuItemRepo.update(menuItem);
    }

    public void delete(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid menu item ID.");
        }

        menuItemRepo.delete(id);
    }
}