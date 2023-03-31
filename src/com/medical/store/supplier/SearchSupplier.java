package com.medical.store.supplier;

import com.medical.store.connections.Connections;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;

public class SearchSupplier extends JFrame {
	private final JTextField id_field = new JTextField();
	private final JTextField name_field = new JTextField();
	private final JTextField address_field = new JTextField();
	private final JTextField phone_field = new JTextField();
	private final JTextField email_field = new JTextField();

	private ResultSet resultSet;
	private final DefaultTableModel model = new DefaultTableModel();

	Connection connections = new Connections().getConnection();

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
		id_field.setToolTipText("Enter supplier id to delete supplier");
		id_field.setFont(fields_font);
		add(id_field);

		JLabel l2 = new JLabel("Supplier name:");
		l2.setBounds(200,160,200,30);
		l2.setFont(font);
		add(l2);

		name_field.setBounds(420,160,300,30);
		name_field.setToolTipText("Enter supplier name to delete supplier");
		name_field.setFont(fields_font);
		add(name_field);

		JLabel l3 = new JLabel("Supplier address:");
		l3.setBounds(200,210,200,30);
		l3.setFont(font);
		add(l3);

		address_field.setBounds(420,210,300,30);
		address_field.setFont(fields_font);
		add(address_field);

		JLabel l4 = new JLabel("Supplier phone no:");
		l4.setBounds(200,260,200,30);
		l4.setFont(font);
		add(l4);

		phone_field.setBounds(420,260,300,30);
		phone_field.setFont(fields_font);
		add(phone_field);

		JLabel l5 = new JLabel("Supplier email:");
		l5.setBounds(200,310,200,30);
		l5.setFont(font);
		add(l5);

		email_field.setBounds(420,310,300,30);
		email_field.setFont(fields_font);
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
		setVisible(true);
	}

	private void handleSearch() {
		String id = id_field.getText();
		String name = name_field.getText();

		try {
			if(id.isBlank() && name.isBlank()) {
				showMessage("Please enter supplier id or name!");
			} else {
				int records_found = 0;
				String query = "select * from suppliers where id = '" + id + "' or name = '" + name + "'";
				PreparedStatement preparedStatement = connections.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					id_field.setText(resultSet.getString(1));
					name_field.setText(resultSet.getString(2));
					address_field.setText(resultSet.getString(3));
					phone_field.setText(resultSet.getString(4));
					email_field.setText(resultSet.getString(5));
					records_found = 1;
				}
				if (records_found == 0) {
					showMessage("Supplier with id " + id + " does not exist");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
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
		int r = 0;
		try {
			Statement statement = connections.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery("SELECT * FROM suppliers" );
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
	    new SearchSupplier();
	}
}
