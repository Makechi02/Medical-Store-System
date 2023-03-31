package com.medical.store.medicine;

import com.medical.store.connections.Connections;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class MedicineList extends JFrame {
    public MedicineList() {
        super("Medicine List");
        setSize(900,700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.cyan);
        setLayout(null);

        JLabel ln = new JLabel("Stock Of Medicines");
        ln.setFont(new Font("Times New Roman",Font.BOLD,25));
        ln.setForeground(Color.blue);
        ln.setBounds(300,30,300,25);
        add(ln);

        DefaultTableModel model = new DefaultTableModel();
        JTable tabGrid = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabGrid);
        scrollPane.setBounds(0,80,900,600);
        add(scrollPane);
        tabGrid.setFont(new Font ("Times New Roman", Font.PLAIN,15));

        model.addColumn("Batch no");
        model.addColumn("Name");
        model.addColumn("Company");
        model.addColumn("Quantity");
        model.addColumn("Type");
        model.addColumn("Purchase date");
        model.addColumn("Expiry date");
        model.addColumn("Purchase price");
        model.addColumn("Sale price");
        model.addColumn("Rack no");
        model.addColumn("Supplier id");
        model.addColumn("supplier name");

        int row = 0;
        try {
            Connection connection = new Connections().getConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from medicine");
            while(rs.next()) {
                model.insertRow(row++,new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)});
            }
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
    	new MedicineList();
    }
}
