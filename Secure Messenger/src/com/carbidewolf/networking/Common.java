
package com.carbidewolf.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import com.carbidewolf.reference.Reference;

/**
 * @author Richousrick
 * 
 *  This class runs the server side netcode
 */
public class Common {

	/**
	 * Create a server on this computer 
	 * @throws InterruptedException
	 */
	public Common() throws InterruptedException {
		Socket socket = null;
		ServerSocket serverSocket = null;
		try {
			// Bound the socket
			serverSocket = new ServerSocket(Reference.port);
			serverSocket.setReuseAddress(true);
			// Wait for a client to connect
			socket = serverSocket.accept();
			// Store input and output streams
			Reference.in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			Reference.outStream = new PrintStream(socket.getOutputStream());
			// Send username to the client
			// TODO encrypt username
			Reference.outStream.println(Reference.username);
			// Get username from the client and display it
			Reference.otherUName = Reference.in.readLine();
			Reference.mainFrame.addText(Reference.otherUName + " joined", false);
			String messageRecieved;
			// Read sent messages
			while (!socket.isClosed()) {
				TimeUnit.MILLISECONDS.sleep(100);
				// Read
				if ((messageRecieved = Reference.in.readLine()) != null
						&& !messageRecieved.equals("")
						&& !messageRecieved.equals("\n")) {
					Reference.mainFrame.addText(messageRecieved, true);
				}
			}
		} catch (SocketException e) {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			Reference.mainFrame.addText(Reference.otherUName + " Disconnected", false);
			new Common();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
