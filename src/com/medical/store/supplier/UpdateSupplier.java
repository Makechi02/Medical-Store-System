package com.medical.store.supplier;

import com.medical.store.connections.Connections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateSupplier extends JFrame {
	private final JTextField id_field = new JTextField();
	private final JTextField name_field = new JTextField();
	private final JTextField address_field = new JTextField();
	private final JTextField phone_field = new JTextField();
	private final JTextField email_field = new JTextField();

    private final DefaultTableModel model = new DefaultTableModel();

	private final Connection connection = new Connections().getConnection();
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public UpdateSupplier() {
		super("Update Supplier");
		setSize(950,720);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		Font font = new Font("Chilanka", Font.BOLD, 20);
		Font fields_font = new Font("Chilanka", Font.PLAIN, 18);
		Color background_color = Color.CYAN;

		JLabel heading = new JLabel("Delete Supplier");
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

		JButton open_button = new JButton("Open", new ImageIcon("images//open.png"));
		open_button.setToolTipText("click to open supplier details");
		open_button.setFont(fields_font);
		open_button.addActionListener(e -> handleOpen());
		buttons_panel.add(open_button);

		JButton update_button = new JButton("Update", new ImageIcon("images//update.png"));
		update_button.setFont(fields_font);
		update_button.setToolTipText("click to update supplier details");
		update_button.addActionListener(e -> handleUpdate());
		buttons_panel.add(update_button);

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
		this.add(scrollPane);
		tabGrid.setFont(new Font ("Chilanka", Font.PLAIN,16));

        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("ADDRESS");
        model.addColumn("PHONE");
        model.addColumn("EMAIL");

		getContentPane().setBackground(background_color);
		setVisible(true);
	}

	private void handleOpen() {
		String id = id_field.getText();
		String name = name_field.getText();

		if (name.isBlank() && id.isBlank()) {
			showMessage("Please enter supplier id or name!");
		} else {
			try {
				int records_found = 0;
				String query = "SELECT * FROM suppliers WHERE id = '"+ id +"' OR name = '"+ name +"'";
				preparedStatement = connection.prepareStatement(query);
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
			} catch(Exception se) {
				se.printStackTrace();
			}
		}
	}

	private void handleUpdate() {
		String id = id_field.getText();
		String name = name_field.getText();
		String address = address_field.getText();
		String phone = phone_field.getText();
		String email = email_field.getText();

		Pattern pattern = Pattern.compile("[_a-z_A-Z_0-9]*[0-9]*@[a-zA-Z0-9]*.[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(email);
		boolean matchFound = matcher.matches();

		if(id.isBlank() && name.isBlank()) {
			JOptionPane.showMessageDialog(this,"Please enter supplier id or name!","Warning!!!",JOptionPane.ERROR_MESSAGE);
		} else if(name.isBlank() || address.isBlank() || phone.isBlank() || email.isBlank()) {
			JOptionPane.showMessageDialog(this,"* Detail are Missing !","Warning!!!",JOptionPane.ERROR_MESSAGE);
		} else if(!matchFound) {
			JOptionPane.showMessageDialog(this,"Invalid email id!","Warning!!!",JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				String query = "UPDATE suppliers SET id='" + id + "', name='" + name + "', address='" + address + "', phone='" + phone + "',email='" + email + "' WHERE id='" + id +"' OR name='" + name +"'";
				new Connections().getStatement().executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Record is updated");
				handleClear();
			} catch(Exception se) {
				se.printStackTrace();
			}
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
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = statement.executeQuery("SELECT * from suppliers");
			while(resultSet.next()) {
				model.insertRow(r++, new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5) });
			}
		} catch(Exception se) {
			se.printStackTrace();
		}
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message, null, JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String[] args) {
	    new UpdateSupplier();
	}
}
