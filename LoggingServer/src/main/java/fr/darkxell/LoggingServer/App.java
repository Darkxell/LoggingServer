package fr.darkxell.LoggingServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.sun.net.httpserver.HttpServer;

public class App {

	public static String startTimestamp = "Unknown time";

	public static void main(String[] args) {
		System.out.println("Gametunut server launched at t=" + System.currentTimeMillis());
		// Setup the server properties
		ApplicationProperties.instance = new ApplicationProperties(args.length >= 1 ? args[0] : null);
		// Create the server endpoint
		try {
			startTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
			HttpServer server = HttpServer.create(new InetSocketAddress(ApplicationProperties.instance.getPort()), 0);
			server.createContext("/Home", new PingRequestHandler());
			server.createContext("/PostData", new LoggingRequestHandler());
			server.setExecutor(null);
			server.start();
			LogFileWriter.log(System.currentTimeMillis() + " : Server (re)started");
		} catch (IOException e) {
			System.err.println();
			e.printStackTrace();
		}

	}

}
