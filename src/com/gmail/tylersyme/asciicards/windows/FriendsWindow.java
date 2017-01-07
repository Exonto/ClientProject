package com.gmail.tylersyme.asciicards.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FriendsWindow extends JFrame
{
	private JTextField friendRequestTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					FriendsWindow frame = new FriendsWindow();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FriendsWindow()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 380);
		getContentPane().setLayout(null);
		
		JPanel friendsPanel = new JPanel();
		friendsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		friendsPanel.setBounds(6, 1, 180, 300);
		getContentPane().add(friendsPanel);
		friendsPanel.setLayout(null);
		
		JLabel lblFriends = new JLabel("Friends");
		lblFriends.setBounds(0, 0, 180, 30);
		lblFriends.setBorder(new LineBorder(new Color(0, 0, 0)));
		friendsPanel.add(lblFriends);
		lblFriends.setFont(new Font("SWTOR Trajan", Font.PLAIN, 12));
		lblFriends.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane friendsScrollPanel = new JScrollPane();
		friendsScrollPanel.setBounds(0, 30, 180, 270);
		friendsPanel.add(friendsScrollPanel);
		
		JList friendsList = new JList();
		friendsScrollPanel.setViewportView(friendsList);
		
		JButton btnSendFriendRequest = new JButton("Send Request");
		btnSendFriendRequest.setEnabled(false);
		btnSendFriendRequest.setFocusable(false);
		btnSendFriendRequest.setFocusPainted(false);
		btnSendFriendRequest.setFont(new Font("SWTOR Trajan", Font.PLAIN, 12));
		btnSendFriendRequest.setBounds(0, 303, 133, 43);
		getContentPane().add(btnSendFriendRequest);
		
		friendRequestTextField = new JTextField();
		friendRequestTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		friendRequestTextField.setBounds(143, 307, 200, 34);
		getContentPane().add(friendRequestTextField);
		friendRequestTextField.setColumns(10);
		
		JPanel friendRequestsPanel = new JPanel();
		friendRequestsPanel.setLayout(null);
		friendRequestsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		friendRequestsPanel.setBounds(190, 1, 180, 300);
		getContentPane().add(friendRequestsPanel);
		
		JLabel lblFriendRequests = new JLabel("Friend Requests");
		lblFriendRequests.setHorizontalAlignment(SwingConstants.CENTER);
		lblFriendRequests.setFont(new Font("SWTOR Trajan", Font.PLAIN, 12));
		lblFriendRequests.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblFriendRequests.setBounds(0, 0, 180, 30);
		friendRequestsPanel.add(lblFriendRequests);
		
		JScrollPane friendRequestsScrollPanel = new JScrollPane();
		friendRequestsScrollPanel.setBounds(0, 30, 180, 270);
		friendRequestsPanel.add(friendRequestsScrollPanel);
		
		JList friendRequestsList = new JList();
		friendRequestsScrollPanel.setViewportView(friendRequestsList);
	}
}
