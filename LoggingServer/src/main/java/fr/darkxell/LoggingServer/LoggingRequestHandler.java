package fr.darkxell.LoggingServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoggingRequestHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		String payloadrequest = getStringFromInputStream(t.getRequestBody());
		// No actual security here, we accept all requests.
		// HOWEVER, this culls scraping bots since we do not want automated content to
		// write arbitrary data on our server
		// Do note that printed content is not sanitized and should in NO WAY be
		// executed.
		boolean acceptrequest = payloadrequest.contains("gametunut-client");

		String response;
		if (acceptrequest) {
			response = "HTTP 200 : Ok";
			t.sendResponseHeaders(200, response.length());
			LogFileWriter.log(System.currentTimeMillis() + " : " + payloadrequest);
		} else {
			response = "HTTP 403 : Forbidden";
			t.sendResponseHeaders(200, response.length());
		}
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	// https://stackoverflow.com/questions/54066429/how-can-i-handle-form-data-in-a-java-httpserver-server
	private static String getStringFromInputStream(java.io.InputStream inputStream) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
