package com.carbidewolf.reference;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.PrintStream;

import com.carbidewolf.gui.EncryptionGUI;
import com.carbidewolf.gui.StartupGUI;
/**
 * This class holds all the commonly used variable used across the program
 * @author Richousrick
 */
public class Reference {

	public static String username = "";
	public static String ip = "";
	public static int port = 13078;
	public static StartupGUI startFrame = null;
	public static EncryptionGUI mainFrame = null;
	public static Color contentColour = new Color(39, 39, 39);
	public static boolean connected = false;
	public static boolean canConnect = true;
	public static BufferedReader in = null;
	public static String out = "";
	public static String otherUName;
	public static PrintStream outStream;
	public static String name = "EncripIMS";
}
