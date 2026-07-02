package com.group4.rims.service;

import java.sql.Connection;

import com.group4.rims.config.DbConnection;
import com.group4.rims.repository.SupplierRepo;
import com.group4.rims.repository.SupplierRepoImpl;

public class ServiceTest {

    public static void main(String[] args) {

        try {

            Connection connection = DbConnection.getConnection();

            SupplierRepo repo = new SupplierRepoImpl(connection);

            SupplierService service = new SupplierService(repo);

            System.out.println("Service initialized successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}