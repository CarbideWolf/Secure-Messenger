/**
 * 
 * @author Richousrick
 *
 */

package com.carbidewolf.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.reference.Reference;
import com.sun.webkit.graphics.Ref;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EncryptionGUI extends JFrame
{

	boolean ctrlDown = false;
	private JPanel contentPane;
	private final JTextArea sendTextArea;
	private JButton sendButton;
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
		
		final JTextArea mainTextArea = new JTextArea();
		mainTextArea.setForeground(Color.GREEN);
		mainTextArea.setEditable(false);
		mainTextArea.setDisabledTextColor(Color.GREEN);
		mainTextArea.setBackground(contentColour);
		mainTextArea.setSelectionColor(Color.BLACK);
		mainTextArea.setSelectedTextColor(Color.BLACK);
		mainTextArea.setBounds(30, 31, 754, 449);
		contentPane.add(mainTextArea);
		
		sendTextArea = new JTextArea();
		sendTextArea.setForeground(Color.GREEN);
		sendTextArea.setDisabledTextColor(Color.GREEN);
		sendTextArea.setBackground(contentColour);
		sendTextArea.setBounds(30, 512, 431, 22);
		contentPane.add(sendTextArea);
		
		sendTextArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_CONTROL){
					ctrlDown = false;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(ctrlDown){
						sendTextArea.append("\n");
						System.out.println("added new line");
					}else{
						sendButton.doClick();
					}
				}else if(e.getKeyCode()==KeyEvent.VK_CONTROL){
					ctrlDown = true;
				}
				
			}
		});
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String messageText = dateFormat.format(cal.getTime()) + " " + Reference.username + ": " + sendTextArea.getText();
				mainTextArea.append(messageText + "\n");
				sendTextArea.setText("");
				
			}
		});
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
