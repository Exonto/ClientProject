package com.gmail.tylersyme.asciicards;

import com.gmail.tylersyme.asciicards.connection.ClientConnection;
import com.gmail.tylersyme.asciicards.connection.communicationcmdsrequests.Commands;
import com.gmail.tylersyme.asciicards.windows.LoginFrame;
import com.gmail.tylersyme.asciicards.windows.MainMenuFrame;
import com.gmail.tylersyme.asciicards.windows.WindowHandler;

public class Application
{
	private static Application app;
	
	public static void main(String[] args)
	{
		getApp().launch();
	}
	
	public static Application getApp()
	{
		if (app == null)
		{
			app = new Application();
		}
		
		return app;
	}

// -----------------------------------------------------------------------------
	
	private WindowHandler windowHandler;
	private ClientConnection conn;
	private Application()
	{
		
	}
	
	/**
	 * The launching point of the application.
	 */
	private void launch()
	{
		windowHandler = WindowHandler.getHandler();
		
		LoginFrame loginFrame = new LoginFrame();
		
		windowHandler.addLaunchFrame(loginFrame);
		windowHandler.addPriorityFrame("login_frame", loginFrame);
		
		MainMenuFrame mainMenuFrame = new MainMenuFrame();
		
		windowHandler.addPriorityFrame("main_menu_frame", mainMenuFrame);
		
		//windowHandler.addPriorityFrame("friends_frame", new FriendsFrame());
		
		conn = new ClientConnection();
		Commands.conn = conn;
		conn.start();
		
		
		
		windowHandler.launch();
	}
	
// -----------------------------------------------------------------------------
// Getters and Setters
// -----------------------------------------------------------------------------

	public ClientConnection getConn()
	{
		return conn;
	}
	
}









