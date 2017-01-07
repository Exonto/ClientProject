package com.gmail.tylersyme.asciicards.windows.panels;

import java.awt.Dimension;

import javax.swing.JPanel;

public class LoginPanel extends JPanel
{
	/**
	 * Initializes the panel and all of its components.
	 */
	public LoginPanel()
	{
		this.initializePanel();
	}
	
// -----------------------------------------------------------------------------
// Initialization Assistance
// -----------------------------------------------------------------------------
	
	public void initializePanel()
	{
		this.setPreferredSize(new Dimension(500, 400));
	}
}
