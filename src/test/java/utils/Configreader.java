package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configreader {
	  static Properties prop;
	  
	    static {
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
	            prop = new Properties();
	            prop.load(fis);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static String get(String key) {
	        return prop.getProperty(key);
	    }
	}


