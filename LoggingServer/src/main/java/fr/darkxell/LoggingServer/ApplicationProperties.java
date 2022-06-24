package fr.darkxell.LoggingServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ApplicationProperties {

	public static ApplicationProperties instance;

	protected HashMap<String, String> properties = new HashMap<>(10);

	public ApplicationProperties(String path) {
		InputStream is = null;
		// Setup inputstream to either a parsed properties file, or default locale
		// gametunut.properties
		if (path != null && !path.equals(""))
			try {
				is = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				System.out.println("Properties file could not be found at path : " + path
						+ "\nThis may be normal if you wish to look for a gametunut.properties fallback in the root folder."
						+ "\nOtherwise, use java -jar filename.jar path/to/file.properties to use a specific properties file.");
				e.printStackTrace();
			}
		if (is == null)
			try {
				is = new FileInputStream("gametunut.properties");
			} catch (FileNotFoundException e) {
				System.out.println("gametunut.properties could not be found locally." + "\nMake sure to either ");
				e.printStackTrace();
			}
		// Use inputstream to build a properties map.
		// If file cannot be found, properties map will remain empty.
		if (is == null) {
			System.err.println(
					"WARNING : no attempt succeeded at finding a properties file! Properties map will remain empty and will default to hardcoded values.");
		} else {
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					String[] propline = line.split("=");
					properties.put(propline[0], propline[1]);
				}
			} catch (Exception e) {
				System.err.println("Couldn't read properties file!\nmake sure to provide a clean properties file.");
			} finally {
				try {
					br.close();
					isr.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int getPort(){
		int toreturn = 8000;
		if(properties.containsKey("port"))
			try {
				toreturn = Integer.parseInt(properties.get("port"));
			} catch (Exception e) {
			}
		return toreturn;
	}

}
