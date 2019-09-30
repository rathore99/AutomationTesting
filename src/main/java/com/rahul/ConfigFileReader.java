package com.rahul;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ConfigFileReader {
	private Properties properties;
	private final String configFilePath= "configs//Configuration.properties";
	
	public ConfigFileReader(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(configFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public  String getDriverPath() {
		 String driverPath = properties.getProperty("driverPath");
		 if(driverPath!= null) return driverPath;
		 else throw new RuntimeException("driverPath not found"); 
	}
	
	public String getFilePath() {
		String filePath =  properties.getProperty("filePath");
		if (filePath != null)
		return filePath;
		else {
			throw new RuntimeException("filePath not found");
		}		
	}
	
	public long getImplicitWait() {
		String implicitWait = properties.getProperty("implicitWait");
		if(implicitWait!=null)
			return Long.parseLong(implicitWait);
		else {
			throw new RuntimeException("value not found");
		}
	}
	
	public String getURL() {
		String webURL = properties.getProperty("url");
		if(webURL!=null)
			return webURL;
		else {
			throw new RuntimeException("URL not found");
		}
	}

}
