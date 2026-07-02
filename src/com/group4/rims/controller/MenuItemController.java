
package com.group4.rims.controller;

import com.group4.rims.service.MenuItemService;
import com.group4.rims.model.MenuItem;
import java.util.List;

public class MenuItemController {

    private final MenuItemService menuItemService;


    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

 
    public void addMenuItem(MenuItem menuItem) {
        try {
            if (menuItem == null) {
                System.out.println("Error: Menu item details cannot be empty.");
                return;
            }
            menuItemService.save(menuItem);
            System.out.println("Success: Menu item '" + menuItem.getMenuName() + "' successfully registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public List<MenuItem> getAllMenuItems() {
        return menuItemService.findAll();
    }


    public MenuItem getMenuItemById(int id) {
        try {
            MenuItem item = menuItemService.findById(id);
            if (item == null) {
                System.out.println("Warning: No menu item found with tracking ID: " + id);
            }
            return item;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public void updateMenuItem(MenuItem menuItem) {
        try {
            if (menuItem == null) {
                System.out.println("Error: Update target cannot be empty.");
                return;
            }

            if (menuItemService.findById(menuItem.getMenuId()) == null) {
                System.out.println("Error: Cannot update. Menu item ID does not exist.");
                return;
            }
            menuItemService.update(menuItem);
            System.out.println("Success: Menu item metadata updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }


    public void deleteMenuItem(int id) {
        try {
            if (menuItemService.findById(id) == null) {
                System.out.println("Error: Deletion failed. Menu item matching ID not found.");
                return;
            }
            menuItemService.delete(id);
            System.out.println("Success: Menu item configuration deleted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}