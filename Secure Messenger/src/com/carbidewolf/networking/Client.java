
package com.carbidewolf.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import com.carbidewolf.reference.Reference;
/**
 * This class runs the client side netcode
 * @author Richousrick
 */
public class Client {
	
	public Socket socket;
	public Client(String ip) throws InterruptedException {
		try {
			System.out.println("client console");
			socket = new Socket(ip, Reference.port);
			if(socket.isConnected()){
				Reference.connected = true;
				Reference.in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Reference.outStream = new PrintStream(socket.getOutputStream());
				//TODO encrypt username
				Reference.outStream.println(Reference.username);
				Reference.outStream.flush();
				String messageRecieved;
				TimeUnit.MILLISECONDS.sleep(200);
				Reference.otherUName = Reference.in.readLine();
				while(!socket.isClosed()){
					TimeUnit.MILLISECONDS.sleep(100);
					// Read
					if((messageRecieved = Reference.in.readLine()) != null
							&& !messageRecieved.equals("") && !messageRecieved.equals("\n")){
						System.out.println(messageRecieved);
						Reference.mainFrame.addText(messageRecieved, true);
					}
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			Reference.canConnect=false;
		}
	}

	/**
	 * Validates the string against criteria to validate the ip
	 * @param ip to check
	 * @return If the validation is passed
	 */
	public static boolean validIp(String ip){
		try{
			if(ip == null){
				return false;
			}
			if(ip.isEmpty()){
				return false;
			}
			// Incase someone tries to connect to a server hosted on the same device
			if(ip.equals("localhost")){
				return true;
			}
			// Checks the ip is in the format I.I.I.I where I is a int from 0 to 255
			String[] section = ip.split("\\.");
			if(section.length !=4){
				return false;
			}
			for(String s : section){
				int i = Integer.parseInt(s);
				if(i<0 || i>255){
					return false;
				}
			}
			return true;
		}catch(NumberFormatException e){
			System.out.println("The Ip " + ip + " is not in the Format I.I.I.I where I is a int from 0 to 255");
			e.printStackTrace();
		}
		return false;
	}
	
	

}
