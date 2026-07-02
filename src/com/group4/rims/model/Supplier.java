
package com.group4.rims.model;

public class Supplier {
    private int supplierId;
    private String supplierName;
    private String contactNumber;
    private String address;

    public Supplier() {
    }

    public Supplier(int supplierId, String supplierName, 
                    String contactNumber, String address) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNUmber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}