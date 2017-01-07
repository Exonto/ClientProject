package com.gmail.tylersyme.asciicards.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests.Commands;
import com.gmail.tylersyme.asciicards.events.EventHandler;
import com.gmail.tylersyme.asciicards.events.FriendAcceptedEvent;
import com.gmail.tylersyme.asciicards.events.FriendAcceptedListener;
import com.gmail.tylersyme.asciicards.events.FriendRequestEvent;
import com.gmail.tylersyme.asciicards.events.FriendRequestListener;
import com.gmail.tylersyme.asciicards.game.PlayerProfile;

@SuppressWarnings("serial")
public class FriendsFrame extends JFrame implements ActionListener, 
													KeyListener,
													
													FriendRequestListener,
													FriendAcceptedListener
{
	private PlayerProfile profile;
	
	private DefaultListModel<String> friendRequestsListModel = 
			new DefaultListModel<>();
	private DefaultListModel<String> friendsListModel = 
			new DefaultListModel<>();
// -----------------------------------------------------------------------------
// Exposed Component Variables
// -----------------------------------------------------------------------------	

	private JList<String> friendRequestsList;
	private JList<String> friendsList;
	
	private JButton btnSendFriendRequest;
	private JTextField friendRequestTextField;
	
	/**
	 * Initializes the window and all of its attributes.
	 */
	public FriendsFrame(PlayerProfile profile)
	{
		this.initializeFrame();
		
		this.profile = profile;
		
		this.loadFriendRequests();
		this.loadFriends();
		
		EventHandler.getHandler().addFriendRequestListener(this);
		EventHandler.getHandler().addFriendAcceptanceListener(this);
	}
	
// -----------------------------------------------------------------------------
// Component Manipulation
// -----------------------------------------------------------------------------
	
	/** 
	 * Loads the player's friend requests to the appropriate {@code JList}.
	 * 
	 * @param profile The profile containing the friend requests 
	 */
	public void loadFriendRequests()
	{
		for (String username : this.profile.getReceivedFriendRequestsAsArray())
		{
			this.friendRequestsListModel.addElement(username);
		}
	}
	
	/** 
	 * Loads the player's friends to the appropriate {@code JList}.
	 * 
	 * @param profile The profile containing the friends
	 */
	public void loadFriends()
	{
		for (String username : this.profile.getFriends())
		{
			this.friendsListModel.addElement(username);
		}
	}
	
// -----------------------------------------------------------------------------
// Event Handling
// -----------------------------------------------------------------------------

	/**
	 * Updates the friend requests JList.
	 */
	@Override
	public void friendRequestOccured(FriendRequestEvent e)
	{
		if (e.isSuccessful())
		{
			if (e.getSender().equals(this.profile.getUsername()) == false)
			{
				this.friendRequestsListModel.addElement(e.getSender());
				this.friendRequestsList.repaint();
			}
		}
	}
	
	/**
	 * Updates the friends JList.
	 */
	@Override
	public void friendAcceptanceOccurred(FriendAcceptedEvent e)
	{
		if (e.isSuccessful())
		{
			// Is this player the original sender
			if (this.profile.getUsername().equals(e.getSender()))
			{
				this.friendsListModel.addElement(e.getReceiver());
			} else {
				this.friendsListModel.addElement(e.getSender());
				this.friendRequestsListModel.removeElement(e.getSender());
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		// Sends a friend request to the server to be processed
		if (source == this.btnSendFriendRequest && 
			this.friendRequestTextField.getText().length() > 0)
		{
			Commands.sendRequest(
					Commands.FRIEND_REQUEST_REQUEST, 
					this.friendRequestTextField.getText());
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		Object source = e.getSource();
		
		// Toggles the enabled status of the friend request button depending on
		// whether any text is in the text box 
		if (source == this.friendRequestTextField)
		{
			if (this.friendRequestTextField.getText().isEmpty() == false)
			{
				this.btnSendFriendRequest.setEnabled(true);
			} else {
				this.btnSendFriendRequest.setEnabled(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
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
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 380);
		setLocationRelativeTo(null); // Center's the window to the screen
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
		
		friendsList = new JList<>(this.friendsListModel);
		friendsScrollPanel.setViewportView(friendsList);
		
		btnSendFriendRequest = new JButton("Send Request");
		btnSendFriendRequest.setEnabled(false);
		btnSendFriendRequest.setFocusable(false);
		btnSendFriendRequest.setFocusPainted(false);
		btnSendFriendRequest.setFont(new Font("SWTOR Trajan", Font.PLAIN, 12));
		btnSendFriendRequest.setBounds(0, 303, 133, 43);
		btnSendFriendRequest.addActionListener(this);
		getContentPane().add(btnSendFriendRequest);
		
		friendRequestTextField = new JTextField();
		friendRequestTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		friendRequestTextField.setBounds(143, 307, 200, 34);
		getContentPane().add(friendRequestTextField);
		friendRequestTextField.setColumns(10);
		friendRequestTextField.addKeyListener(this);
		
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
		
		friendRequestsList = new JList<>(this.friendRequestsListModel);
		friendRequestsList.addMouseListener(
		new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent mouseEvent)
			{
				@SuppressWarnings("unchecked")
				JList<String> theList = (JList<String>) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) // Double click occurred
				{
					// Get double clicked index location
					int index = theList.locationToIndex(mouseEvent.getPoint());
					
					// Get the username located in that index
					String username = FriendsFrame.this
												  .friendRequestsListModel
												  .elementAt(index);
					
					// Send a friend accepted request to the server
					Commands.sendRequest(
							Commands.FRIEND_ACCEPTED_REQUEST, 
							username); // The original sender
				}
			}
		});
		friendRequestsScrollPanel.setViewportView(friendRequestsList);
	}

		
}












