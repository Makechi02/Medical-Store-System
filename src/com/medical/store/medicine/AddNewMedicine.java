package com.medical.store.medicine;

import com.medical.store.connections.Connections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddNewMedicine extends JFrame {

	private final JTextField batch_field = new JTextField();
	private final JTextField name_field = new JTextField();
	private final JTextField company_field = new JTextField();
	private final JTextField medicine_field = new JTextField();
	private final JTextField expiry_field = new JTextField();
	private final JTextField purchase_date_field;
	private final JTextField purchase_price_field;
	private final JTextField sale_field;
	private final JTextField rack_no_field;
	private final JComboBox<String> supplier_names_box = new JComboBox<>();
	private final JComboBox<String> type_box = new JComboBox<>();
	private String supplier, supplier_id, type;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private final DefaultTableModel model = new DefaultTableModel();

	private final Connection connection = new Connections().getConnection();

	public AddNewMedicine() {
		super("Add New Medicine ");
		setSize(900,700);
		setResizable(false);
		getContentPane().setBackground(Color.cyan);
		setLocationRelativeTo(null);

		Font font = new Font("Times New Roman", Font.BOLD, 15);
		setLayout(null);

		JLabel details_label = new JLabel("New Medicine details");
		details_label.setFont(new Font("Times New Roman",Font.BOLD,25));
		details_label.setForeground(Color.blue);
		details_label.setBounds(300,30,400,40);
		add(details_label);

		JLabel batch_label = new JLabel("Medicine Batch no*");
		batch_label.setFont(font);
		batch_label.setBounds(50,100,200,25);
		add(batch_label);

		batch_field.setBounds(250,100,100,25);
		batch_field.setToolTipText("Enter medicine batch no");
		add(batch_field);

		JLabel name_label = new JLabel("Medicine name*");
		name_label.setFont(font);
		name_label.setBounds(50,140,200,25);
		add(name_label);

		name_field.setBounds(250,140,200,25);
		name_field.setToolTipText("Enter medicine name");
		add(name_field);

		JLabel company_label = new JLabel("Medicine company*");
		company_label.setFont(font);
		company_label.setBounds(50,180,200,25);
		add(company_label);

		company_field.setBounds(250,180,200,25);
		company_field.setToolTipText("Enter medicine company");
		add(company_field);

		JLabel quantity_label = new JLabel("Medicine quantity*");
		quantity_label.setFont(font);
		quantity_label.setBounds(50,220,200,25);
		add(quantity_label);

		medicine_field.setBounds(250,220,100,25);
		medicine_field.setToolTipText("Enter medicine quantity");
		add(medicine_field);

		JLabel expiry_label = new JLabel("Med expiry date*");
		expiry_label.setFont(font);
		expiry_label.setBounds(50,260,250,25);
		add(expiry_label);

		expiry_field.setBounds(250,260,100,25);
		expiry_field.setToolTipText("Enter medicine expiry date");
		add(expiry_field);

		JLabel purchase_date_label = new JLabel("Med purchase date*");
		purchase_date_label.setFont(font);
		purchase_date_label.setBounds(50,300,260,25);
		add(purchase_date_label);

		purchase_date_field = new JTextField();
		purchase_date_field.setBounds(250,300,100,25);
		purchase_date_field.setToolTipText("Enter medicine purchase date");
		add(purchase_date_field);

		Date date1 = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		String strDate = calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR);
		purchase_date_field.setText(strDate);

		JLabel type_label = new JLabel("Medicine type*");
		type_label.setFont(font);
		type_label.setBounds(470,100,200,25);
		add(type_label);

		type_box.addItem("--type--");
		type_box.addItem("Tablet");
		type_box.addItem("Capsule");
		type_box.addItem("Syrup");
		type_box.addItem("Insulin");
		type_box.addItem("Cream");
		type_box.addItem("Balm");
		type_box.addItem("Drop");
		type_box.addItem("Granules");
		type_box.addItem("Oil");
		type_box.addItem("Powder");
		type_box.setBounds(720,100,100,25);
		type_box.setToolTipText("Select medicine type");
		type_box.addActionListener(ae -> type = (String) type_box.getSelectedItem());
		add(type_box);

		JLabel purchase_price_label = new JLabel("Medicine purchase price*");
		purchase_price_label.setFont(font);
		purchase_price_label.setBounds(470,140,220,25);
		add(purchase_price_label);

		purchase_price_field = new JTextField(20);
		purchase_price_field.setBounds(720,140,100,25);
		purchase_price_field.setToolTipText("Enter medicine purchase price");
		add(purchase_price_field);

		JLabel sale_label = new JLabel("Medicine sale price*");
		sale_label.setFont(font);
		sale_label.setBounds(470,180,200,25);
		add(sale_label);

		sale_field = new JTextField(20);
		sale_field.setBounds(720,180,100,25);
		sale_field.setToolTipText("Enter medicine sale price");
		add(sale_field);

		JLabel rack_no_label = new JLabel("Medicine rack no*");
		rack_no_label.setFont(font);
		rack_no_label.setBounds(470,220,200,25);
		add(rack_no_label);

		rack_no_field = new JTextField(20);
		rack_no_field.setBounds(720,220,100,25);
		rack_no_field.setToolTipText("Enter medicine rack no");
		add(rack_no_field);

		JLabel supplier_name_label = new JLabel("Supplier name*");
		supplier_name_label.setFont(font);
		supplier_name_label.setBounds(470,260,250,25);
		add(supplier_name_label);

		supplier_names_box.setBounds(720,260,130,25);
		supplier_names_box.setToolTipText("select medicine supplier name");
		supplier_names_box.addActionListener(ae -> supplier = (String) supplier_names_box.getSelectedItem());
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

		JButton save_button = new JButton("Save", new ImageIcon("images//save.png"));
		save_button.setBounds(150,330,110,35);
		save_button.setToolTipText("click to save medicine details");
		save_button.addActionListener(e -> handleSave());
		add(save_button);

		JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
		clear_button.setBounds(300,330,110,35);
		clear_button.setToolTipText("click to clear all text fields");
		clear_button.addActionListener(e -> handleClear());
		add(clear_button);

		JButton all_button = new JButton("All", new ImageIcon("images//all.png"));
		all_button.setBounds(450,330,110,35);
		all_button.setToolTipText("click to view all medicine details");
		all_button.addActionListener(e -> handleAll());
		add(all_button);

		JTable tabGrid = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tabGrid);
		scrollPane.setBounds(0,380,900,600);
		add(scrollPane);
		tabGrid.setFont(new Font ("Times New Roman", Font.PLAIN,15));

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

		setVisible(true);
	}

	private void handleSave() {
		try {
			if(((batch_field.getText()).equals(""))||((name_field.getText()).equals(""))||((company_field.getText()).equals(""))||((medicine_field.getText()).equals(""))||((expiry_field.getText()).equals(""))||((purchase_date_field.getText()).equals(""))|| ((purchase_price_field.getText()).equals(""))||((sale_field.getText()).equals(""))||((rack_no_field.getText()).equals(""))) {
				JOptionPane.showMessageDialog(this,"* Details are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
			} else {
				float a = Float.parseFloat(purchase_price_field.getText());
				float b = Float.parseFloat(sale_field.getText());
				if(b < a) {
					JOptionPane.showMessageDialog(this,"sale price should be greater than purchase price!","Warning!!!",JOptionPane.WARNING_MESSAGE);
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
					preparedStatement.setString(1, batch_field.getText());
					preparedStatement.setString(2, name_field.getText());
					preparedStatement.setString(3, company_field.getText());
					preparedStatement.setInt(4, Integer.parseInt(medicine_field.getText()));
					preparedStatement.setString(5, expiry_field.getText());
					preparedStatement.setString(6, purchase_date_field.getText());
					preparedStatement.setString(7, type);
					preparedStatement.setFloat(8, Float.parseFloat(purchase_price_field.getText()));
					preparedStatement.setFloat(9, Float.parseFloat(sale_field.getText()));
					preparedStatement.setString(10, rack_no_field.getText());
					preparedStatement.setInt(11, Integer.parseInt(supplier_id));
					preparedStatement.setString(12, supplier);
					preparedStatement.executeUpdate();

					int reply = JOptionPane.showConfirmDialog(null,"Medicine added successfully.Do you want add more Medicines?","Added Medicine",JOptionPane.YES_NO_OPTION);

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
		medicine_field.setText("");
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

	public static void main(String[] args) {
		new AddNewMedicine();
	}
}
