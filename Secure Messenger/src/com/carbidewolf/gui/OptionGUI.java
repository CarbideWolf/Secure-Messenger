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

import com.carbidewolf.Core;
import com.carbidewolf.io.BufferedWriterHelper;
import com.carbidewolf.io.BufferedWriterHelper.WriterBase;
import com.carbidewolf.io.VariableStore;
import com.carbidewolf.reference.Reference;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class OptionGUI extends JDialog
{
	WriterBase wb = new BufferedWriterHelper().createPath(Core.optionFilePath, true);
	BufferedReader br = new BufferedWriterHelper().getPath(Core.optionFilePath);
	VariableStore optionFile = new VariableStore();

	private final JPanel contentPanel = new JPanel();
	private JTextField testVarTextField;

	
	
	public OptionGUI(int x, int y)
	{
		setModal(true);
		setBounds(x, y, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.DARK_GRAY);
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTestVar = new JLabel("Test Var");
		lblTestVar.setForeground(Color.GREEN);
		lblTestVar.setBounds(10, 11, 46, 14);
		contentPanel.add(lblTestVar);
		
		testVarTextField = new JTextField(optionFile.getString(br, "testVar"));
		testVarTextField.setBorder(null);
		testVarTextField.setForeground(Color.GREEN);
		testVarTextField.setBackground(Reference.contentColour);
		testVarTextField.setBounds(66, 8, 86, 20);
		contentPanel.add(testVarTextField);
		testVarTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setForeground(Color.DARK_GRAY);
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBorder(null);
				okButton.setPreferredSize(new Dimension(47, 23));
				okButton.setForeground(Color.GREEN);
				okButton.setBackground(Reference.contentColour);
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
				cancelButton.setBorder(null);
				cancelButton.setPreferredSize(new Dimension(65, 23));
				cancelButton.setForeground(Color.GREEN);
				cancelButton.setBackground(Reference.contentColour);
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
		optionFile.updateVar(wb, testVarTextField.getText(), "testVar");
		
		try {
			wb.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
