package com.carbidewolf.gui;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.networking.Client;
import com.carbidewolf.networking.Common;
import com.carbidewolf.reference.Reference;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * The menu that allows you to create or connect to a server
 * @author Richousrick
 */
public class StartupGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField ipField;

	
	/**
	 * Create the menu with default position
	 */
	public StartupGUI(){
		this(100, 100);
	}

	/**
	 * Creates the menu wit specified position
	 * @param x coordinate of the menu
	 * @param y coordinate of the menu
	 */
	public StartupGUI(int x, int y) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.GREEN);
		lblUsername.setBounds(30, 31, 89, 14);
		contentPane.add(lblUsername);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setForeground(Color.GREEN);
		lblServerIp.setBounds(30, 84, 89, 14);
		contentPane.add(lblServerIp);
		
		usernameField = new JTextField();
		usernameField.setBorder(null);
		usernameField.setForeground(Color.GREEN);
		usernameField.setBackground(Reference.contentColour);
		usernameField.setBounds(129, 28, 275, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		ipField = new JTextField();
		ipField.setBorder(null);
		ipField.setForeground(Color.GREEN);
		ipField.setBackground(Reference.contentColour);
		ipField.setBounds(129, 81, 275, 20);
		contentPane.add(ipField);
		ipField.setColumns(10);
		
		JButton connectButton = new JButton("Connect");
		connectButton.setBorder(null);
		connectButton.setForeground(Color.GREEN);
		connectButton.setBackground(Reference.contentColour);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInput()){
					runMainMenu(true);
				}else{
					JOptionPane.showMessageDialog(null, "Invalid Ip");
				}
			}
		});
		connectButton.setBounds(315, 208, 89, 23);
		contentPane.add(connectButton);
		
		JButton hostButton = new JButton("Host");
		hostButton.setBorder(null);
		hostButton.setForeground(Color.GREEN);
		hostButton.setBackground(Reference.contentColour);
		hostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validUsername()){
					int option = JOptionPane.showConfirmDialog(Reference.startFrame, "Are you sure you want to host a server?", "Host Server?", JOptionPane.YES_NO_OPTION);
					if(option == 0){
						
						runMainMenu(false);
					}
				}else{
					JOptionPane.showMessageDialog(Reference.startFrame, "Username should be longer than 3 chars");
				}
				
			}
		});
		hostButton.setBounds(30, 208, 89, 23);
		contentPane.add(hostButton);
	}
	
	/**
	 * Validates the entered username
	 * @return If it passes validation
	 */
	private boolean validUsername() {
		if(usernameField.getText().length()>3){
			return true;
		}
		return false;
	}
	
	/**
	 * Validates all entered text
	 * @return If it passes validation
	 */
	private boolean validateInput(){
		if(!Client.validIp(ipField.getText())){
			return false;
		}
		return validUsername();
	}
	
	/**
	 * Creates the network connection and sets up the main menu
	 * @param client if the client side is to be created or server
	 */
	public void runMainMenu(boolean client){
		Reference.username = usernameField.getText();
		if(client){
			Reference.ip = ipField.getText();
			Thread netThread = new Thread(){
				public void run(){
					try {
						new Client(ipField.getText());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			};
			netThread.start();
			try
			{
				int timer = 0;
				while(!Reference.connected){
					if(Reference.canConnect ){
						//wait for client server connection and stop trying after a second
						
						TimeUnit.MILLISECONDS.sleep(100);
						timer ++;
						if(timer>10){
							Reference.canConnect = false;
							break;
						}
					}else{
						break;
					}
				}
				if(Reference.canConnect){
					Point pos = Reference.startFrame.getLocationOnScreen();
					Reference.mainFrame = new EncryptionGUI(pos.x, pos.y);
					Reference.mainFrame.setTitle(Reference.name+" -Client");
					Reference.mainFrame.setVisible(true);
					dispose();
				}else{
					JOptionPane.showMessageDialog(Reference.startFrame, "Failed to Connect to server");
					Reference.canConnect = true;
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}else{
			try {
				URL whatismyip = new URL("http://checkip.amazonaws.com");
				BufferedReader in = new BufferedReader(new InputStreamReader(
				                whatismyip.openStream()));
	
				
				Reference.ip = in.readLine();
				in.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Thread netThread = new Thread(){
				public void run(){
					try {
						new Common();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			netThread.start();
			Point pos = ((JFrame)Reference.startFrame).getLocationOnScreen();
			Reference.mainFrame = new EncryptionGUI(pos.x, pos.y);
			Reference.mainFrame.setTitle(Reference.name+" -Server");
			Reference.mainFrame.setVisible(true);
			dispose();
		}
	}
}
