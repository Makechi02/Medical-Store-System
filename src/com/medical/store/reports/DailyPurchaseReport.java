package com.medical.store.reports;

import com.medical.store.connections.Connections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DailyPurchaseReport extends JFrame {
    private final JTextField t1;
    private final DefaultTableModel model = new DefaultTableModel();

    public DailyPurchaseReport() {
        super("Daily Purchase Report");
        setSize(900,700);
        setLocation(20,20);
        setResizable(false);
        getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);

        Font font = new Font("Times New Roman", Font.BOLD, 20);
        setLayout(null);

        JLabel ln = new JLabel("Daily purchase report");
        ln.setFont(new Font("Times New Roman",Font.BOLD,25));
        ln.setForeground(Color.blue);
        ln.setBounds(300,30,300,25);
        add(ln);

        JLabel l1 = new JLabel("Enter purchase report date:");
        l1.setFont(font);
        l1.setBounds(50,100,250,25);
        add(l1);

        t1 = new JTextField(10);
        t1.setBounds(300,100,100,25);
        t1.setToolTipText("Enter purchase report date");
        add(t1);

        JButton submit_button = new JButton("Submit", new ImageIcon("images//open.png"));
        submit_button.setBounds(120,150,110,35);
        submit_button.setToolTipText("click to open daily purchase report");
        submit_button.addActionListener(e -> handleSubmit());
        add(submit_button);

        JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
        clear_button.setBounds(300,150,110,35);
        clear_button.setToolTipText("click to clear text field");
        clear_button.addActionListener(e -> t1.setText(""));
        add(clear_button);

        JTable tabGrid = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabGrid);
        scrollPane.setBounds(0,200,900,600);
        add(scrollPane);
        tabGrid.setFont(new Font ("Times New Roman", Font.PLAIN,15));

        model.addColumn("SUPPLIER_ID");
        model.addColumn("SUPPLIER_NAME");
        model.addColumn("BATCH");
        model.addColumn("NAME");
        model.addColumn("EXP_DATE");
        model.addColumn("QUANTITY");
        model.addColumn("PUR_PRICE");
        model.addColumn("SALE_PRICE");

        setVisible(true);
    }

    private void handleSubmit() {
        Connection connection = new Connections().getConnection();
        int row = 0;
        try {
            if((t1.getText()).equals("")) {
                JOptionPane.showMessageDialog(this,"Please enter purchase date !","Warning!!!",JOptionPane.WARNING_MESSAGE);
            } else {
                int records_found = 0;
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery(
                        "SELECT supplier_id,supplier_name,batch_no,name,expiry_date,quantity,purchase_price,sale_price from medicine where purchase_date='" + t1.getText() + "' "
                );
                while(resultSet.next()) {
                    model.insertRow(row++,new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)});
                    records_found = 1;
                }
                if (records_found == 0) {
                    JOptionPane.showMessageDialog(null,"Not any medicine purchase on given date","Dialog",JOptionPane.WARNING_MESSAGE);
                }
            }
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DailyPurchaseReport();
    }

}
