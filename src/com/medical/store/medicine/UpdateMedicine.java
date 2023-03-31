package com.medical.store.medicine;

import com.medical.store.connections.Connections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateMedicine extends JFrame {
	private final JTextField t1;
	private final JTextField t2;
	private final JTextField t3;
	private final JTextField t4;
	private final JTextField t5;
	private final JTextField t6;
	private final JTextField t7;
	private final JTextField t8;
	private final JTextField t9;
	private final JTextField t10;
	private final JTextField t11;
	private final JTextField t12;
	private final JComboBox<String> msname;
	private final JComboBox<String> tabtype;
    private String s;
	private String tabt;
	private final Connection connection = new Connections().getConnection();
	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet;
	private final DefaultTableModel model = new DefaultTableModel();

	public UpdateMedicine() {
		super("Update Medicine");
		setSize(900,700);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.cyan);
		
		Font f = new Font("Times New Roman", Font.BOLD, 20);
		this.setLayout(null);
		
		JLabel ln = new JLabel("Update Medicine");
		ln.setFont(new Font("Times New Roman",Font.BOLD,25));
		ln.setForeground(Color.blue);
		ln.setBounds(300,30,300,40);
		this.add(ln);
		
		JLabel l1 = new JLabel("Medicine Batch no*");
		l1.setFont(f);
		l1.setBounds(50,100,200,25);
		this.add(l1);
		
		t1 = new JTextField(20);
		t1.setBounds(250,100,100,25);t1.setToolTipText("Enter medicine batch no");
		this.add(t1);
		
		JLabel l2 = new JLabel("Medicine name*");
		l2.setFont(f);
		l2.setBounds(50,140,200,25);
		this.add(l2);
		
		t2 = new JTextField(20);
		t2.setBounds(250,140,200,25);
		t2.setToolTipText("Enter medicine name");
		this.add(t2);
		
		JLabel l3 = new JLabel("Medicine company*");
		l3.setFont(f);
		l3.setBounds(50,180,200,25);
		this.add(l3);
		
		t3 = new JTextField(20);
		t3.setBounds(250,180,200,25);
		t3.setToolTipText("Enter medicine company");
		this.add(t3);
		
		JLabel l4 = new JLabel("Medicine quantity*");
		l4.setFont(f);
		l4.setBounds(50,220,200,25);
		this.add(l4);
		
		t4= new JTextField(20);
		t4.setBounds(250,220,100,25);
		t4.setToolTipText("Enter medicine quantity");
		this.add(t4);
		
		JLabel l5 = new JLabel("Med expiry date*");
		l5.setFont(f);
		l5.setBounds(50,260,250,25);
		this.add(l5);
		
		t5= new JTextField(20);
		t5.setBounds(250,260,100,25);
		t5.setToolTipText("Enter medicine expiry date");
		this.add(t5);
		
		JLabel l6 = new JLabel("Med purchase date*");
		l6.setFont(f);
		l6.setBounds(50,300,260,25);
		this.add(l6);
		
		t6= new JTextField(20);
		t6.setBounds(250,300,100,25);
		t6.setToolTipText("Enter medicine expiry date");
		this.add(t6);
		
		JLabel l7 = new JLabel("Medicine type*");
		l7.setFont(f);
		l7.setBounds(470,100,200,25);
		this.add(l7);
		
		t7 = new JTextField(20);
		t7.setBounds(720,100,100,25);
		t7.setToolTipText("Enter medicine type");
		this.add(t7);
		
		tabtype=new JComboBox<>();
		tabtype.addItem("--type--");
		tabtype.addItem("Tablet");
		tabtype.addItem("Capsule");
		tabtype.addItem("Syrup");
		tabtype.addItem("Insulin");
		tabtype.addItem("Cream");
		tabtype.addItem("Balm");
		tabtype.addItem("Drop");
		tabtype.addItem("Granule");
		tabtype.addItem("Oil");
		tabtype.addItem("Powder");
		tabtype.setBounds(600,100,100,25);
		tabtype.setToolTipText("Select medicine type");
		this.add(tabtype);
		tabtype.addActionListener(ae -> {
			tabt = (String)tabtype.getSelectedItem();
			t7.setText(tabt);
		});
		
		JLabel l8 = new JLabel("Medicine purchase price*");
		l8.setFont(f);
		l8.setBounds(470,140,220,25);
		this.add(l8);
		
		t8 = new JTextField(20);
		t8.setBounds(720,140,100,25);
		t8.setToolTipText("Enter medicine purchase price");
		this.add(t8);
		
		JLabel l9 = new JLabel("Medicine sale price*");
		l9.setFont(f);
		l9.setBounds(470,180,200,25);
		this.add(l9);
		
		t9 = new JTextField(20);
		t9.setBounds(720,180,100,25);
		t9.setToolTipText("Enter medicine sale price");
		this.add(t9);
		
		JLabel l10 = new JLabel("Medicine rack no*");
		l10.setFont(f);
		l10.setBounds(470,220,200,25);
		this.add(l10);
		
		t10 = new JTextField(20);
		t10.setBounds(720,220,100,25);
		t10.setToolTipText("Enter medicine rack no");
		this.add(t10);
		
		JLabel l11 = new JLabel("Supplier name*");
		l11.setFont(f);
		l11.setBounds(470,260,180,25);
		this.add(l11);
		
		t11 = new JTextField(20);
		t11.setBounds(720,260,100,25);
		this.add(t11);
		
		JLabel l12 = new JLabel("Supplier id");
		l12.setFont(f);
		l12.setBounds(470,300,180,25);
		this.add(l12);
		
		t12 = new JTextField(20);
		t12.setBounds(720,300,100,25);
		this.add(t12);
		
		msname=new JComboBox<>();
		msname.setBounds(600,260,110,25);
		msname.setToolTipText("select medicine supplier name");
		this.add(msname);
		msname.addActionListener(ae -> {
			s = (String) msname.getSelectedItem();
			t11.setText(s);
		});
		
		try {
			preparedStatement = connection.prepareStatement("select id, name from suppliers");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String sname1= resultSet.getString(2);
				msname.addItem(sname1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		JButton open_button = new JButton("Open", new ImageIcon("images//open.png"));
		open_button.setBounds(150,330,110,35);
		open_button.setToolTipText("click to open medicine details");
		open_button.addActionListener(e -> handleOpen());
		this.add(open_button);

		JButton update_button = new JButton("Update", new ImageIcon("images//update.png"));
		update_button.setBounds(300,330,110,35);
		update_button.setToolTipText("click to update medicine details");
		update_button.addActionListener(e -> handleUpdate());
		this.add(update_button);

		JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
		clear_button.setBounds(450,330,110,35);
		clear_button.setToolTipText("click to clear all text fields");
		clear_button.addActionListener(e -> handleClear());
		this.add(clear_button);

		JButton all_button = new JButton("All", new ImageIcon("images//all.png"));
		all_button.setBounds(600,330,110,35);
		all_button.setToolTipText("click to view all medicine details");
		all_button.addActionListener(e -> handleAll());
		this.add(all_button);

		JTable tabGrid = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tabGrid);
		scrollPane.setBounds(0,380,900,600);
		this.add(scrollPane);
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
		
		this.setVisible(true);
 	}

	 private void handleOpen() {
		 if(((t1.getText()).equals(""))&&((t2.getText()).equals(""))) {
			 JOptionPane.showMessageDialog(this,"Please enter medicine batch no or name!","Warning!!!",JOptionPane.WARNING_MESSAGE);
		 }
		 else {
			 try {
				 int record_found = 0;
				 preparedStatement = connection.prepareStatement("Select id from suppliers where name='"+s+"'");
				 resultSet = preparedStatement.executeQuery();
				 while(resultSet.next()) {
					 String sid1 = resultSet.getString(1);
					 t12.setText(sid1);
				 }

				 preparedStatement = connection.prepareStatement("select * from medicine where name='"+t2.getText()+"' or batch_no='"+t1.getText()+"' ");
				 resultSet = preparedStatement.executeQuery();
				 while(resultSet.next()) {
					 t1.setText(resultSet.getString(1));
					 t2.setText(resultSet.getString(2));
					 t3.setText(resultSet.getString(3));
					 t4.setText(resultSet.getString(4));
					 t5.setText(resultSet.getString(5));
					 t6.setText(resultSet.getString(6));
					 t7.setText(resultSet.getString(7));
					 t8.setText(resultSet.getString(8));
					 t9.setText(resultSet.getString(9));
					 t10.setText(resultSet.getString(10));
					 t12.setText(resultSet.getString(11));
					 t11.setText(resultSet.getString(12));
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
		 try {
			 if(((t1.getText()).equals(""))||((t2.getText()).equals(""))||((t3.getText()).equals(""))||((t4.getText()).equals(""))||((t5.getText()).equals(""))||((t6.getText()).equals(""))||((t7.getText()).equals(""))||((t8.getText()).equals(""))||((t9.getText()).equals(""))||((t10.getText()).equals(""))||((t11.getText()).equals(""))) {
				 JOptionPane.showMessageDialog(this,"* Detail are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
			 } else {
				 statement = connection.createStatement();
				 String str1="UPDATE medicine SET batch_no='"+t1.getText()+"',name='"+t2.getText()+"',company='"+t3.getText()+"',quantity='"+t4.getText()+"',expiry_date='"+t5.getText()+"',purchase_date='"+t6.getText()+"',type='"+t7.getText()+"',purchase_price='"+t8.getText()+"',sale_price='"+t9.getText()+"',rack_no='"+t10.getText()+"',supplier_id='"+t12.getText()+"',supplier_name='"+t11.getText()+"' where batch_no='"+t1.getText()+"'or name='"+t2.getText()+"'";
				 statement.executeUpdate(str1);
				 JOptionPane.showMessageDialog(null, "Record is updated");
				 handleClear();
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	 private void handleClear() {
		 t1.setText("");
		 t2.setText("");
		 t3.setText("");
		 t4.setText("");
		 t5.setText("");
		 t6.setText("");
		 t7.setText("");
		 t8.setText("");
		 t9.setText("");
		 t10.setText("");
		 t11.setText("");
		 t12.setText("");
	 }

	 private void handleAll() {
		 int r = 0;
		 try {
			 statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 resultSet = statement.executeQuery("SELECT * from medicine" );
			 while(resultSet.next()) {
				 model.insertRow(r++, new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12) });
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	public static void main(String[] args) {
		new UpdateMedicine();
	}
}
