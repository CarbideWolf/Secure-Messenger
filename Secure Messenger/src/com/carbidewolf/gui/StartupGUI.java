package com.carbidewolf.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.Core;

import javax.swing.JLabel;
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
public class StartupGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		lblUsername.setBounds(30, 31, 57, 14);
		contentPane.add(lblUsername);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setBounds(30, 84, 46, 14);
		contentPane.add(lblServerIp);
		
		textField = new JTextField();
		textField.setBounds(97, 28, 307, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(97, 81, 307, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
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
				}
			}
		});
		connectButton.setBounds(315, 208, 89, 23);
		contentPane.add(connectButton);
	}
	
	public boolean validateInput(){
		
		return true;
	}
	
}
