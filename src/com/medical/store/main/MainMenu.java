package com.medical.store.main;

import com.medical.store.medicine.*;
import com.medical.store.reports.DailyPurchaseReport;
import com.medical.store.reports.SupplierWiseMedList;
import com.medical.store.supplier.*;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	int width = screen.width - 50;
	int height = screen.height - 50;

	public MainMenu() {
		super("Main Menu");
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.white);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("WELCOME TO MEDICAL STOCK MANAGEMENT SYSTEM");
		label.setFont(new Font("Chilanka",Font.BOLD,35));
		add(label);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu supplier_menu = new JMenu("Supplier");
		menuBar.add(supplier_menu);
		JMenuItem m1_1 = new JMenuItem("Add New Supplier", new ImageIcon("images//add.png"));
		supplier_menu.add(m1_1);
		JMenuItem m1_2 = new JMenuItem("search Supplier", new ImageIcon("images//search.png"));
		supplier_menu.add(m1_2);
		JMenuItem m1_3 = new JMenuItem("Update Supplier", new ImageIcon("images//update.png"));
		supplier_menu.add(m1_3);
		JMenuItem m1_4 = new JMenuItem("Delete Supplier", new ImageIcon("images//delete.png"));
		supplier_menu.add(m1_4);
		JMenuItem m1_5 = new JMenuItem("List of Supplier", new ImageIcon("images//all.png"));
		supplier_menu.add(m1_5);

		JMenu medicine_menu = new JMenu("Medicine");
		menuBar.add(medicine_menu);
		JMenuItem m2_1 = new JMenuItem("Add New Medicine", new ImageIcon("images//add.png"));
		medicine_menu.add(m2_1);
		JMenuItem m2_2 = new JMenuItem("search Medicine", new ImageIcon("images//search.png"));
		medicine_menu.add(m2_2);
		JMenuItem m2_3 = new JMenuItem("Update Medicine", new ImageIcon("images//update.png"));
		medicine_menu.add(m2_3);
		JMenuItem m2_4 = new JMenuItem("Delete Medicine", new ImageIcon("images//delete.png"));
		medicine_menu.add(m2_4);
		JMenuItem m2_5 = new JMenuItem("Stock of Medicine", new ImageIcon("images//all.png"));
		medicine_menu.add(m2_5);

		JMenu report_menu = new JMenu("Report");
	    menuBar.add(report_menu);
		JMenuItem m3_1 = new JMenuItem("Daily Purchase Report", new ImageIcon("images//report.png"));
		report_menu.add(m3_1);
		JMenuItem m3_2 = new JMenuItem("Supplier wise medicine Report", new ImageIcon("images//report.png"));
		report_menu.add(m3_2);

		JMenu about_menu = new JMenu("About");
		menuBar.add(about_menu);
		JMenuItem m4_1 = new JMenuItem("About System", new ImageIcon("images//help.png"));
		about_menu.add(m4_1);

		JMenu exit_menu = new JMenu("Exit");
		menuBar.add(exit_menu);
		JMenuItem m5_1 = new JMenuItem("Exit", new ImageIcon("images//exit.png"));
		exit_menu.add(m5_1);

        m1_1.addActionListener(e -> new AddNewSupplier());
		m1_2.addActionListener(e -> new SearchSupplier());
		m1_3.addActionListener(e -> new UpdateSupplier());
		m1_4.addActionListener(e -> new DeleteSupplier());
    	m1_5.addActionListener(e -> new SupplierList());
		m2_1.addActionListener(e -> new AddNewMedicine());
		m2_2.addActionListener(e -> new SearchMedicine());
		m2_3.addActionListener(e -> new UpdateMedicine());
		m2_4.addActionListener(e -> new DeleteMedicine());
	    m2_5.addActionListener(e -> new MedicineList());
		m3_1.addActionListener(e -> new DailyPurchaseReport());
		m3_2.addActionListener(e -> new SupplierWiseMedList());
		m4_1.addActionListener(e -> new About());
		m5_1.addActionListener(e -> System.exit(0));

		setVisible(true);
	}

	public static void main(String[] args) {
		new MainMenu();
	}
}
