package com.crm.autodesk.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * 
 * @author Punith
 *
 */
public class FileUtility {
/**
 * It is Used to read the data from CommonData.Properties File based on Key which you Pass as an argument
 * @param key
 * @return 
 * @throws Throwable 
 * @throws Throwable
 */
	public String getPropertyKeyValue(String key) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./data/commondata.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String value=pobj.getProperty(key);
		return value;
	}
}
