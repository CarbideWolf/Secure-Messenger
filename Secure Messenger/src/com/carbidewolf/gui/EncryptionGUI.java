package com.carbidewolf.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.reference.Reference;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

/**
 * The menu that displays, sends and receives messages
 * @author Richousrick 
 */
public class EncryptionGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	/** Format of the date used in messages*/
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	/** a boolean wither the control button is being held down */
	boolean ctrlDown = false;
	/** Text area Containing the text to be sent */
	private final JTextArea sendTextArea;
	/** Button that sends the text in the senddTextArea */
	private JButton sendButton;
	/** Text area that contains all the Messages */
	public JTextArea mainTextArea;
	
	/**
	 * Creates the menu
	 * @param x coordinate of the menu
	 * @param y coordinate of the menu
	 */
	public EncryptionGUI(int x, int y)
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 830, 593);
		JPanel contentPane = new JPanel();
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
		
		// Makes control enter make new line and enter send the text
		String TEXT_SUBMIT = "text-submit";
		String INSERT_BREAK = "insert-break";
		InputMap input = sendTextArea.getInputMap();
	    KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
	    KeyStroke shiftEnter = KeyStroke.getKeyStroke("control ENTER");
	    input.put(shiftEnter, INSERT_BREAK);  
	    input.put(enter, TEXT_SUBMIT);

	    ActionMap actions = sendTextArea.getActionMap();
	    actions.put(TEXT_SUBMIT, new AbstractAction() {
			private static final long serialVersionUID = 5154866482487106142L;

			@Override
	        public void actionPerformed(ActionEvent e) {
				sendButton.doClick();
	        }
	    });
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validText()){
					if(!checkCommand()){
						addText(Reference.username + ": " + sendTextArea.getText(), false);
						Reference.outStream.println(sendTextArea.getText());
					}
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
		
		JButton optionsButton = new JButton("Options");
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
	
	/** 
	 * Validates the text to be sent 
	 * @return If the validation is passed
	 */
	public boolean validText(){
		String noSpaces = sendTextArea.getText().replace(" ", "");
		if(noSpaces.startsWith("\n") || noSpaces.endsWith("\n") || noSpaces.length()==0){
			return false;
		}
	return true;
	}
	
	/**
	 * Adds text to a new line on the 
	 * @param text
	 * @param message
	 */
	public void addText(String text, boolean message){
		Calendar cal = Calendar.getInstance();
		String messageText;
		if(message){
			messageText = dateFormat.format(cal.getTime()) + " " + Reference.otherUName + ": " + text;
		}else{
			messageText = dateFormat.format(cal.getTime()) + " " + text;
		}
		
		mainTextArea.append(messageText + "\n");
	}
	
	/**
	 * Checks if the text sent is a command
	 * @return if the text was a command
	 */
	public boolean checkCommand(){
		String[] values = sendTextArea.getText().trim().split(" ");
		if(values.length>0){
			if(values[0].startsWith("\\")){
				if(values[0].equals("\\clear")){
					mainTextArea.setText("");
					
					return true;
				}
			}
		}
		return false;
	}
}
