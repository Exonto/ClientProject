package com.gmail.tylersyme.asciicards.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests.Commands;
import com.gmail.tylersyme.asciicards.events.EventHandler;
import com.gmail.tylersyme.asciicards.events.FriendAcceptedEvent;
import com.gmail.tylersyme.asciicards.events.FriendAcceptedListener;
import com.gmail.tylersyme.asciicards.events.FriendRequestEvent;
import com.gmail.tylersyme.asciicards.events.FriendRequestListener;
import com.gmail.tylersyme.asciicards.events.LoginEvent;
import com.gmail.tylersyme.asciicards.events.LoginListener;
import com.gmail.tylersyme.asciicards.events.PlayerProfileUpdateEvent;
import com.gmail.tylersyme.asciicards.events.PlayerProfileUpdateListener;
import com.gmail.tylersyme.asciicards.game.GameState;
import com.gmail.tylersyme.asciicards.game.Player;
import com.gmail.tylersyme.asciicards.game.PlayerProfile;
import com.gmail.tylersyme.asciicards.game.cards.Card;
import com.gmail.tylersyme.asciicards.game.cards.CardFactory;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame implements ActionListener, 

													 LoginListener,
													 FriendRequestListener,
													 FriendAcceptedListener,
													 PlayerProfileUpdateListener
{
	private JPanel contentPane;
	
	private JButton btnQuit;
	private JButton friendsButton;
	private JButton btnChallengeAFriend;
	private JLabel lblUsername;
	
	private Player player;
	
	/**
	 * Initializes the window and all of its attributes.
	 */
	public MainMenuFrame()
	{
		this.initializeFrame();
		
		GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
	     try
		{
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, 
							new File("Resources/Fonts/DwarvenAxe.ttf")));
		} catch (FontFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// Subscribe to events
		EventHandler.getHandler().addLoginListener(this);
		EventHandler.getHandler().addFriendRequestListener(this);
		EventHandler.getHandler().addFriendAcceptanceListener(this);
		EventHandler.getHandler().addPlayerProfileUpdateListener(this);
	}
	
// -----------------------------------------------------------------------------
// Component Manipulation
// -----------------------------------------------------------------------------
	
	/**
	 * Sets the text of the {@code JLabel} containing the player's username in
	 * the main menu for the purposes of display.
	 */
	public void setUsernameDisplay(String username)
	{
		this.lblUsername.setText(username);
	}
	
