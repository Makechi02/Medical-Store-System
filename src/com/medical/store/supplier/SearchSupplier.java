package com.medical.store.supplier;

import com.medical.store.supplier.connection.SupplierService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchSupplier extends JFrame {
	private final JTextField id_field = new JTextField();
	private final JTextField name_field = new JTextField();
	private final JTextField address_field = new JTextField();
	private final JTextField phone_field = new JTextField();
	private final JTextField email_field = new JTextField();

	private final DefaultTableModel model = new DefaultTableModel();

	private final SupplierService supplierService;

	public SearchSupplier() {
		super("Search Supplier");
		setSize(950,720);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		Font font = new Font("Chilanka", Font.BOLD, 20);
		Font fields_font = new Font("Chilanka", Font.PLAIN, 18);
		Color background_color = Color.CYAN;

		JLabel heading = new JLabel("Search Supplier");
		heading.setFont(new Font("Chilanka",Font.BOLD,30));
		heading.setBounds(0,40,getWidth(),40);
		heading.setHorizontalAlignment(JLabel.CENTER);
		heading.setForeground(Color.blue);
		add(heading);

		JLabel l1 = new JLabel("Supplier id:");
		l1.setBounds(200,110,200,30);
		l1.setFont(font);
		add(l1);

		id_field.setBounds(420,110,300,30);
		id_field.setToolTipText("Enter supplier id");
		id_field.setFont(fields_font);
		add(id_field);

		JLabel l2 = new JLabel("Supplier name:");
		l2.setBounds(200,160,200,30);
		l2.setFont(font);
		add(l2);

		name_field.setBounds(420,160,300,30);
		name_field.setFont(fields_font);
		name_field.setEditable(false);
		add(name_field);

		JLabel l3 = new JLabel("Supplier address:");
		l3.setBounds(200,210,200,30);
		l3.setFont(font);
		add(l3);

		address_field.setBounds(420,210,300,30);
		address_field.setFont(fields_font);
		address_field.setEditable(false);
		add(address_field);

		JLabel l4 = new JLabel("Supplier phone no:");
		l4.setBounds(200,260,200,30);
		l4.setFont(font);
		add(l4);

		phone_field.setBounds(420,260,300,30);
		phone_field.setFont(fields_font);
		phone_field.setEditable(false);
		add(phone_field);

		JLabel l5 = new JLabel("Supplier email:");
		l5.setBounds(200,310,200,30);
		l5.setFont(font);
		add(l5);

		email_field.setBounds(420,310,300,30);
		email_field.setFont(fields_font);
		email_field.setEditable(false);
		add(email_field);

		JPanel buttons_panel = new JPanel();
		buttons_panel.setBounds(0, 360, getWidth(), 40);
		buttons_panel.setBackground(background_color);

		JButton search_button = new JButton("Search", new ImageIcon("images//search.png"));
		search_button.setBounds(150,330,110,35);
		search_button.setFont(fields_font);
		search_button.setToolTipText("click to open supplier details");
		search_button.addActionListener(e -> handleSearch());
		buttons_panel.add(search_button);

		JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
		clear_button.setToolTipText("click to clear all text fields");
		clear_button.setFont(fields_font);
		clear_button.addActionListener(e -> handleClear());
		buttons_panel.add(clear_button);

		JButton all_button = new JButton("All", new ImageIcon("images//all.png"));
		all_button.setToolTipText("click to view all supplier details");
		all_button.setFont(fields_font);
		all_button.addActionListener(e -> handleAll());
		buttons_panel.add(all_button);

		add(buttons_panel);

		JTable tabGrid = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tabGrid);
		scrollPane.setBounds(0, 405, getWidth(), getHeight() - 440);
		add(scrollPane);
		tabGrid.setFont(new Font ("Chilanka", Font.PLAIN,16));

		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("ADDRESS");
		model.addColumn("PHONE");
		model.addColumn("EMAIL");

		getContentPane().setBackground(background_color);
		supplierService = new SupplierService();
		setVisible(true);
	}

	private void handleSearch() {
		String id = id_field.getText();
		if (id.isBlank()) {
			showMessage("Please enter supplier id!");
		} else {
			Supplier supplier = supplierService.getSupplier(Integer.parseInt(id));
			id_field.setText(String.valueOf(supplier.getId()));
			name_field.setText(supplier.getName());
			address_field.setText(supplier.getAddress());
			phone_field.setText(supplier.getPhone());
			email_field.setText(supplier.getEmail());
		}
	}

	private void handleClear() {
		id_field.setText("");
		name_field.setText("");
		address_field.setText("");
		phone_field.setText("");
		email_field.setText("");
	}

	private void handleAll() {
		int row = 0;
		List<Supplier> suppliers = supplierService.getSuppliers();
		for (Supplier supplier: suppliers) {
			model.insertRow(row++, new Object[] {supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getPhone(), supplier.getEmail()});
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message, null, JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
	    new SearchSupplier();
	}
}
