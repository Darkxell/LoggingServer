package fr.darkxell.LoggingServer;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class PingRequestHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		String response = "<!Doctype html><html><head></head><body><h1>Gametunut server</h1><br>"
				+ "This server is up and running since " + App.startTimestamp + "<br>" + LogFileWriter.logcounter
				+ " payloads logged on this instance.</body></html>";
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
