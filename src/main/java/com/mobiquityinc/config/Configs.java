package com.mobiquityinc.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import com.mobiquityinc.exception.APIException;

public class Configs implements IConfigs {
	public static Integer MAX_WEIGHT_OF_PACKAGE = 100;
	public static Integer MAX_WEIGHT_OF_ITEM = 100;
	public static Integer MAX_COST_OF_ITEM = 100;
	public static Integer NUMBER_OF_ITEMS_TO_PICK_FROM = 15;
	
	public static String FILE_NOT_FOUND = "FILE_NOT_FOUND";
	public static String EMPTY_ROW = "EMPTY_ROW";
	public static String INVALID_ROW_FORMAT = "INVALID_ROW_FORMAT";
	public static String EXCEED_WEIGHT_LIMIT = "EXCEED_WEIGHT_LIMIT";
	public static String EXCEED_COST_LIMIT = "EXCEED_COST_LIMIT";
	public static String MAX_WEIGHT_PACKAGE_LIMIT = "MAX_WEIGHT_PACKAGE_LIMIT";
	
    static Properties properties = new Properties();
	

	private static IConfigs instance;

	public static IConfigs getInstance() {
		if (instance == null) {
			instance = new Configs();
			try {
				properties.load(new FileReader("src/main/resources/msg.properties"));
			} catch (FileNotFoundException e) {
				throw new APIException("FileNotFoundException");
			} catch (IOException e) {
				throw new APIException("IOException");
			}
		}
		return instance;
	}

	private Configs(){};

	/* (non-Javadoc)
	 * @see com.mobiquityinc.config.IConfigs#getProprtyValue(java.lang.String, java.lang.Object)
	 */
	@Override
	public String getProprtyValue(String key, Object... args) {
		return MessageFormat.format(properties.getProperty(key), args);
	}
}
