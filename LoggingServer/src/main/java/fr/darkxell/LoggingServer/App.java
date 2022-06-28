package fr.darkxell.LoggingServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class App {

	public static void main(String[] args) {
		System.out.println("Gametunut server launched at t=" + System.currentTimeMillis());
		// Setup the server properties
		ApplicationProperties.instance = new ApplicationProperties(args.length >= 1 ? args[0] : null);
		// Create the server endpoint
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(ApplicationProperties.instance.getPort()), 0);
			server.createContext("/Home", new PingRequestHandler());
			server.createContext("/PostData", new LoggingRequestHandler());
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			System.err.println();
			e.printStackTrace();
		}

	}

}
