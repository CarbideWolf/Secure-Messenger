/**
 * 
 * @author Richousrick
 *
 */

package com.carbidewolf.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.reference.Reference;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
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
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	boolean ctrlDown = false;
	private JPanel contentPane;
	private final JTextArea sendTextArea;
	private JButton sendButton;
	private JButton optionsButton;
	public JTextArea mainTextArea;
	/**
	 * Create the frame.
	 */
	public EncryptionGUI(int x, int y)
	{
		setResizable(false);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 830, 593);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mainTextArea = new JTextArea("Connected to " + Reference.ip + " As " + Reference.username+"\n");
		mainTextArea.setForeground(Color.GREEN);
		mainTextArea.setEditable(false);
		mainTextArea.setDisabledTextColor(Color.GREEN);
		mainTextArea.setBackground(Reference.contentColour);
		mainTextArea.setSelectionColor(Color.BLACK);
		mainTextArea.setSelectedTextColor(Color.BLACK);
		mainTextArea.setBounds(30, 31, 754, 449);
		contentPane.add(mainTextArea);
		
		sendTextArea = new JTextArea();
		sendTextArea.setForeground(Color.GREEN);
		sendTextArea.setDisabledTextColor(Color.GREEN);
		sendTextArea.setBackground(Reference.contentColour);
		sendTextArea.setBounds(30, 512, 431, 23);
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
				if(validText()){
					String messageText = dateFormat.format(cal.getTime()) + " " + Reference.username + ": " + sendTextArea.getText();
					mainTextArea.append(messageText + "\n");
					Reference.outStream.println(sendTextArea.getText());
					sendTextArea.setText("");
				}
			}
		});
		sendButton.setBorderPainted(false);
		sendButton.setForeground(Color.GREEN);
		sendButton.setBackground(Reference.contentColour);
		sendButton.setBounds(471, 511, 95, 23);
		contentPane.add(sendButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options  = new String[]{"Quit", "Restart", "Cancel"};
				int option = JOptionPane.showOptionDialog(Reference.mainFrame, "Are you sure you want to quit?","Quit?",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
				if(option == 0){
					System.exit(0);
				}else if(option == 1){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Point pos = Reference.mainFrame.getLocationOnScreen();
								Reference.startFrame = new StartupGUI(pos.x, pos.y);
								Reference.startFrame.setVisible(true);
								Reference.mainFrame = null;
								dispose();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		quitButton.setBorderPainted(false);
		quitButton.setForeground(Color.GREEN);
		quitButton.setBackground(Reference.contentColour);
		quitButton.setBounds(689, 511, 95, 23);
		contentPane.add(quitButton);
		
		optionsButton = new JButton("Options");
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Point pos = Reference.mainFrame.getLocationOnScreen();
					OptionGUI dialog = new OptionGUI(pos.x, pos.y);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		optionsButton.setForeground(Color.GREEN);
		optionsButton.setBorderPainted(false);
		optionsButton.setBackground(new Color(39, 39, 39));
		optionsButton.setBounds(580, 511, 95, 23);
		contentPane.add(optionsButton);
	}
	
	public boolean validText(){
		if(sendTextArea.getText().length()>0 && !sendTextArea.getText().equals("") && !sendTextArea.getText().equals("\n")){
			return true;
		}
	return false;
	}
	
	public void addMessage(String text){
		String messageText = dateFormat.format(cal.getTime()) + " " + Reference.otherUName + ": " + text;
		mainTextArea.append(messageText + "\n");
	}
}