// -----------------------------------------------------------------------------
// Event Handling
// -----------------------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		// Causes the program to fully terminate
		if (source == this.btnQuit)
		{
			System.exit(0);
		} else if (source == this.friendsButton) {
			
			// Opens a new friends frame with friends and friend requests 
			// loaded
			FriendsFrame friendsFrame = new FriendsFrame(this.player.getProfile());
			friendsFrame.setVisible(true);
		} else if (source == this.btnChallengeAFriend) {
			
		}
	}

	@Override
	public void login(LoginEvent e)
	{
		// Handles player login assuming the login attempt was successful
		if (e.isSuccessful())
		{
			// Setup and show this frame
			PlayerProfile profile = new PlayerProfile();
			profile.login(e.getUsername());
			
			this.player = new Player(profile);
			this.setUsernameDisplay(this.player.getUsername());
			this.setVisible(true);
			
			// Retrieve all important player information from the server
			// This is later handled when a PlayerStateEvent is fired
			Commands.sendRequest(Commands.PLAYER_PROFILE_REQUEST);
		}
	}
	
	@Override
	public void friendRequestOccured(FriendRequestEvent e)
	{
		if (e.isSuccessful())
		{
			this.player.getProfile().addFriendRequest(
					e.getSender(), 
					e.getReceiver());
		}
	}
	
	@Override
	public void friendAcceptanceOccurred(FriendAcceptedEvent e)
	{
		if (e.isSuccessful())
		{	
			String sender = e.getSender();
			String receiver = e.getReceiver();
			
			// Update the player profile
			if (this.player.getUsername().equals(sender))
			{
				// Add the receiver if this player was the sender
				this.player.getProfile().addFriend(receiver);
			} else {
				// Add the sender if this player was the receiver
				this.player.getProfile().addFriend(sender);
			}
			
			this.player.getProfile().removeFriendRequest(sender, receiver);
		}
	}
	
	@Override
	public void updatePlayerProfile(PlayerProfileUpdateEvent e)
	{
		this.player.getProfile()
				   .addReceivedFriendRequests(e.getReceivedFriendRequests());
		this.player.getProfile()
				   .addSentFriendRequests(e.getSentFriendRequests());
		
		this.player.getProfile().addFriends(e.getFriends());
		
		List<Card> cards = new ArrayList<>();
		
		int idx = 0;
		while (idx < e.getCardAttributes().size())
		{
			String qualifiedName = e.getCardAttributes().get(idx++);
			String title = e.getCardAttributes().get(idx++);
			String description = e.getCardAttributes().get(idx++);
			String quote = e.getCardAttributes().get(idx++);
			String cardType = e.getCardAttributes().get(idx++);
			String manaType = e.getCardAttributes().get(idx++);
			int manaCost = Integer.parseInt(e.getCardAttributes().get(idx++));
			int health = 0;
			int cooldown = 0;
			int damage = 0;
			if (cardType.equals("Creature"))
			{
				health = Integer.parseInt(e.getCardAttributes().get(idx++));
				cooldown = Integer.parseInt(e.getCardAttributes().get(idx++));
				damage = Integer.parseInt(e.getCardAttributes().get(idx++));
			}
			boolean isEnabled = Boolean.parseBoolean(e.getCardAttributes().get(idx++));
			++idx;
			
			if (cardType.equals("Creature"))
			{
				cards.add(CardFactory.createGenericCreatureCard(
						qualifiedName, 
						title, 
						description, 
						quote, 
						cardType, 
						manaType, 
						manaCost, 
						health, 
						cooldown, 
						damage,
						isEnabled));
			}
		}

		GameState.initializeCardFactory(new CardFactory(cards));
		
		System.out.println(GameState.getCardFactory().getGuardOfTheShield().getTitle());
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 660);
		setLocationRelativeTo(null); // Center's the window to the screen
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Initialize Components - Absolute Layout Style
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		statsPanel.setBounds(-2, 0, 146, 500);
		contentPane.add(statsPanel);
		statsPanel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Profile");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFont(new Font("SWTOR Trajan", Font.BOLD, 11));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBounds(10, 11, 126, 43);
		statsPanel.add(btnNewButton_1);
		
		JButton btnMyDecks = new JButton("<html><center>My<br></center> <center>Decks</center></html>");
		btnMyDecks.setFocusable(false);
		btnMyDecks.setFont(new Font("SWTOR Trajan", Font.BOLD, 11));
		btnMyDecks.setFocusPainted(false);
		btnMyDecks.setBounds(10, 65, 126, 43);
		statsPanel.add(btnMyDecks);
		
		JButton btnCardCollection = new JButton("<html><center>Card<br></center> <center>Collection</center></html>");
		btnCardCollection.setFocusable(false);
		btnCardCollection.setFont(new Font("SWTOR Trajan", Font.BOLD, 11));
		btnCardCollection.setFocusPainted(false);
		btnCardCollection.setBounds(10, 119, 126, 43);
		statsPanel.add(btnCardCollection);
		
		JButton btnCardDealer = new JButton("<html><center>Card<br></center> <center>Dealer</center></html>");
		btnCardDealer.setFocusable(false);
		btnCardDealer.setFont(new Font("SWTOR Trajan", Font.BOLD, 11));
		btnCardDealer.setFocusPainted(false);
		btnCardDealer.setBounds(10, 173, 126, 43);
		statsPanel.add(btnCardDealer);
		
		JButton btnTradingPost = new JButton("<html><center>Trading<br></center> <center>Post</center></html>");
		btnTradingPost.setFocusable(false);
		btnTradingPost.setFont(new Font("SWTOR Trajan", Font.BOLD, 11));
		btnTradingPost.setFocusPainted(false);
		btnTradingPost.setBounds(10, 227, 126, 43);
		statsPanel.add(btnTradingPost);
		
		JButton btnMatchHistory = new JButton("<html><center>Match<br></center> <center>History</center></html>");
		btnMatchHistory.setFocusable(false);
		btnMatchHistory.setFont(new Font("SWTOR Trajan", Font.BOLD, 11));
		btnMatchHistory.setFocusPainted(false);
		btnMatchHistory.setBounds(10, 281, 126, 43);
		statsPanel.add(btnMatchHistory);
		
		JPanel generalInfoPanel = new JPanel();
		generalInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		generalInfoPanel.setBounds(190, 534, 477, 100);
		contentPane.add(generalInfoPanel);
		generalInfoPanel.setLayout(null);
		
		this.btnQuit = new JButton("Quit");
		btnQuit.setFocusable(false);
		btnQuit.setFont(new Font("SWTOR Trajan", Font.PLAIN, 11));
		btnQuit.setFocusPainted(false);
		btnQuit.setBounds(11, 30, 122, 37);
		btnQuit.addActionListener(this);
		generalInfoPanel.add(btnQuit);
		
		JButton btnNews = new JButton("News");
		btnNews.setFocusable(false);
		btnNews.setFont(new Font("SWTOR Trajan", Font.PLAIN, 11));
		btnNews.setFocusPainted(false);
		btnNews.setBounds(178, 30, 122, 37);
		generalInfoPanel.add(btnNews);
		
		JButton btnChangeLog = new JButton("Change Log");
		btnChangeLog.setFocusable(false);
		btnChangeLog.setFont(new Font("SWTOR Trajan", Font.PLAIN, 11));
		btnChangeLog.setFocusPainted(false);
		btnChangeLog.setBounds(343, 30, 122, 37);
		generalInfoPanel.add(btnChangeLog);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		userInfoPanel.setBounds(180, 0, 190, 100);
		contentPane.add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		JPanel usernameDisplayPanel = new JPanel();
		usernameDisplayPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		usernameDisplayPanel.setBounds(0, 0, 190, 49);
		userInfoPanel.add(usernameDisplayPanel);
		usernameDisplayPanel.setLayout(new BorderLayout(0, 0));
		
		lblUsername = new JLabel("Username");
		usernameDisplayPanel.add(lblUsername, BorderLayout.CENTER);
		lblUsername.setFont(new Font("DwarvenAxeBBW00-Regular", Font.PLAIN, 26));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel goldDisplayPanel = new JPanel();
		goldDisplayPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		goldDisplayPanel.setBounds(0, 48, 190, 52);
		userInfoPanel.add(goldDisplayPanel);
		goldDisplayPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGold = new JLabel("Gold - 0");
		goldDisplayPanel.add(lblGold, BorderLayout.CENTER);
		lblGold.setFont(new Font("DwarvenAxeBBW00-Regular", Font.PLAIN, 25));
		lblGold.setHorizontalAlignment(SwingConstants.CENTER);
		
		friendsButton = new JButton("Friends");
		friendsButton.setFocusable(false);
		friendsButton.setFocusPainted(false);
		friendsButton.setFont(new Font("DwarvenAxeBBW00-Regular", Font.PLAIN, 23));
		friendsButton.setBounds(546, 11, 133, 50);
		friendsButton.addActionListener(this);
		contentPane.add(friendsButton);
		
		JPanel computerGamePanel = new JPanel();
		computerGamePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		computerGamePanel.setBounds(180, 130, 233, 370);
		contentPane.add(computerGamePanel);
		computerGamePanel.setLayout(null);
		
		JPanel computerGameTitlePanel = new JPanel();
		computerGameTitlePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		computerGameTitlePanel.setBounds(0, 0, 233, 48);
		computerGamePanel.add(computerGameTitlePanel);
		computerGameTitlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblComputer = new JLabel("Computer");
		lblComputer.setHorizontalAlignment(SwingConstants.CENTER);
		lblComputer.setFont(new Font("DwarvenAxeBBW00-Regular", Font.PLAIN, 32));
		computerGameTitlePanel.add(lblComputer);
		
		JPanel unrankedGamePanel = new JPanel();
		unrankedGamePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		unrankedGamePanel.setBounds(447, 130, 233, 370);
		contentPane.add(unrankedGamePanel);
		unrankedGamePanel.setLayout(null);
		
		JPanel unrankedGameTitlePanel = new JPanel();
		unrankedGameTitlePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		unrankedGameTitlePanel.setBounds(0, 0, 233, 48);
		unrankedGamePanel.add(unrankedGameTitlePanel);
		unrankedGameTitlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUnranked = new JLabel("Unranked");
		lblUnranked.setHorizontalAlignment(SwingConstants.CENTER);
		unrankedGameTitlePanel.add(lblUnranked, BorderLayout.CENTER);
		lblUnranked.setFont(new Font("DwarvenAxeBBW00-Regular", Font.PLAIN, 31));
		
		JButton btnRandomOpponent = new JButton("Random Opponent");
		btnRandomOpponent.setFocusable(false);
		btnRandomOpponent.setFont(new Font("DwarvenAxeBBW00-Regular", Font.PLAIN, 22));
		btnRandomOpponent.setFocusPainted(false);
		btnRandomOpponent.setBounds(17, 60, 199, 50);
		unrankedGamePanel.add(btnRandomOpponent);
		
		btnChallengeAFriend = new JButton("Challenge a Friend");
		btnChallengeAFriend.setFocusable(false);
		btnChallengeAFriend.setFont(new Font("DwarvenAxeBBW00-Regular", Font.PLAIN, 22));
		btnChallengeAFriend.setFocusPainted(false);
		btnChallengeAFriend.setBounds(17, 116, 199, 50);
		btnChallengeAFriend.addActionListener(this);
		unrankedGamePanel.add(btnChallengeAFriend);
	}
	
}











