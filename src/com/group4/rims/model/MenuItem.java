
package com.group4.rims.model;

public class MenuItem {
    
    private int menuId;
    private String menuName;
    private double price;
    private String category;
    private String status;
    
    public MenuItem (){
        
    }

    public MenuItem(int menuId, String menuName, double price, 
                    String category, String status) {
        
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
