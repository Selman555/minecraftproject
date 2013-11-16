package be.pxl.minecraftguide.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;

public class BackgroundGet extends AsyncTask<String, String, String>  {
	private String chatSession;
	// GET Request
	private HttpGet httpGet;
	private HttpParams httpParameters;
	// Tijd in ms hoelang er gewacht wordt op verbinding met de webservice
	private int timeoutConnection;
	// Tijd in ms hoelang er na connectie op data gewacht moet worden.
	private int timeoutSocket;
	private HttpClient client;
	
	public BackgroundGet() {
		// GET Request
		httpGet = new HttpGet("http://192.168.0.233:8080/MinecraftRestServer/webresources/ChatResource");
		httpParameters = new BasicHttpParams();
		// Tijd in ms hoelang er gewacht wordt op verbinding met de webservice
		timeoutConnection = 3000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		// Tijd in ms hoelang er na connectie op data gewacht moet worden.
		timeoutSocket = 5000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		client = new DefaultHttpClient(httpParameters);
	}
	
	@Override
	protected String doInBackground(String... arg0) {

		BufferedReader reader = null;
		try {
			// GET Request uitvoeren
			HttpResponse response = client.execute(httpGet);
			// Antwoord controlleren
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) { // 200 == OK
				// Response uitlezen
				HttpEntity entity = response.getEntity();
				// Inputstream om uit te lezen
				InputStream content = entity.getContent();
				// BufferedReader van de inputstream
				reader = new BufferedReader(new InputStreamReader(content));
			} else {
				return "The webservice could not answer your request";
			}
		} catch (ClientProtocolException cpex) {
			return "Could not load data at this moment";
		} catch (IOException ioex) {
			return "Could not connect to the webservice";
		} catch (IllegalArgumentException iae) {
			return "Some received data could not be processed";
		}
		
		if (reader != null) {
			StringBuilder builder = new StringBuilder();
			String chatLine = "";
			try {
				while ((chatLine = reader.readLine()) != null) {
				    builder.append(chatLine);
				}
				return builder.toString();
			} catch (IOException e) {
				return "Could not build chat session";
			}
		} else {
			return "Could not communicate with the chat session.";
		}
	}
	
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        chatSession = result;
    }

	public String getJsonResponse() {
		return chatSession;
	}
}
