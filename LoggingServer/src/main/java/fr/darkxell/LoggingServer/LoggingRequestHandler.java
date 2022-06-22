package fr.darkxell.LoggingServer;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoggingRequestHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		String response = "Response text";
		t.sendResponseHeaders(200, response.length()); // 200 OK http
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
