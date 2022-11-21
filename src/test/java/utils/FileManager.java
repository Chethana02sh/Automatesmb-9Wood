package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileManager {
	
	 private static FileManager fileManager;
	    private static Properties properties;

	    private FileManager(){}

	    public static FileManager getInstance(String file) {
	        try {
	            if (fileManager == null) {
	                FileInputStream fileInput = new FileInputStream(file);
	                properties = new Properties();
	                properties.load(fileInput);
	                fileManager = new FileManager();
	            }
	        }catch (FileNotFoundException e){
	            System.out.println("Invalid File path: "+file);
	        }catch (IOException e){
	            e.printStackTrace();
	        }
	        return fileManager;
	    }

	    public String getPropertyValue(String key){
	        return properties.getProperty(key);
	    }

}
