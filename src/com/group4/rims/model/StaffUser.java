
package com.group4.rims.model;

public class StaffUser {
    
    private int staffId;
    private String userName;
    private String password;
    private String fullName;
    private String role;

    public StaffUser() {

    }

    public StaffUser(int staffId, String userName, String password,
            String fullName, String role) {

        this.staffId = staffId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        
    }

    public int getStaffId() {
        return staffId;
    }
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
