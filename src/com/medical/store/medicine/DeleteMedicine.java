package com.medical.store.medicine;

import com.medical.store.connections.Connections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DeleteMedicine extends JFrame {
	private final JTextField batch_field = new JTextField();
	private final JTextField name_field = new JTextField();
	private final JTextField company_field = new JTextField();
	private final JTextField quantity_field = new JTextField();
	private final JTextField expiry_field = new JTextField();
	private final JTextField purchase_date_field = new JTextField();
	private final JTextField type_field = new JTextField();
	private final JTextField purchase_price_field = new JTextField();
	private final JTextField sale_field = new JTextField();
	private final JTextField rack_no_field = new JTextField();
	private final JTextField supplier_name_field = new JTextField();
	private final JTextField supplier_id_field = new JTextField();

	private final Connection connection = new Connections().getConnection();
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private final DefaultTableModel model = new DefaultTableModel();

	public DeleteMedicine() {
		super("Delete Medicine");
		setSize(950,720);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		Font font = new Font("Chilanka", Font.BOLD, 20);
		Font fields_font = new Font("Chilanka", Font.PLAIN, 18);
		Color background_color = Color.CYAN;

		JLabel heading = new JLabel("Delete Medicine");
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
		batch_field.setToolTipText("Enter medicine batch no to delete medicine");
		batch_field.setFont(fields_font);
		add(batch_field);

		JLabel name_label = new JLabel("Name");
		name_label.setFont(font);
		name_label.setBounds(50,140,200,25);
		add(name_label);

		name_field.setBounds(250, 140, 200, 25);
		name_field.setToolTipText("Enter medicine name to delete medicine");
		name_field.setFont(fields_font);
		add(name_field);

		JLabel company_label = new JLabel("Company");
		company_label.setFont(font);
		company_label.setBounds(50,180,200,25);
		add(company_label);

		company_field.setBounds(250,180,200,25);
		company_field.setFont(fields_font);
		company_field.setEditable(false);
		add(company_field);

		JLabel quantity_label = new JLabel("Quantity");
		quantity_label.setFont(font);
		quantity_label.setBounds(50,220,200,25);
		add(quantity_label);

		quantity_field.setBounds(250, 220, 200, 25);
		quantity_field.setFont(fields_font);
		quantity_field.setEditable(false);
		add(quantity_field);

		JLabel expiry_label = new JLabel("Expiry Date");
		expiry_label.setFont(font);
		expiry_label.setBounds(50,260,200,25);
		add(expiry_label);

		expiry_field.setBounds(250, 260, 200, 25);
		expiry_field.setFont(fields_font);
		expiry_field.setEditable(false);
		add(expiry_field);

		JLabel purchase_date_label = new JLabel("Purchase Date");
		purchase_date_label.setFont(font);
		purchase_date_label.setBounds(50,300,200,25);
		add(purchase_date_label);

		purchase_date_field.setBounds(250, 300, 200, 25);
		purchase_date_field.setFont(fields_font);
		purchase_date_field.setEditable(false);
		add(purchase_date_field);

		JLabel type_label = new JLabel("Type");
		type_label.setFont(font);
		type_label.setBounds(500,100,200,25);
		add(type_label);

		type_field.setBounds(690, 100, 200, 25);
		type_field.setFont(fields_font);
		type_field.setEditable(false);
		add(type_field);

		JLabel purchase_price_label = new JLabel("Purchase Price");
		purchase_price_label.setFont(font);
		purchase_price_label.setBounds(500,140,220,25);
		add(purchase_price_label);

		purchase_price_field.setBounds(690,140,200,25);
		purchase_price_field.setFont(fields_font);
		purchase_price_field.setEditable(false);
		add(purchase_price_field);

		JLabel sale_label = new JLabel("Sale Price");
		sale_label.setFont(font);
		sale_label.setBounds(500,180,200,25);
		add(sale_label);

		sale_field.setBounds(690,180,200,25);
		sale_field.setToolTipText("Enter medicine sale price");
		sale_field.setFont(fields_font);
		sale_field.setEditable(false);
		add(sale_field);

		JLabel rack_no_label = new JLabel("Rack No");
		rack_no_label.setFont(font);
		rack_no_label.setBounds(500,220,200,25);
		add(rack_no_label);

		rack_no_field.setBounds(690,220,200,25);
		rack_no_field.setEditable(false);
		rack_no_field.setFont(fields_font);
		add(rack_no_field);

		JLabel supplier_name_label = new JLabel("Supplier Name");
		supplier_name_label.setFont(font);
		supplier_name_label.setBounds(500,260,200,25);
		add(supplier_name_label);

		supplier_name_field.setBounds(690,260,200,25);
		supplier_name_field.setFont(fields_font);
		supplier_name_field.setEditable(false);
		add(supplier_name_field);

		JLabel supplier_id_label = new JLabel("Supplier Id");
		supplier_id_label.setFont(font);
		supplier_id_label.setBounds(500,300,200,25);
		add(supplier_id_label);

		supplier_id_field.setBounds(690,300,200,25);
		supplier_id_field.setFont(fields_font);
		supplier_id_field.setEditable(false);
		add(supplier_id_field);

		JPanel buttons_panel = new JPanel();
		buttons_panel.setBounds(0, 330, getWidth(), 40);
		buttons_panel.setBackground(background_color);

		JButton open_button = new JButton("Open", new ImageIcon("images//open.png"));
		open_button.setToolTipText("click to open supplier details");
		open_button.setFont(fields_font);
		open_button.addActionListener(e -> handleOpen());
		buttons_panel.add(open_button);

		JButton delete_button = new JButton("Delete", new ImageIcon("images//delete.png"));
		delete_button.setToolTipText("click to delete medicine details");
		delete_button.setFont(fields_font);
		delete_button.addActionListener(e -> handleDelete());
		buttons_panel.add(delete_button);

		JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
		clear_button.setToolTipText("click to clear all text fields");
		clear_button.setFont(fields_font);
		clear_button.addActionListener(e -> handleClear());
		buttons_panel.add(clear_button);

		JButton all_button = new JButton("All", new ImageIcon("images//all.png"));
		all_button.setToolTipText("click to view all medicine details");
		all_button.setFont(fields_font);
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
		model.addColumn("PURCHASE_DATE");
		model.addColumn("TYPE");
		model.addColumn("SALE_PRICE");
		model.addColumn("PUR_PRICE");
		model.addColumn("RACK_NO");
		model.addColumn("SUPPLIER_ID");
		model.addColumn("SUPPLIER_NAME");

		getContentPane().setBackground(background_color);
		setVisible(true);
 	}

	 private void handleOpen() {
		String batch_no = batch_field.getText();
		String name = name_field.getText();

		 try {
			 if(batch_no.isBlank() && name.isBlank()) {
				 showMessage("Please enter medicine batch no or name!");
			 } else {
				 int record_found = 0;

				 preparedStatement = connection.prepareStatement("select * from medicine where name='"+ name +"' or batch_no='"+ batch_no +"'");
				 resultSet = preparedStatement.executeQuery();
				 while(resultSet.next()) {
					 batch_field.setText(resultSet.getString(1));
					 name_field.setText(resultSet.getString(2));
					 company_field.setText(resultSet.getString(3));
					 quantity_field.setText(resultSet.getString(4));
					 expiry_field.setText(resultSet.getString(5));
					 purchase_date_field.setText(resultSet.getString(6));
					 type_field.setText(resultSet.getString(7));
					 purchase_price_field.setText(resultSet.getString(8));
					 sale_field.setText(resultSet.getString(9));
					 rack_no_field.setText(resultSet.getString(10));
					 supplier_id_field.setText(resultSet.getString(11));
					 supplier_name_field.setText(resultSet.getString(12));
					 record_found = 1;
				 }
				 if (record_found == 0) {
					 showMessage("medicine with that id or name does not exist!");
				 }
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	 private void handleDelete() {
		 String batch_no = batch_field.getText();
		 String name = name_field.getText();

		 if(batch_no.isBlank() && name.isBlank()) {
			 showMessage("Please enter medicine batch no or name!");
		 } else {
			 try {
				 preparedStatement = connection.prepareStatement("delete from medicine where batch_no='"+ batch_no +"' or name='"+ name +"'");
				 preparedStatement.executeUpdate();
				 JOptionPane.showMessageDialog(null,"Record is deleted.");
				 handleClear();
			 } catch(Exception e) {
				 e.printStackTrace();
			 }
		 }
	 }

	 private void handleClear() {
		 batch_field.setText("");
		 name_field.setText("");
		 company_field.setText("");
		 quantity_field.setText("");
		 expiry_field.setText("");
		 purchase_date_field.setText("");
		 type_field.setText("");
		 purchase_price_field.setText("");
		 sale_field.setText("");
		 rack_no_field.setText("");
		 supplier_name_field.setText("");
		 supplier_id_field.setText("");
	 }

	 private void handleAll() {
		 int row = 0;
		 try {
			 Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 resultSet = statement.executeQuery("SELECT * from medicine");
			 while(resultSet.next()) {
				 model.insertRow(row++, new Object[] {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12) });
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message, null, JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
		new DeleteMedicine();
	}
}

