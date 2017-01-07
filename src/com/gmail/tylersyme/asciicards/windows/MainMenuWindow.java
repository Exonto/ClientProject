package com.gmail.tylersyme.asciicards.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MainMenuWindow extends JFrame
{

	private JPanel contentPane;

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
					MainMenuWindow frame = new MainMenuWindow();
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
	public MainMenuWindow()
	{
		setTitle("The Scrolls");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFocusable(false);
		btnQuit.setFont(new Font("SWTOR Trajan", Font.PLAIN, 11));
		btnQuit.setFocusPainted(false);
		btnQuit.setBounds(11, 30, 122, 37);
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
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		usernameDisplayPanel.add(lblNewLabel_1, BorderLayout.CENTER);
		lblNewLabel_1.setFont(new Font("SWTOR Trajan", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel goldDisplayPanel = new JPanel();
		goldDisplayPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		goldDisplayPanel.setBounds(0, 48, 190, 52);
		userInfoPanel.add(goldDisplayPanel);
		goldDisplayPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGold = new JLabel("Gold - 0");
		goldDisplayPanel.add(lblGold, BorderLayout.CENTER);
		lblGold.setFont(new Font("Trajan Pro", Font.PLAIN, 13));
		lblGold.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton friendsButton = new JButton("Friends");
		friendsButton.setFocusable(false);
		friendsButton.setFocusPainted(false);
		friendsButton.setFont(new Font("SWTOR Trajan", Font.PLAIN, 11));
		friendsButton.setBounds(546, 11, 133, 50);
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
		lblComputer.setFont(new Font("SWTOR Trajan", Font.PLAIN, 19));
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
		lblUnranked.setFont(new Font("SWTOR Trajan", Font.PLAIN, 19));
		
		JButton btnRandomOpponent = new JButton("Random Opponent");
		btnRandomOpponent.setFocusable(false);
		btnRandomOpponent.setFont(new Font("SWTOR Trajan", Font.BOLD, 12));
		btnRandomOpponent.setFocusPainted(false);
		btnRandomOpponent.setBounds(17, 60, 199, 50);
		unrankedGamePanel.add(btnRandomOpponent);
		
		JButton btnChallengeAFriend = new JButton("<html>Challenge a<br> <center>Friend</center></html>");
		btnChallengeAFriend.setFocusable(false);
		btnChallengeAFriend.setFont(new Font("SWTOR Trajan", Font.BOLD, 12));
		btnChallengeAFriend.setFocusPainted(false);
		btnChallengeAFriend.setBounds(17, 116, 199, 50);
		unrankedGamePanel.add(btnChallengeAFriend);
	}
}
