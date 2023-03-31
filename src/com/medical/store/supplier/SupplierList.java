package com.medical.store.supplier;

import com.medical.store.connections.Connections;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class SupplierList extends JFrame {

    public SupplierList() {
        setTitle("Supplier List");
        setSize(900,700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.cyan);
        setLayout(null);

        JLabel label = new JLabel("List Of Supplier Details");
        label.setFont(new Font("Chilanka",Font.BOLD,25));
        label.setForeground(Color.blue);
        label.setBounds(0,30,getWidth(),40);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);

        DefaultTableModel model = new DefaultTableModel();
        JTable tabGrid = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabGrid);
        scrollPane.setBounds(0,80,900,600);
        add(scrollPane);
        tabGrid.setFont(new Font ("Chilanka", Font.PLAIN,15));

        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("ADDRESS");
        model.addColumn("PHONE NO.");
        model.addColumn("EMAIL");

        int row = 0;
        try {
            Connection connection = new Connections().getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM suppliers");
            while(resultSet.next()) {
                model.insertRow(row++,new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)});
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new SupplierList();
    }

}
