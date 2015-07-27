package com.carbidewolf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.Core;
import com.carbidewolf.networking.Client;
import com.carbidewolf.networking.Common;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * TODO Annotate Class
 *
 * @author Richousrick
 *
 */
@SuppressWarnings("serial")
public class StartupGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField ipField;

	/**
	 * Launch the application.
	 */
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartupGUI frame = new StartupGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartupGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(30, 31, 89, 14);
		contentPane.add(lblUsername);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setBounds(30, 84, 89, 14);
		contentPane.add(lblServerIp);
		
		usernameField = new JTextField();
		usernameField.setBounds(129, 28, 275, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		ipField = new JTextField();
		ipField.setBounds(129, 81, 275, 20);
		contentPane.add(ipField);
		ipField.setColumns(10);
		
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInput()){
					
					dispose();
					EventQueue.invokeLater(new Runnable()
					{
						public void run()
						{
							try
							{
								Core.mainFrame = new EncryptionGUI();
								Core.mainFrame.setVisible(true);
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					});
				}else{
					JOptionPane.showMessageDialog(null, "Invalid Ip");
				}
			}
		});
		connectButton.setBounds(315, 208, 89, 23);
		contentPane.add(connectButton);
		
		JButton hostButton = new JButton("Host");
		hostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to host a server?", "Host Server?", JOptionPane.YES_NO_OPTION);
				if(option == 0){
					Common.hostServer();
					JOptionPane.showMessageDialog(null, "Server sucessfully hosted at "+Common.ip);
				}
			}
		});
		hostButton.setBounds(30, 208, 89, 23);
		contentPane.add(hostButton);
	}
	
	public boolean validateInput(){
		if(!Client.validIp(ipField.getText())){
			return false;
		}
		if(!Client.serverUp(ipField.getText())){
			return false;
		}
		return true;
	}
}
