package com.medical.store.main;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
	public About() {
		super("About Project");
		setSize(900,700);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		JLabel l1 = new JLabel("Medical Stock Management System");
		l1.setFont(new Font("Times New Roman",Font.BOLD,35));
		l1.setBounds(200,30,600,40);
		l1.setForeground(Color.blue);
		this.add(l1);

		JLabel l2 = new JLabel("This Project developed by:");
		l2.setFont(new Font("Times New Roman",Font.BOLD,20));
		l2.setBounds(250,150,600,40);
		l2.setForeground(Color.BLACK);
		this.add(l2);

		JLabel l3 = new JLabel("Makechi Eric");
		l3.setFont(new Font("Times New Roman",Font.BOLD,30));
		l3.setBounds(500,150,400,40);
		l3.setForeground(Color.red);
		this.add(l3);

		JLabel phone = new JLabel("Contact Me :");
		phone.setFont(new Font("Times New Roman",Font.BOLD,20));
		phone.setBounds(300,200,600,40);
		phone.setForeground(Color.BLACK);
		this.add(phone);
		
		JLabel call=new JLabel("+254-716-862-131");
		call.setFont(new Font("Times New Roman",Font.BOLD,30));
		call.setBounds(430,200,400,40);
		call.setForeground(Color.red);
		this.add(call);
		
		JLabel feature=new JLabel("About Project");
		feature.setFont(new Font("Times New Roman",Font.BOLD,20));
		feature.setBounds(250,250,400,40);
		feature.setForeground(Color.GRAY);
		this.add(feature);
		JLabel l5 = new JLabel("In this system, one can add details of Medicines, Suppliers.");
		//l5.setFont(new Font("Times New Roman",Font.BOLD,20));
		l5.setForeground(Color.BLACK);
		l5.setBounds(250,300,800,40);
		this.add(l5);

		JLabel l6 = new JLabel("One can also update,delete & search the existing details.");
		//l6.setFont(new Font("Times New Roman",Font.BOLD,20));
		l6.setForeground(Color.BLACK);
		l6.setBounds(250,350,800,40);
		this.add(l6);

		JLabel l7 = new JLabel("It is helpful for keeping records of Medicine stock.");
		//l7.setFont(new Font("Times New Roman",Font.BOLD,20));
		l7.setForeground(Color.BLACK);
		l7.setBounds(250,400,800,40);
		this.add(l7);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new About();
	}
}
