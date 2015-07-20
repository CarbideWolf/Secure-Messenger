package com.carbidewolf.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TODO Annotate Class
 *
 * @author Richousrick
 *
 */
public class BufferedWriterHelper {
	
	public BufferedWriter createPath(String path, Boolean append){
		try {
			return new BufferedWriter(new FileWriter(new File(path), append));
		} catch (IOException e) {
			System.out.println("Path " + path + " does not exist");
			e.printStackTrace();
		}
		return null;
	}
	
	public void addLine(BufferedWriter bw, String text){
		try {
			bw.write(text);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Buffered writer is invalid");
			e.printStackTrace();
		}
	}
	
	public void addLines(BufferedWriter bw, String[] text){
		for(String s : text){
			addLine(bw, s);
		}
	}
	
	public void addLine(String path, boolean append, String text){
		BufferedWriter bw = createPath(path, append);
		try {
			bw.write(text);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println("Buffered writer is invalid");
			e.printStackTrace();
		}
	}
	
	public void createFile(String path, String[] text){
		try {
			BufferedWriter bw = createPath(path, false);
			addLines(bw, text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
