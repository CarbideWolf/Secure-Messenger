package com.carbidewolf;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.carbidewolf.gui.EncryptionGUI;

/**
 * 
 * @author CarbideWolf
 *
 */

public class Core
{
	
	public static JFrame mainFrame;
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					mainFrame = new EncryptionGUI();
					mainFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}