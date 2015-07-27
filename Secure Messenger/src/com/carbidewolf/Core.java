/**
 * 
 * @author CarbideWolf
 *
 */

package com.carbidewolf;

import javax.swing.JFrame;

import com.carbidewolf.gui.StartupGUI;

public class Core
{
	public static String optionFilePath = Core.class.getClassLoader().getResource("").getPath()+"Options.txt";
	public static JFrame mainFrame;
	public static void main(String args[])
	{
		StartupGUI.init();
	}
}