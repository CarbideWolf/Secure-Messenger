package com.carbidewolf.gui;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.Core;
import com.carbidewolf.networking.Common;
import com.carbidewolf.reference.Reference;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

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

	

	public StartupGUI(){
		this(100, 100);
	}

	/**
	 * Create the frame.
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
		usernameField.setBackground(Core.contentColour);
		usernameField.setBounds(129, 28, 275, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		ipField = new JTextField();
		ipField.setBorder(null);
		ipField.setForeground(Color.GREEN);
		ipField.setBackground(Core.contentColour);
		ipField.setBounds(129, 81, 275, 20);
		contentPane.add(ipField);
		ipField.setColumns(10);
		
		JButton connectButton = new JButton("Connect");
		connectButton.setBorder(null);
		connectButton.setForeground(Color.GREEN);
		connectButton.setBackground(Core.contentColour);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInput()){
					Reference.username = usernameField.getText();
					Reference.ip = ipField.getText();
					EventQueue.invokeLater(new Runnable()
					{
						public void run()
						{
							try
							{
								Point pos = Reference.startFrame.getLocationOnScreen();
								Reference.mainFrame = new EncryptionGUI(pos.x, pos.y);
								Reference.mainFrame.setVisible(true);
								dispose();
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
		hostButton.setBorder(null);
		hostButton.setForeground(Color.GREEN);
		hostButton.setBackground(Core.contentColour);
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
//		if(!Client.validIp(ipField.getText())){
//			return false;
//		}
//		if(!Client.serverUp(ipField.getText())){
//			return false;
//		}
		return true;
	}
}
