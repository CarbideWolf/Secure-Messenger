/**
 * 
 * @author CarbideWolf
 *
 */

package com.carbidewolf;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;
import com.carbidewolf.gui.StartupGUI;
import com.carbidewolf.reference.Reference;


public class Core
{
	public static String optionFilePath = Core.class.getClassLoader().getResource("").getPath()+"Options.txt";
	public static Color contentColour = new Color(39,39,39);
	public static void main(String args[])
	{
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		UIManager.put("OptionPane.messageForeground", Color.GREEN);

		UIManager.put("Button.background", contentColour);
		UIManager.put("Button.foreground", Color.GREEN);
		UIManager.put("Button.foreground", Color.GREEN);
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reference.startFrame = new StartupGUI();
					Reference.startFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}