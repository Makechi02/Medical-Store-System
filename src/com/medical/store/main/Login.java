package com.medical.store.main;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
	private final JTextField username_field = new JTextField();
	private final JPasswordField password_field = new JPasswordField();
	private int count = 0;

	public Login() {
		super("Medical Store System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		Font font = new Font("Chilanka", Font.BOLD, 20);
		Font buttons_font = new Font("Chilanka", Font.PLAIN, 18);
		Color background_color = Color.CYAN;

		JLabel heading = new JLabel("ADMINISTRATOR LOGIN");
		heading.setFont(new Font("Chilanka",Font.BOLD,30));
		heading.setBounds(0, 70, getWidth(), 50);
		heading.setHorizontalAlignment(JLabel.CENTER);
		add(heading);

		JLabel username_icon = new JLabel(new ImageIcon("images//users.png"));
		username_icon.setBounds(150,200,50,40);
		add(username_icon);

		JLabel username_label = new JLabel("Username:");
		username_label.setFont(font);
		username_label.setBounds(200,200,200,40);
		add(username_label);

		username_field.setBounds(350,200,250,40);
		username_field.setToolTipText("Enter Username");
		username_field.setFont(new Font("Chilanka", Font.PLAIN, 18));
		add(username_field);

		JLabel password_icon = new JLabel(new ImageIcon("images//pass.png"));
		password_icon.setBounds(150,260,50,30);
		add(password_icon);

		JLabel password_label = new JLabel("Password:");
		password_label.setFont(font);
		password_label.setBounds(200,260,200,30);
		add(password_label);

		password_field.setBounds(350,260,250,40);
		password_field.setToolTipText("Enter Password");
		password_field.setFont(new Font("Chilanka", Font.PLAIN, 18));
		add(password_field);

		JPanel buttons_panel = new JPanel();
		buttons_panel.setBounds(0, 370, getWidth(), 50);
		buttons_panel.setBackground(background_color);

		JButton login_button = new JButton("Login", new ImageIcon("images//Login.png"));
		login_button.setFont(buttons_font);
		login_button.addActionListener(e -> handleLogin());
		buttons_panel.add(login_button);

		JButton clear_button = new JButton("Clear", new ImageIcon("images//clear.png"));
		clear_button.setFont(buttons_font);
		clear_button.addActionListener(e -> handleClear());
		buttons_panel.add(clear_button);

		JButton exit_button = new JButton("Exit", new ImageIcon("images//exit.png"));
		exit_button.setFont(buttons_font);
		exit_button.addActionListener(e -> System.exit(0));
		buttons_panel.add(exit_button);

		add(buttons_panel);

		changeLookAndFeel();

		getContentPane().setBackground(background_color);
		setVisible(true);
	}

	private void handleLogin() {
		String username = username_field.getText();
		String password = String.valueOf(password_field.getPassword());

		if((username.compareTo("admin") == 0) && (password.compareTo("admin") == 0)) {
			setVisible(false);
			new MainMenu();
		} else {
			checkCounter();
		}
	}

	private void checkCounter() {
		count++;
		JOptionPane.showMessageDialog(this,"Invalid user credentials!!!",null, JOptionPane.ERROR_MESSAGE);
		handleClear();
		if(count == 3) {
			JOptionPane.showMessageDialog(this,"Maximum attempts reached!", null, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	private void handleClear() {
		username_field.setText("");
		password_field.setText("");
	}

	private void changeLookAndFeel() {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(looks[1].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
				 IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
