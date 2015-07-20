package com.carbidewolf.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO Annotate Class
 *
 * @author Richousrick
 *
 */
public class VariableStore {
	BufferedWriterHelper bwh = new BufferedWriterHelper();
	
	
	public void storeInt(BufferedWriter bw, int i,String name){
		bwh.addLine(bw, name + ":" + i);
	}
	
	public void storeInt(String path, int i, String name){
		bwh.addLine(path, true,name + ":" + i);
	}

	public int getInt(BufferedReader br, String name){
		String line;
		try {
			line = br.readLine();
			while(line != null){
				if(line.substring(0, name.length()).equals(name)){
					return Integer.parseInt(line.substring(name.length()+1));
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -999;
	}
	
	public int getInt(String path, String name){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			int returnInt = getInt(br,"meaningOfLife");
			br.close();
			return returnInt;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -999;
	}
	
	public void storeBoolean(BufferedWriter bw, boolean b,String name){
		bwh.addLine(bw, name + ":" + b);
	}
	
	public void storeBoolean(String path, boolean b, String name){
		bwh.addLine(path, true,name + ":" + b);
	}
	
	public boolean getBoolean(BufferedReader br, String name, Boolean Fallback){
		String line;
		try {
			line = br.readLine();
			while(line != null){
				if(line.substring(0, name.length()).equals(name)){
					return Boolean.valueOf(line.substring(name.length()+1));
				}else{
					line = br.readLine();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Fallback;
	}

	public boolean getBoolean(String path, String name, Boolean Fallback){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			boolean returnBool = getBoolean(br,name, Fallback);
			br.close();
			return returnBool;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Fallback;
	}
	
	public void storeString(BufferedWriter bw, String s,String name){
		bwh.addLine(bw, name + ":" + s);
	}
	
	public void storeString(String path, String s, String name){
		bwh.addLine(path, true,name + ":" + s);
	}
	
	public String getString(BufferedReader br, String name){
		String line;
		try {
			line = br.readLine();
			while(line != null){
				if(line.substring(0, name.length()).equals(name)){
					return line.substring(name.length()+1);
				}else{
					line = br.readLine();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getString(String path, String name){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String returnString = getString(br,name);
			br.close();
			return returnString;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void storeDouble(BufferedWriter bw, double d,String name){
		bwh.addLine(bw, name + ":" + d);
	}
	
	public void storeDouble(String path, double d, String name){
		bwh.addLine(path, true,name + ":" + d);
	}
	
	public double getDouble(BufferedReader br, String name, double fallback){
		String line;
		try {
			line = br.readLine();
			while(line != null){
				if(line.substring(0, name.length()).equals(name)){
					return Double.valueOf(line.substring(name.length()+1));
				}else{
					line = br.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fallback;
	}
	
	public double getDouble(String path, String name, double fallback){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			double returnDouble = getDouble(br,name, fallback);
			br.close();
			return returnDouble;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fallback;
	}
	
	

}
