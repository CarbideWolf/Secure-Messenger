/**
 * @author Richousrick
 *
 */
package com.carbidewolf.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.carbidewolf.gui.StartupGUI;
import com.carbidewolf.reference.Reference;

import sun.rmi.runtime.NewThreadAction;

public class Common {
	
	public static void hostServer() throws InterruptedException{
		Socket  socket = null;
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(Reference.port);
			try {
				URL whatismyip = new URL("http://checkip.amazonaws.com");
				BufferedReader in = new BufferedReader(new InputStreamReader(
				                whatismyip.openStream()));
	
				
				JOptionPane.showMessageDialog(Reference.startFrame, "Server sucessfully hosted at "+in.readLine());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			socket = serverSocket.accept();
			Reference.in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Reference.outStream = new PrintStream(socket.getOutputStream());
			//TODO encrypt username
			Reference.outStream.println(Reference.username);
			Reference.outStream.flush();
			System.out.println("sent username is "+Reference.username);
			String lastMessageRecieved = "";
			String lastMessageSent = "";
			Reference.otherUName = Reference.in.readLine();
			Reference.mainFrame.addMessage(Reference.otherUName+" joined");
			String messageRecieved;
			while(!socket.isClosed()){
				TimeUnit.MILLISECONDS.sleep(100);
				//read
				if((messageRecieved = Reference.in.readLine()) != null
						&& !messageRecieved.equals("") && !messageRecieved.equals("\n")){
					System.out.println(messageRecieved);
					Reference.mainFrame.addMessage(messageRecieved);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connection died, server shutting down");
			if(socket!= null){
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			System.exit(0);
		}
		
	}
}
