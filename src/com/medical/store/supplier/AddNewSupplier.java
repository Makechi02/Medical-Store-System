package com.medical.store.supplier;

import com.medical.store.connections.Connections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewSupplier extends JFrame {
	private final JTextField name_field = new JTextField();
	private final JTextField address_field = new JTextField();
	private final JTextField phone_field = new JTextField();
	private final JTextField email_field = new JTextField();

	private final DefaultTableModel model = new DefaultTableModel();

	Connections connections = new Connections();

	public AddNewSupplier() {
		super("Add New Supplier");
		setSize(950,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		Font font = new Font("Chilanka", Font.BOLD, 20);
		Font fields_font = new Font("Chilanka", Font.PLAIN, 18);
		Color background_color = Color.CYAN;

		JLabel heading = new JLabel("New Supplier Details");
	    heading.setFont(new Font("Chilanka",Font.BOLD,30));
	    heading.setBounds(0,40,getWidth(),40);
		heading.setHorizontalAlignment(JLabel.CENTER);
		heading.setForeground(Color.blue);
	    add(heading);

		JLabel l2 = new JLabel("Supplier name:");
		l2.setFont(font);
        l2.setBounds(200, 110, 200, 35);
		add(l2);

		name_field.setBounds(420, 110, 300, 35);
		name_field.setToolTipText("Enter supplier name");
		name_field.setFont(fields_font);
		add(name_field);

		JLabel l3 = new JLabel("Supplier address:");
		l3.setFont(font);
        l3.setBounds(200,165,200,35);
		add(l3);

		address_field.setBounds(420, 165, 300, 35);
		address_field.setToolTipText("Enter supplier address");
		address_field.setFont(fields_font);
		add(address_field);

		JLabel l4 = new JLabel("Supplier phone no:");
		l4.setFont(font);
        l4.setBounds(200,220,200,35);
		add(l4);

		phone_field.setBounds(420,220,300,35);
		phone_field.setToolTipText("Enter supplier phone no");
		phone_field.setFont(fields_font);
		add(phone_field);

		JLabel l5 = new JLabel("Supplier email:");
		l5.setFont(font);
        l5.setBounds(200,275,200,35);
		add(l5);

		email_field.setBounds(420,275,300,35);
		email_field.setToolTipText("Enter supplier email");
		email_field.setFont(fields_font);
		add(email_field);

		JPanel buttons_panel = new JPanel();
		buttons_panel.setBounds(0, 330, getWidth(), 50);
		buttons_panel.setBackground(background_color);

		JButton save_button = new JButton("Save", new ImageIcon("images//save.png"));
		save_button.setFont(fields_font);
		save_button.setToolTipText("click to save supplier details");
		save_button.addActionListener(e -> handleSave());
		buttons_panel.add(save_button);

		JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
		clear_button.setFont(fields_font);
		clear_button.setToolTipText("click to clear all text fields");
		clear_button.addActionListener(e -> clearFields());
	    buttons_panel.add(clear_button);

		JButton all_button = new JButton("All", new ImageIcon("images//all.png"));
		all_button.setFont(fields_font);
		all_button.setToolTipText("click to view all supplier details");
		all_button.addActionListener(e -> handleAll());
		buttons_panel.add(all_button);

		add(buttons_panel);

		JTable tabGrid = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tabGrid);
		scrollPane.setBounds(0,380, getWidth(), getHeight() - 420);
        add(scrollPane);
        tabGrid.setFont(new Font ("Chilanka", Font.PLAIN,16));

        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("ADDRESS");
        model.addColumn("PHONE NO.");
        model.addColumn("EMAIL");

		getContentPane().setBackground(background_color);
		setVisible(true);
	}

	private void handleSave() {
		String name = name_field.getText();
		String address = address_field.getText();
		String phone = phone_field.getText();
		String email = email_field.getText();

		Pattern pattern = Pattern.compile("[_a-z_A-Z_0-9]+[0-9]*@[a-zA-Z0-9]+.[a-zA-Z0-9]+");
		Matcher matcher = pattern.matcher(email);
		boolean matchFound = matcher.matches();

		if (name.isBlank() || address.isBlank() || phone.isBlank() || email.isBlank()) {
			showMessage("Please fill out all fields!");
		} else if(!matchFound) {
			showMessage("Invalid email!");
		} else {
			connections.saveSupplier(name, address, phone, email);
			int reply = JOptionPane.showConfirmDialog(this, "Supplier added successfully.Do you want add more supplier?",null, JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				setVisible(false);
				new AddNewSupplier();
			} else if (reply == JOptionPane.NO_OPTION) {
				setVisible(false);
			}
		}
	}

	private void clearFields() {
		name_field.setText("");
		address_field.setText("");
		phone_field.setText("");
		email_field.setText("");
	}

	private void handleAll() {
		int r = 0;
		try {
			Statement statement = Connections.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = statement.executeQuery("SELECT * FROM suppliers GROUP BY id");
			while(resultSet.next()) {
				model.insertRow(r++, new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5) });
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message, null, JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
		new AddNewSupplier();
	}

}
