package fr.darkxell.LoggingServer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogFileWriter {

	public static void log(String line) {
		try (PrintWriter out = new PrintWriter("gametunut.log")) { //FIXME: make the file not override everything
		    out.println(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
