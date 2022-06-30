package fr.darkxell.LoggingServer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

public class LogFileWriter implements Runnable {

	private static ArrayList<String> logStack = new ArrayList<>(50);

	private static LogFileWriter instance = null;

	public static int logcounter = 0;

	public synchronized static void log(String line) {
		logStack.add(line);
		logcounter++;
		if (instance == null) {
			instance = new LogFileWriter();
			Thread t = new Thread(instance);
			t.start();
		}
	}

	private void logToFile(String line) {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(generateFileName(), true))) {
			out.println(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private int machinerandomID = -1;

	private String generateFileName() {
		try {
			if (machinerandomID == -1)
				machinerandomID = new Random().nextInt(999);
			Date date = new Date(System.currentTimeMillis());
			return date.toString() + "-" + machinerandomID + "-gametunut.log";
		} catch (Exception e) {
			return "gametunut-default.log";
		}
	}

	@Override
	public void run() {
		for (;;) {
			try {
				while (logStack.size() >= 1) {
					String tolog = logStack.get(0);
					logToFile(tolog);
					logStack.remove(0);
				}
			} catch (Exception e) {
				System.err.println("Couldn't log " + logStack.size() + " lines, trying again in 1 second...");
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}

}
