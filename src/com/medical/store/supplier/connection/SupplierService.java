package com.medical.store.supplier.connection;


import com.medical.store.supplier.Supplier;

import javax.swing.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierService {
    private final SupplierConnection supplierConnection = SupplierConnection.getInstance();

    public void addNewSupplier(String id_str, String name, String address, String phone, String email) {
        Pattern email_pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher email_matcher = email_pattern.matcher(email);
        boolean matchFound = email_matcher.matches();

        if (id_str.isBlank() || name.isBlank() || address.isBlank() || phone.isBlank() || email.isBlank()) {
            showMessage("Please fill out all fields!", "WARNING");
        } else if(!matchFound) {
            showMessage("Invalid email", "ERROR");
        } else {
            Supplier supplier = new Supplier(Integer.parseInt(id_str), name, address, phone, email);
            supplierConnection.addNewSupplier(supplier);
            showMessage("Supplier Added successfully!", "INFORMATION");
        }
    }

    public void updateSupplier(String id, String name, String address, String phone, String email) {
        Pattern email_pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher email_matcher = email_pattern.matcher(email);
        boolean matchFound = email_matcher.matches();

        if (!matchFound) {
            throw new RuntimeException("Invalid email");
        } else {
            supplierConnection.updateSupplier(id, name, address, phone, email);
        }
    }

    public void deleteSupplier(String id) {
        if(id.isBlank()) {
            showMessage("Please enter supplier id!", "WARNING");
        } else {
            supplierConnection.deleteSupplier(Integer.parseInt(id));
            showMessage("Supplier Deleted Successfully!", "INFORMATION");
        }
    }

    public List<Supplier> getSuppliers() {
        return supplierConnection.getSuppliers();
    }

    public Supplier getSupplier(int id) {
        return supplierConnection.getSupplier(id);
    }

    public void showMessage(String message, String type) {
        switch (type) {
            case "ERROR" -> JOptionPane.showMessageDialog(null, message, null, JOptionPane.ERROR_MESSAGE);
            case "WARNING" -> JOptionPane.showMessageDialog(null, message, null, JOptionPane.WARNING_MESSAGE);
            case "INFORMATION" -> JOptionPane.showMessageDialog(null, message, null, JOptionPane.INFORMATION_MESSAGE);
            default -> JOptionPane.showMessageDialog(null, message);
        }
    }

}