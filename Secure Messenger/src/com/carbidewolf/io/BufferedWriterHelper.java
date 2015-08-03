/**
 *
 * @author Richousrick
 *
 */

package com.carbidewolf.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterHelper {
	
	public class WriterBase extends BufferedWriter{
		final String path;
		
		/**
		 * @param in
		 * @throws IOException 
		 */
		public WriterBase(String path_, boolean append) throws IOException {
			super(new FileWriter(new File(path_),true));
			path = path_;
		}
		
	}
	
	public class ReaderBase extends BufferedReader{
		final String path;
		
		/**
		 * @param in
		 * @throws IOException 
		 */
		public ReaderBase(String path_) throws IOException {
			super(new FileReader(new File(path_)));
			path = path_;
		}
		
	}
	
	public WriterBase createPath(String path, Boolean append)
	{
		try
		{
			return new WriterBase(path, append);
		}
		catch (IOException e)
		{
			System.out.println("Path " + path + " does not exist");
			e.printStackTrace();
		}
		return null;
	}
	
	public ReaderBase getPath(String path)
	{
		try
		{
			return new ReaderBase(path);
		}
		catch (IOException e)
		{
			System.out.println("Path " + path + " does not exist");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void addLine(BufferedWriter bw, String text)
	{
		try
		{
			bw.write(text);
			bw.newLine();
		}
		catch (IOException e)
		{
			System.out.println("Buffered writer is invalid");
			e.printStackTrace();
		}
	}
	
	public void addLines(BufferedWriter bw, String[] text)
	{
		for(String s : text)
		{
			addLine(bw, s);
		}
	}
	
	public void addLine(String path, boolean append, String text)
	{
		BufferedWriter bw = createPath(path, append);
		try
		{
			bw.write(text);
			bw.newLine();
			bw.close();
		}
		catch (IOException e)
		{
			System.out.println("Buffered writer is invalid");
			e.printStackTrace();
		}
	}
	
	public void createFile(String path, String[] text)
	{
		try
		{
			BufferedWriter bw = createPath(path, false);
			addLines(bw, text);
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
