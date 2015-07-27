/**
 * @author Richousrick
 *
 */
package com.carbidewolf.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
	
	public Client(String ip) {
		
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
			if(!InetAddress.getByName(ip).isReachable(10000)){
				System.out.println("cant reach");
				return false;
			}
			return true;
		}catch(NumberFormatException e){
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean serverUp(String ip){
		
		return true;
	}

}
