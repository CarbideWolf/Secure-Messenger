/**
 *
 * @author Richousrick
 *
 */

package com.carbidewolf.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.carbidewolf.io.BufferedWriterHelper.WriterBase;

public class VariableStore
{
	BufferedWriterHelper bwh = new BufferedWriterHelper();
	
	private void deleteVar(WriterBase wb, String name) throws IOException {
		File file = new File(this.getClass().getClassLoader().getResource("").getPath()+"tmpOption.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		BufferedReader br = new BufferedReader(new FileReader(new File(wb.path)));
		String line;
		line = br.readLine();
		while(line != null)
		{
			if(line.startsWith("#")){
				bw.write(line);
				line = br.readLine();
			}else{
				if(line.length()>name.length()){
					if(!line.substring(0, name.length()).equals(name))
					{	
						bw.write(line);
					}
				}else{
					bw.write(line);
				}
				line = br.readLine();
			}
		}
		br.close();
		bw.close();
		Files.copy(file.toPath(), new FileOutputStream(new File(wb.path)));
		file.delete();
	}
	
	public void storeInt(WriterBase wb, int i,String name)
	{
		try {
			deleteVar(wb, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bwh.addLine(wb, name + ":" + i);
	}

	public void storeInt(String path, int i, String name)
	{
		bwh.addLine(path, true,name + ":" + i);
	}

	public int getInt(BufferedReader br, String name)
	{
		String line;
		try
		{
			line = br.readLine();
			while(line != null)
			{
				if(line.startsWith("#")){
					line = br.readLine();
				}else{
					if(line.length()>name.length()){
						if(line.substring(0, name.length()).equals(name))
						{	
							return Integer.parseInt(line.substring(name.length()+1));
						}
					}
					line = br.readLine();
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return -999;
	}
	
	public int getInt(String path, String name)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			int returnInt = getInt(br,"meaningOfLife");
			br.close();
			return returnInt;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return -999;
	}
	
	public void storeBoolean(WriterBase wb, boolean b,String name)
	{
		try {
			deleteVar(wb, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bwh.addLine(wb, name + ":" + b);
	}
	
	public void storeBoolean(String path, boolean b, String name)
	{
		bwh.addLine(path, true,name + ":" + b);
	}
	
	public boolean getBoolean(BufferedReader br, String name, Boolean Fallback)
	{
		String line;
		try
		{
			line = br.readLine();
			while(line != null)
			{
				if(line.startsWith("#")){
					line = br.readLine();
				}else{
					if(line.length()>name.length()){
						if(line.substring(0, name.length()).equals(name))
						{
							return Boolean.valueOf(line.substring(name.length()+1));
						}
					}
					else
					{
						line = br.readLine();
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return Fallback;
	}

	public boolean getBoolean(String path, String name, Boolean Fallback)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			boolean returnBool = getBoolean(br,name, Fallback);
			br.close();
			return returnBool;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return Fallback;
	}
	
	public void storeString(WriterBase wb, String s,String name)
	{
		try {
			deleteVar(wb, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bwh.addLine(wb, name + ":" + s);
	}
	
	public void storeString(String path, String s, String name)
	{
		bwh.addLine(path, true,name + ":" + s);
	}
	
	public String getString(BufferedReader br, String name)
	{
		String line;
		try
		{
			line = br.readLine();
			while(line != null)
			{
				if(line.startsWith("#")){
					line = br.readLine();
				}else{
					if(line.length()>name.length())
					{
						if(line.substring(0, name.length()).equals(name))
						{
							return line.substring(name.length()+1);
						}
					}
					else
					{
						line = br.readLine();
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String getString(String path, String name)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String returnString = getString(br,name);
			br.close();
			return returnString;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void storeDouble(WriterBase wb, double d,String name)
	{
		try {
			deleteVar(wb, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bwh.addLine(wb, name + ":" + d);
	}
	
	public void storeDouble(String path, double d, String name)
	{
		bwh.addLine(path, true,name + ":" + d);
	}
	
	public double getDouble(BufferedReader br, String name, double fallback)
	{
		String line;
		try
		{
			line = br.readLine();
			while(line != null)
			{
				if(line.startsWith("#")){
					line = br.readLine();
				}else{
					if(line.length()>name.length()){
						if(line.substring(0, name.length()).equals(name))
						{
							return Double.valueOf(line.substring(name.length()+1));
						}
					}					
					else
					{
						line = br.readLine();
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return fallback;
	}
	
	public double getDouble(String path, String name, double fallback)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			double returnDouble = getDouble(br,name, fallback);
			br.close();
			return returnDouble;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return fallback;
	}
}
