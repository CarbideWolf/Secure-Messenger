package com.carbidewolf;

import java.awt.EventQueue;

import com.carbidewolf.gui.EncryptionGUI;

/**
 * 
 * @author CarbideWolf
 *
 */

public class Core
{
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					EncryptionGUI frame = new EncryptionGUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}