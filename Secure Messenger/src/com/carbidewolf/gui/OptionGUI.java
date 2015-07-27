/**
 *
 * @author Richousrick
 *
 */

package com.carbidewolf.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.carbidewolf.io.BufferedWriterHelper;
import com.carbidewolf.io.BufferedWriterHelper.WriterBase;
import com.carbidewolf.io.VariableStore;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OptionGUI extends JDialog
{
	String optionFilePath = this.getClass().getClassLoader().getResource("").getPath()+"Options.txt";
	WriterBase wb = new BufferedWriterHelper().createPath(optionFilePath, true);
	BufferedReader br = new BufferedWriterHelper().getPath(optionFilePath);
	VariableStore optionFile = new VariableStore();

	private final JPanel contentPanel = new JPanel();
	private JTextField testVarTextField;

	public static void init()
	{
		try
		{
			OptionGUI dialog = new OptionGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public OptionGUI()
	{
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTestVar = new JLabel("Test Var");
		lblTestVar.setBounds(10, 11, 46, 14);
		contentPanel.add(lblTestVar);
		
		testVarTextField = new JTextField(optionFile.getString(br, "testVar"));
		testVarTextField.setBounds(66, 8, 86, 20);
		contentPanel.add(testVarTextField);
		testVarTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(validateInput()){
							storeOptions();
							dispose();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private boolean validateInput()
	{
		return true;
	}
	
	public void storeOptions() {
		optionFile.storeString(wb, testVarTextField.getText(), "testVar");
		
		try {
			wb.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
