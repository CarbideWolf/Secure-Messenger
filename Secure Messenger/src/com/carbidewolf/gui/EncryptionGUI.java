package com.carbidewolf.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.Core;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Richousrick
 *
 */

@SuppressWarnings("serial")
public class EncryptionGUI extends JFrame
{

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public EncryptionGUI()
	{
		setResizable(false);
		
		Color contentColour = new Color(39,39,39);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 593);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea mainTextArea = new JTextArea();
		mainTextArea.setForeground(Color.GREEN);
		mainTextArea.setEditable(false);
		mainTextArea.setDisabledTextColor(Color.GREEN);
		mainTextArea.setBackground(contentColour);
		mainTextArea.setSelectionColor(Color.BLACK);
		mainTextArea.setSelectedTextColor(Color.BLACK);
		mainTextArea.setBounds(30, 31, 754, 449);
		contentPane.add(mainTextArea);
		
		JTextArea SendTextArea = new JTextArea();
		SendTextArea.setForeground(Color.GREEN);
		SendTextArea.setDisabledTextColor(Color.GREEN);
		SendTextArea.setBackground(contentColour);
		SendTextArea.setBounds(30, 512, 431, 22);
		contentPane.add(SendTextArea);
		
		JButton sendButton = new JButton("Send");
		sendButton.setBorderPainted(false);
		sendButton.setForeground(Color.GREEN);
		sendButton.setBackground(contentColour);
		sendButton.setBounds(471, 511, 89, 23);
		contentPane.add(sendButton);
		
		JButton optionsButton = new JButton("Options");
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OptionGUI.init();
			}
		});
		optionsButton.setBorderPainted(false);
		optionsButton.setForeground(Color.GREEN);
		optionsButton.setBackground(contentColour);
		optionsButton.setBounds(695, 511, 89, 23);
		contentPane.add(optionsButton);
	}
}
