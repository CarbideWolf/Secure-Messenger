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
import com.carbidewolf.io.VariableStore;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class OptionGUI extends JDialog
{

	private final JPanel contentPanel = new JPanel();

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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(validateInput()){
							saveOptions();
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
	
	private void saveOptions()
	{
		BufferedWriter bw = new BufferedWriterHelper().createPath(this.getClass().getClassLoader().getResource("").getPath()+"Options.txt", true);
		BufferedWriterHelper helper = new BufferedWriterHelper();
		
	}
}
