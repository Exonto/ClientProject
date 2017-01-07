package com.gmail.tylersyme.asciicards.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests.Commands;
import com.gmail.tylersyme.asciicards.events.EventHandler;
import com.gmail.tylersyme.asciicards.events.LoginEvent;
import com.gmail.tylersyme.asciicards.events.LoginListener;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener, LoginListener
{
	
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JButton btnRegister;
	private JButton btnLogin;

	/**
	 * Initializes the window and all of its attributes.
	 */
	public LoginFrame()
	{
		this.initializeFrame();
		
		EventHandler.getHandler().addLoginListener(this);
	}
	
// -----------------------------------------------------------------------------
// Initialization Assistance
// -----------------------------------------------------------------------------

	/**
	 * Initializes all attributes of this JFrame.<br>
	 * Also initializes and adds the main menu panel to the window.
	 */
	private void initializeFrame()
	{
		// -- Initialize the Frame -- //

		setTitle("The Scrolls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		getContentPane().setLayout(null);
		
		// Initialize Components - Absolute Layout Style
		
		JLabel lblNewLabel = new JLabel("Username - ");
		lblNewLabel.setBounds(71, 77, 64, 14);
		getContentPane().add(lblNewLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(127, 69, 200, 30);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password - ");
		lblPassword.setBounds(71, 123, 64, 14);
		getContentPane().add(lblPassword);
		
		passwordTextField = new JTextField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(127, 115, 200, 30);
		getContentPane().add(passwordTextField);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(232, 156, 95, 30);
		btnRegister.setFocusPainted(false);
		btnRegister.addActionListener(this);
		getContentPane().add(btnRegister);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(127, 156, 95, 30);
		btnLogin.setFocusPainted(false);
		btnLogin.addActionListener(this);
		getContentPane().add(btnLogin);
	}
	
// -----------------------------------------------------------------------------
// Event Handling
// -----------------------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		// Both username and password fields must have text in them
		if (this.usernameTextField.getText().isEmpty() == false && 
			this.passwordTextField.getText().isEmpty() == false)
		{
			if (source == this.btnLogin)
			{
				Commands.sendRequest(Commands.LOGIN_REQUEST, 
						this.usernameTextField.getText(), 
						this.passwordTextField.getText());
			} else if (source == this.btnRegister) {
				Commands.sendRequest(Commands.REGISTER_ACCOUNT_REQUEST, 
						this.usernameTextField.getText(), 
						this.passwordTextField.getText());
			}
		}
	}

	@Override
	public void login(LoginEvent e)
	{
		if (e.isSuccessful())
		{
			this.setVisible(false);
		}
	}
	
}











