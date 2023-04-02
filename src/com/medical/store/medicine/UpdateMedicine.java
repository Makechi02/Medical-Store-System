package com.medical.store.medicine;

import com.medical.store.connections.Connections;
import com.medical.store.constants.MedicineType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateMedicine extends JFrame {
	private final JTextField batch_field = new JTextField();
	private final JTextField name_field = new JTextField();
	private final JTextField company_field = new JTextField();
	private final JTextField quantity_field = new JTextField();
	private final JTextField expiry_field = new JTextField();
	private final JTextField purchase_date_field = new JTextField();
	private final JTextField purchase_price_field = new JTextField();
	private final JTextField sale_field = new JTextField();
	private final JTextField rack_no_field = new JTextField();
	private final JTextField supplier_id_field = new JTextField();

	private final JComboBox<String> supplier_name_box;
	private final JComboBox<MedicineType> type_box;
    private String supplier_name;
	private String type;
	private final Connection connection = new Connections().getConnection();
	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet;
	private final DefaultTableModel model = new DefaultTableModel();
	private final List<String> supplier_names = new ArrayList<>();

	public UpdateMedicine() {
		super("Update Medicine");
		setSize(950, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		Font font = new Font("Chilanka", Font.BOLD, 20);
		Font fields_font = new Font("Chilanka", Font.PLAIN, 18);
		Color background_color = Color.CYAN;

		JLabel heading = new JLabel("Update Medicine");
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
		company_field.setFont(fields_font);
		add(company_field);

		JLabel quantity_label = new JLabel("Quantity");
		quantity_label.setFont(font);
		quantity_label.setBounds(50,220,200,25);
		add(quantity_label);

		quantity_field.setBounds(250, 220, 200, 25);
		quantity_field.setFont(fields_font);
		add(quantity_field);

		JLabel expiry_label = new JLabel("Expiry Date");
		expiry_label.setFont(font);
		expiry_label.setBounds(50,260,200,25);
		add(expiry_label);

		expiry_field.setBounds(250, 260, 200, 25);
		expiry_field.setFont(fields_font);
		add(expiry_field);

		JLabel purchase_date_label = new JLabel("Purchase Date");
		purchase_date_label.setFont(font);
		purchase_date_label.setBounds(50,300,200,25);
		add(purchase_date_label);

		purchase_date_field.setBounds(250, 300, 200, 25);
		purchase_date_field.setFont(fields_font);
		add(purchase_date_field);

		JLabel type_label = new JLabel("Type");
		type_label.setFont(font);
		type_label.setBounds(500,100,200,25);
		add(type_label);
		
		type_box = new JComboBox<>(MedicineType.values());
		type_box.setBounds(690, 100, 200, 25);
		type_box.setToolTipText("Select medicine type");
		type_box.setFont(fields_font);
		type_box.addActionListener(e -> type = Objects.requireNonNull(type_box.getSelectedItem()).toString());
		add(type_box);

		JLabel purchase_price_label = new JLabel("Purchase Price");
		purchase_price_label.setFont(font);
		purchase_price_label.setBounds(500,140,220,25);
		add(purchase_price_label);

		purchase_price_field.setBounds(690,140,200,25);
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
		rack_no_field.setFont(fields_font);
		add(rack_no_field);

		JLabel supplier_name_label = new JLabel("Supplier Name");
		supplier_name_label.setFont(font);
		supplier_name_label.setBounds(500,260,200,25);
		add(supplier_name_label);

		JLabel supplier_id_label = new JLabel("Supplier Id");
		supplier_id_label.setFont(font);
		supplier_id_label.setBounds(500,300,200,25);
		add(supplier_id_label);

		supplier_id_field.setBounds(690,300,200,25);
		supplier_id_field.setFont(fields_font);
		supplier_id_field.setEditable(false);
		add(supplier_id_field);
		
		supplier_name_box = new JComboBox<>();
		supplier_name_box.setBounds(690,260,200,25);
		supplier_name_box.setToolTipText("select medicine supplier name");
		supplier_name_box.setFont(fields_font);
		supplier_name_box.addActionListener(e -> supplier_name = Objects.requireNonNull(supplier_name_box.getSelectedItem()).toString());
		this.add(supplier_name_box);

		try {
			preparedStatement = connection.prepareStatement("select name from suppliers");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String supplier_name = resultSet.getString("name");
				supplier_name_box.addItem(supplier_name);
				supplier_names.add(supplier_name);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		JPanel buttons_panel = new JPanel();
		buttons_panel.setBounds(0, 330, getWidth(), 40);
		buttons_panel.setBackground(background_color);

		JButton open_button = new JButton("Open", new ImageIcon("images//open.png"));
		open_button.setToolTipText("click to open medicine details");
		open_button.setFont(fields_font);
		open_button.addActionListener(e -> handleOpen());
		buttons_panel.add(open_button);

		JButton update_button = new JButton("Update", new ImageIcon("images//update.png"));
		update_button.setFont(fields_font);
		update_button.setToolTipText("click to update medicine details");
		update_button.addActionListener(e -> handleUpdate());
		buttons_panel.add(update_button);

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
		model.addColumn("PUR_DATE");
		model.addColumn("TYPE");
		model.addColumn("SALE_PRICE");
		model.addColumn("PUR_PRICE");
		model.addColumn("RACK_NO");
		model.addColumn("SUPPLIER_ID");
		model.addColumn("SUPPLIER_NAME");

		getContentPane().setBackground(background_color);
		this.setVisible(true);
 	}

	 private void handleOpen() {
		String batch = batch_field.getText();
		String name = name_field.getText();

		 if(batch.isBlank() && name.isBlank()) {
			 JOptionPane.showMessageDialog(this,"Please enter medicine batch no or name!","Warning!!!",JOptionPane.WARNING_MESSAGE);
		 }
		 else {
			 try {
				 int record_found = 0;
				 preparedStatement = connection.prepareStatement("Select id from suppliers where name='" + supplier_name + "'");
				 resultSet = preparedStatement.executeQuery();
				 while(resultSet.next()) {
					 String sid1 = resultSet.getString(1);
					 supplier_id_field.setText(sid1);
				 }

				 preparedStatement = connection.prepareStatement("select * from medicine where name='"+ name +"' or batch_no='"+ batch +"' ");
				 resultSet = preparedStatement.executeQuery();
				 while(resultSet.next()) {
					 batch_field.setText(resultSet.getString("batch_no"));
					 name_field.setText(resultSet.getString("name"));
					 company_field.setText(resultSet.getString("company"));
					 quantity_field.setText(resultSet.getString("quantity"));
					 expiry_field.setText(resultSet.getString(5));
					 purchase_date_field.setText(resultSet.getString(6));
					 type_box.setSelectedIndex(getTypeIndex(resultSet.getString("type")));
					 purchase_price_field.setText(resultSet.getString(8));
					 sale_field.setText(resultSet.getString(9));
					 rack_no_field.setText(resultSet.getString(10));
					 supplier_id_field.setText(resultSet.getString(11));
					 supplier_name_box.setSelectedIndex(getSupplierIndex(resultSet.getString("supplier_name")));
					 record_found = 1;
				 }
				 if (record_found == 0) {
					 JOptionPane.showMessageDialog(null,"Record is not available","Dialogs",JOptionPane.WARNING_MESSAGE);
				 }
			 } catch(Exception e) {
				 e.printStackTrace();
			 }
		 }
	 }

	 private void handleUpdate() {
		 String batch = batch_field.getText();
		 String name = name_field.getText();
		 String company = company_field.getText();
		 String quantity = quantity_field.getText();
		 String expiry = expiry_field.getText();
		 String purchase_date = purchase_date_field.getText();
		 String purchase_price = purchase_price_field.getText();
		 String sale_price = sale_field.getText();
		 String rack_no = rack_no_field.getText();
		 String supplier_id = supplier_id_field.getText();

		 try {
			 if (
					 batch.isBlank()
					 || name.isBlank()
					 || company.isBlank()
					 || quantity.isBlank()
					 || expiry.isBlank()
					 || purchase_date.isBlank()
					 || purchase_price.isBlank()
					 || sale_price.isBlank()
					 || rack_no.isBlank()
			 ) {
				 JOptionPane.showMessageDialog(this,"Please fill out all fields!","Warning!!!",JOptionPane.WARNING_MESSAGE);
			 } else {
				 statement = connection.createStatement();
				 String str1 = "UPDATE medicine SET batch_no='"+ batch +"',name='"+ name +"',company='"+ company +"',quantity='"+ quantity +"',expiry_date='"+ expiry +"',purchase_date='"+ purchase_date +"',type='"+ type +"',purchase_price='"+ purchase_price +"',sale_price='"+ sale_price +"',rack_no='"+ rack_no +"',supplier_id='"+ supplier_id +"',supplier_name='"+ supplier_name +"' where batch_no='"+ batch +"'or name='"+ name +"'";
				 statement.executeUpdate(str1);
				 JOptionPane.showMessageDialog(null, "Record is updated");
				 handleClear();
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
		 supplier_id_field.setText("");
	 }

	 private void handleAll() {
		 int r = 0;
		 try {
			 statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 resultSet = statement.executeQuery("SELECT * from medicine" );
			 while(resultSet.next()) {
				 model.insertRow(r++, new Object[] {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12) });
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	private int getTypeIndex(String type) {
		MedicineType[] medicineTypes = MedicineType.values();
		for (int i = 0; i < medicineTypes.length; i++) {
			if (Objects.equals(medicineTypes[i].toString(), type)) return i;
		}
		return -1;
	}

	private int getSupplierIndex(String name) {
		for (int i = 0; i < supplier_names.size(); i++) {
			if (Objects.equals(supplier_names.get(i), name)) return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		new UpdateMedicine();
	}
}
