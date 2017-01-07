package com.gmail.tylersyme.asciicards.windows;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame
{
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnRegister;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LoginWindow()
	{
		setTitle("The Scrolls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username - ");
		lblNewLabel.setBounds(71, 77, 64, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(127, 69, 200, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password - ");
		lblPassword.setBounds(71, 123, 64, 14);
		getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 115, 200, 30);
		getContentPane().add(textField_1);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(232, 156, 95, 30);
		btnRegister.setFocusPainted(false);
		getContentPane().add(btnRegister);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(127, 156, 95, 30);
		btnLogin.setFocusPainted(false);
		getContentPane().add(btnLogin);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), textField, textField_1, btnLogin, btnRegister, lblNewLabel, lblPassword}));
	}
}
