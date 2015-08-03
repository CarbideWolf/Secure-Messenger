/**
 * @author Richousrick
 *
 */
package com.carbidewolf.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import com.carbidewolf.reference.Reference;

public class Client {
	
	public Socket socket;
	public Client(String ip) throws InterruptedException {
		try {
			socket = new Socket(ip, Reference.port);
			if(socket.isConnected()){
				Reference.connected = true;
				Reference.in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Reference.outStream = new PrintStream(socket.getOutputStream());
				//TODO encrypt username
				Reference.outStream.println(Reference.username);
				Reference.outStream.flush();
				String lastMessageRecieved = "";
				String lastMessageSent = "";
				
				TimeUnit.MILLISECONDS.sleep(2000);
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
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			Reference.canConnect=false;
		}
	}
	
	public static boolean validIp(String ip){
		try{
			if(ip == null){
				return false;
			}
			if(ip.isEmpty()){
				return false;
			}
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
//			if(!InetAddress.getByName(ip).isReachable(10000)){
//				System.out.println("cant reach");
//				return false;
//			}
			return true;
		}catch(NumberFormatException e){
			e.printStackTrace();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}
		return false;
	}
	
	

}
