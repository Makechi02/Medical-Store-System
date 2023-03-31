package com.medical.store.reports;

import com.medical.store.connections.Connections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupplierWiseMedList extends JFrame {
    private final JTextField t1;
    private final Connection connection = new Connections().getConnection();
    private final DefaultTableModel model = new DefaultTableModel();

    public SupplierWiseMedList() {
        super("Supplier Wise Medicine Report");
        setSize(900,700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.cyan);

        Font f = new Font("Times New Roman", Font.BOLD, 20);
        setLayout(null);

        JLabel ln = new JLabel("Supplier wise Medicine report");
        ln.setFont(new Font("Times New Roman",Font.BOLD,25));
        ln.setForeground(Color.blue);
        ln.setBounds(300,30,500,25);
        add(ln);

        JLabel l1 = new JLabel("Enter Supplier name:");
        l1.setFont(f);
        l1.setBounds(50,100,200,25);
        add(l1);
        
        t1=new JTextField(10);
        t1.setBounds(250,100,200,25);
        t1.setToolTipText("Enter supplier name");
        add(t1);

        JButton submit_button = new JButton("Submit", new ImageIcon("images//open.png"));
        submit_button.setBounds(120,150,110,35);
        submit_button.setToolTipText("click to open supplier wise medicine report");
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
        tabGrid.setFont(new Font("Times New Roman", Font.PLAIN,15));
        
        model.addColumn("BNO");
        model.addColumn("NAME");
        model.addColumn("COMPANY");
        model.addColumn("QUANTITY");
        model.addColumn("EXP_DATE");
        model.addColumn("PUR_DATE");
        model.addColumn("TYPE");
        model.addColumn("PUR_PRICE");
        model.addColumn("SALE_PRICE");
        model.addColumn("RACK_NO");
        model.addColumn("SUPPLIER_ID");
        model.addColumn("SUPPLIER_NAME");
        
        setVisible(true);
    }

    private void handleSubmit() {
        int r = 0;
        try {
            if(((t1.getText()).equals(""))) {
                JOptionPane.showMessageDialog(this,"Please supplier name !","Warning!!!",JOptionPane.WARNING_MESSAGE);
            }
            else {
                int records_found = 0;
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery("SELECT batch_no,name,company,quantity,expiry_date,purchase_date,type,purchase_price,sale_price,rack_no,supplier_id,supplier_name from medicine where supplier_name='" + t1.getText() + "' ");
                while(resultSet.next()) {
                    model.insertRow(r++,new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12)});
                    records_found = 1;
                }
                if (records_found == 0) {
                    JOptionPane.showMessageDialog(null,"Not any medicine provide by given supplier","Dialog",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SupplierWiseMedList();
    }
}
