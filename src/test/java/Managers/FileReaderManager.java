package Managers;

import dataProvider.ConfigFileReader;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;
	
	//private constructor
	private FileReaderManager() {
		
	}
	
	//reference to its own instance and returns that reference
	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}
	
	//returns the instance of ConfigFileReader(package: dataProvider)
	public static ConfigFileReader getConfigReader() {
		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}
}
