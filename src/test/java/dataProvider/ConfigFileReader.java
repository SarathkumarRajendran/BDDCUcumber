package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath = "configs//Configuration.properties";
	
	//Function to read Properties file
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration file is not available in this path:" + propertyFilePath);
		}
		
	}
	
	//Function to read driverpath from Properties file
	public String getDriverPath() {
		String driverPath = properties.getProperty("driverPath");
		if(driverPath !=null) return driverPath;
		else throw new RuntimeException("driverpath is not specificed in the configuration file");
					
	}
	
	//Function to read application url from Properties file
		public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url !=null) return url;
		else throw new RuntimeException("url is not specificed in the configuration file");
					
	}
	
		//Function to read browser from Properties file
		public DriverType getBrowser() {
			String browserName = properties.getProperty("browser");
			if(browserName ==null ||browserName.equals("chrome")) return DriverType.CHROME;
			else if (browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
			else if (browserName.equalsIgnoreCase("iexplorer")) return DriverType.INTERNETEXPLORER;
			else throw new RuntimeException("browser name is not specificed in the configuration file");
						
		}
	
		//Function to read report path from Properties file
		public String getReportConfigPath() {
			String reportConfigpath = properties.getProperty("reportConfigpath");
			if(reportConfigpath !=null) return reportConfigpath;
			else throw new RuntimeException("reportConfigpath is not specificed in the configuration file");
						
		}
	
}
