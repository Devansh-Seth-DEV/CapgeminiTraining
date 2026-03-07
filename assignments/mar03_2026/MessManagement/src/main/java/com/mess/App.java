package com.mess;

import com.mess.dao.MessDAO;
import com.mess.util.EntityManagerFactoryProvider;

public class App {
    public static void main(String[] args) {
        MessDAO dao = new MessDAO();

        dao.populateDatabase();

        System.out.println("Supplier Names: " + dao.getAllSupplierNames());
        
        System.out.println("Suppliers in Delhi:");
        dao.getSuppliersByCity("Delhi")
        .forEach(System.out::println);

        System.out.println("Items from Supplier 1: " + dao.getItemNamesBySupplierId(1));

        EntityManagerFactoryProvider.closeFactory();
    }
}