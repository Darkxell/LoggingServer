package fr.darkxell.LoggingServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class App {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Gametunut server launched at t=" + System.currentTimeMillis());
		// Setup the server properties
		
		// Create the server endpoint
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/PostData", new LoggingRequestHandler());
		server.setExecutor(null);
		server.start();
	}
	
}
