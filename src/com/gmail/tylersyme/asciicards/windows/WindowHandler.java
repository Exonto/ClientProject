package com.gmail.tylersyme.asciicards.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



/**
 * Allows for easy communication between JFrames and JPanels.
 */
public class WindowHandler
{
	
	private static WindowHandler handler;
	
	public static WindowHandler getHandler()
	{
		if (handler == null)
		{
			handler = new WindowHandler(UIManager.getSystemLookAndFeelClassName());
		}
		
		return handler;
	}
	
// -----------------------------------------------------------------------------
	
	// The frame(s) which will open when the application launches
	private List<JFrame> launchFrames = new ArrayList<>();
	
	// Contains the important frames of the program
	private Map<String, JFrame> priorityFrames = new HashMap<>();
	
	private WindowHandler(String lookAndFeel)
	{
		try
		{
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addLaunchFrame(JFrame launchFrame)
	{
		this.launchFrames.add(launchFrame);
	}
	
	public void addPriorityFrame(String name, JFrame priorityFrame)
	{
		this.priorityFrames.put(name, priorityFrame);
	}
	
	public JFrame getFrameByName(String name)
	{
		return this.priorityFrames.get(name);
	}
	
	public void displayFrameByName(String name)
	{
		JFrame frame = this.getFrameByName(name);
		this.centerWindow(frame);
		frame.setVisible(true);
	}
	
	public void hideFrameByName(String name)
	{
		JFrame frame = this.getFrameByName(name);
		frame.setVisible(false);
	}
	
	/**
	 * <p>
	 * Displays all launch frames to the screen.
	 * </p>
	 * <p>
	 * If a launch frame implements {@link LaunchFrame}, the {@code launchWindow}
	 * method will be called on that JFrame.
	 * </p>
	 */
	public void launch()
	{
		for (JFrame frame : this.launchFrames)
		{
			this.centerWindow(frame);
			frame.setVisible(true);
			
			if (frame instanceof LaunchFrame)
			{
				((LaunchFrame) frame).launchWindow();
			}
		}
	}

// -----------------------------------------------------------------------------
	
	/**
	 * Centers this window to the screen based upon the monitor's screen
	 * resolution and this frame's pre-defined width and height.
	 */
	private void centerWindow(JFrame frame)
	{
		Dimension resolution = this.getScreenResolution();

		// Centers this window's location
		frame.setLocation((resolution.width / 2) - (frame.getWidth() / 2),
				(resolution.height / 2) - (frame.getHeight() / 2));
	}

	/**
	 * @return The screen resolution in terms of width and height
	 */
	private Dimension getScreenResolution()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}




