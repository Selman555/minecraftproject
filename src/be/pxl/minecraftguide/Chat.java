package be.pxl.minecraftguide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

import be.pxl.minecraftguide.model.Recipe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Chat extends Activity {
	private String errorMessage;
	private static boolean run = true;
	private static BufferedReader reader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	public void sendChatButtonClicked(View v) {
		
	}
	
	public void startChatThread() {
		// GET Request
		final HttpGet httpGet = new HttpGet("http://192.168.43.190:8080/MinecraftRestServer/webresources/Chat");
		HttpParams httpParameters = new BasicHttpParams();
		// Tijd in ms hoelang er gewacht wordt op verbinding met de webservice
		int timeoutConnection = 3000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		// Tijd in ms hoelang er na connectie op data gewacht moet worden.
		int timeoutSocket = 5000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		final HttpClient client = new DefaultHttpClient(httpParameters);
		
		(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
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
				
				EditText txtChatSession = (EditText)findViewById(R.id.txtChatSession);
				try {
					txtChatSession.setText(txtChatSession.getText() + reader.readLine().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		}).run();
			
	}
	
	public BufferedReader getChat() {
		
		
		return null;
	}
}
