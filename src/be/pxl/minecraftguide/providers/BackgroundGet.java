package be.pxl.minecraftguide.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import android.os.AsyncTask;

public class BackgroundGet extends AsyncTask<String, String, String>  {
	String errorMessage;
	String chatSession;
	
	@Override
	protected String doInBackground(String... arg0) {
		// GET Request
		final HttpGet httpGet = new HttpGet("http://192.168.0.233:8080/MinecraftRestServer/webresources/Chat");
		HttpParams httpParameters = new BasicHttpParams();
		// Tijd in ms hoelang er gewacht wordt op verbinding met de webservice
		int timeoutConnection = 3000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		// Tijd in ms hoelang er na connectie op data gewacht moet worden.
		int timeoutSocket = 5000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		final HttpClient client = new DefaultHttpClient(httpParameters);

		BufferedReader reader = null;
		try {
			// GET Request uitvoeren
			HttpResponse response = client.execute(httpGet);
			// Antwoord controlleren
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) { // 200 == OK
				// Response uitlezen
				HttpEntity entity = response.getEntity();
				// Inputstream om uit te lezen
				InputStream content = entity.getContent();
				// BufferedReader van de inputstream
				reader = new BufferedReader(new InputStreamReader(content));
			} else {
				errorMessage = "The webservice could not answer your request";
			}
		} catch (ClientProtocolException cpex) {
			errorMessage = "Could not load data at this moment";
		} catch (IOException ioex) {
			errorMessage = "Could not connect to the webservice";
		} catch (IllegalArgumentException iae) {
			errorMessage = "Some received data could not be processed";
		}
		
		if (reader != null) {
			return reader.toString();
		} else {
			return "Welcome to MC Talk";
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
