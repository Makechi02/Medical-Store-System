package com.medical.store.medicine;

import com.medical.store.connections.Connections;
import com.medical.store.constants.MedicineType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class AddNewMedicine extends JFrame {

    private final JTextField batch_field = new JTextField();
    private final JTextField name_field = new JTextField();
    private final JTextField company_field = new JTextField();
    private final JTextField quantity_field = new JTextField();
    private final JTextField expiry_field = new JTextField();
    private final JTextField purchase_date_field = new JTextField();
    private final JTextField purchase_price_field = new JTextField();
    private final JTextField sale_field = new JTextField();
    private final JTextField rack_no_field = new JTextField();
    private final JComboBox<String> supplier_names_box = new JComboBox<>();
    private final JComboBox<MedicineType> type_box = new JComboBox<>(MedicineType.values());
    private String supplier, supplier_id, type;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final DefaultTableModel model = new DefaultTableModel();

    private final Connection connection = new Connections().getConnection();

    public AddNewMedicine() {
        super("Add New Medicine");
        setSize(950, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        Font font = new Font("Chilanka", Font.BOLD, 20);
        Font fields_font = new Font("Chilanka", Font.PLAIN, 18);
        Color background_color = Color.CYAN;

        JLabel heading = new JLabel("New Medicine Details");
        heading.setFont(new Font("Chilanka",Font.BOLD,30));
        heading.setBounds(0,40,getWidth(),40);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setForeground(Color.blue);
        add(heading);

        JLabel batch_label = new JLabel("Batch no");
        batch_label.setFont(font);
        batch_label.setBounds(50,100,200,25);
        add(batch_label);

        batch_field.setBounds(250, 100, 200, 25);
        batch_field.setToolTipText("Enter medicine batch no");
        batch_field.setFont(fields_font);
        add(batch_field);

        JLabel name_label = new JLabel("Name");
        name_label.setFont(font);
        name_label.setBounds(50,140,200,25);
        add(name_label);

        name_field.setBounds(250, 140, 200, 25);
        name_field.setToolTipText("Enter medicine name");
        name_field.setFont(fields_font);
        add(name_field);

        JLabel company_label = new JLabel("Company");
        company_label.setFont(font);
        company_label.setBounds(50,180,200,25);
        add(company_label);

        company_field.setBounds(250,180,200,25);
        company_field.setToolTipText("Enter medicine company");
        company_field.setFont(fields_font);
        add(company_field);

        JLabel quantity_label = new JLabel("Quantity");
        quantity_label.setFont(font);
        quantity_label.setBounds(50,220,200,25);
        add(quantity_label);

        quantity_field.setBounds(250, 220, 200, 25);
        quantity_field.setToolTipText("Enter medicine quantity");
        quantity_field.setFont(fields_font);
        add(quantity_field);

        JLabel expiry_label = new JLabel("Expiry Date");
        expiry_label.setFont(font);
        expiry_label.setBounds(50,260,200,25);
        add(expiry_label);

        expiry_field.setBounds(250, 260, 200, 25);
        expiry_field.setToolTipText("Enter medicine expiry date");
        expiry_field.setFont(fields_font);
        add(expiry_field);

        JLabel purchase_date_label = new JLabel("Purchase Date");
        purchase_date_label.setFont(font);
        purchase_date_label.setBounds(50,300,200,25);
        add(purchase_date_label);

        purchase_date_field.setBounds(250, 300, 200, 25);
        purchase_date_field.setToolTipText("Enter medicine purchase date");
        purchase_date_field.setFont(fields_font);
        add(purchase_date_field);

        Date date1 = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        String strDate = calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR);
        purchase_date_field.setText(strDate);

        JLabel type_label = new JLabel("Type");
        type_label.setFont(font);
        type_label.setBounds(500,100,200,25);
        add(type_label);

        type_box.setBounds(690, 100, 200, 25);
        type_box.setToolTipText("Select medicine type");
        type_box.addActionListener(e -> type = Objects.requireNonNull(type_box.getSelectedItem()).toString());
        type_box.setFont(fields_font);
        add(type_box);

        JLabel purchase_price_label = new JLabel("Purchase Price");
        purchase_price_label.setFont(font);
        purchase_price_label.setBounds(500,140,220,25);
        add(purchase_price_label);

        purchase_price_field.setBounds(690,140,200,25);
        purchase_price_field.setToolTipText("Enter medicine purchase price");
        purchase_price_field.setFont(fields_font);
        add(purchase_price_field);

        JLabel sale_label = new JLabel("Sale Price");
        sale_label.setFont(font);
        sale_label.setBounds(500,180,200,25);
        add(sale_label);

        sale_field.setBounds(690,180,200,25);
        sale_field.setToolTipText("Enter medicine sale price");
        sale_field.setFont(fields_font);
        add(sale_field);

        JLabel rack_no_label = new JLabel("Rack No");
        rack_no_label.setFont(font);
        rack_no_label.setBounds(500,220,200,25);
        add(rack_no_label);

        rack_no_field.setBounds(690,220,200,25);
        rack_no_field.setToolTipText("Enter medicine rack no");
        rack_no_field.setFont(fields_font);
        add(rack_no_field);

        JLabel supplier_name_label = new JLabel("Supplier Name");
        supplier_name_label.setFont(font);
        supplier_name_label.setBounds(500,260,250,25);
        add(supplier_name_label);

        supplier_names_box.setBounds(690,260,200,25);
        supplier_names_box.setToolTipText("select medicine supplier name");
        supplier_names_box.addActionListener(e -> supplier = Objects.requireNonNull(supplier_names_box.getSelectedItem()).toString());
        supplier_names_box.setFont(fields_font);
        add(supplier_names_box);

        try {
            preparedStatement = connection.prepareStatement("select name from suppliers");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String supplier_name = resultSet.getString(1);
                supplier_names_box.addItem(supplier_name);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        JPanel buttons_panel = new JPanel();
        buttons_panel.setBounds(0, 330, getWidth(), 50);
        buttons_panel.setBackground(background_color);

        JButton save_button = new JButton("Save", new ImageIcon("images//save.png"));
        save_button.setFont(fields_font);
        save_button.setToolTipText("click to save medicine details");
        save_button.addActionListener(e -> handleSave());
        buttons_panel.add(save_button);

        JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
        clear_button.setFont(fields_font);
        clear_button.setToolTipText("click to clear all text fields");
        clear_button.addActionListener(e -> handleClear());
        buttons_panel.add(clear_button);

        JButton all_button = new JButton("All", new ImageIcon("images//all.png"));
        all_button.setFont(fields_font);
        all_button.setToolTipText("click to view all medicine details");
        all_button.addActionListener(e -> handleAll());
        buttons_panel.add(all_button);

        add(buttons_panel);

        JTable tabGrid = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabGrid);
        scrollPane.setBounds(0,380, getWidth(), getHeight() - 420);
        add(scrollPane);
        tabGrid.setFont(new Font ("Chilanka", Font.PLAIN,16));

        model.addColumn("BATCH");
        model.addColumn("NAME");
        model.addColumn("COMPANY");
        model.addColumn("QUANTITY");
        model.addColumn("EXP_DATE");
        model.addColumn("PUR_DATE");
        model.addColumn("TYPE");
        model.addColumn("SALE_PRICE");
        model.addColumn("PUR_PRICE");
        model.addColumn("RACK_NO");
        model.addColumn("SUPPLIER_ID");
        model.addColumn("SUPPLIER_NAME");

        getContentPane().setBackground(background_color);
        setVisible(true);
    }

    private void handleSave() {
        String batch_no = batch_field.getText();
        String name = name_field.getText();
        String company = company_field.getText();
        String quantity = quantity_field.getText();
        String expiry_date = expiry_field.getText();
        String purchase_date = purchase_date_field.getText();
        String purchase_price_str = purchase_price_field.getText();
        String sale_price_str = sale_field.getText();
        String rack_no = rack_no_field.getText();

        try {
            if (
                    batch_no.isBlank()
                    || name.isBlank()
                    || company.isBlank()
                    || quantity.isBlank()
                    || expiry_date.isBlank()
                    || purchase_date.isBlank()
                    || purchase_price_str.isBlank()
                    || sale_price_str.isBlank()
                    || rack_no.isBlank()
            ) {
                showMessage("Please fill out all fields!");
            } else {
                float purchase_price = Float.parseFloat(purchase_price_str);
                float sale_price = Float.parseFloat(sale_price_str);
                if(sale_price < purchase_price) {
                    showMessage("Sale price should be greater than purchase price!");
                } else {
                    preparedStatement = connection.prepareStatement("Select id from suppliers where name = '" + supplier + "'");
                    resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()) {
                        supplier_id = resultSet.getString(1);
                    }

                    preparedStatement = connection.prepareStatement(
                            "insert into medicine " +
                                    "(batch_no,name,company,quantity,expiry_date,purchase_date,type,purchase_price,sale_price,rack_no,supplier_id,supplier_name) " +
                                    "values(?,?,?,?,?,?,?,?,?,?,?,?)"
                    );
                    preparedStatement.setString(1, batch_no);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, company);
                    preparedStatement.setInt(4, Integer.parseInt(quantity));
                    preparedStatement.setString(5, expiry_date);
                    preparedStatement.setString(6, purchase_date);
                    preparedStatement.setString(7, type);
                    preparedStatement.setFloat(8, purchase_price);
                    preparedStatement.setFloat(9, sale_price);
                    preparedStatement.setString(10, rack_no);
                    preparedStatement.setInt(11, Integer.parseInt(supplier_id));
                    preparedStatement.setString(12, supplier);
                    preparedStatement.executeUpdate();

                    int reply = JOptionPane.showConfirmDialog(this,"Medicine added successfully.Do you want add more Medicines?",null ,JOptionPane.YES_NO_OPTION);

                    if (reply == JOptionPane.YES_OPTION) {
                        setVisible(false);
                        new AddNewMedicine();
                    } else if (reply == JOptionPane.NO_OPTION) {
                        setVisible(false);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClear() {
        batch_field.setText("");
        name_field.setText("");
        company_field.setText("");
        quantity_field.setText("");
        expiry_field.setText("");
        purchase_date_field.setText("");
        purchase_price_field.setText("");
        sale_field.setText("");
        rack_no_field.setText("");
    }

    private void handleAll() {
        int row = 0;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT * from medicine");
            while(resultSet.next()) {
                model.insertRow(row++, new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12) });
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, null, JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        new AddNewMedicine();
    }
}
