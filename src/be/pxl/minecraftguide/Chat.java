package be.pxl.minecraftguide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import be.pxl.minecraftguide.model.Recipe;
import be.pxl.minecraftguide.providers.BackgroundGet;
import be.pxl.minecraftguide.providers.BackgroundPut;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Chat extends Activity {
	private String errorMessage;
	private static boolean run = true;
	private static BufferedReader reader;
	private EditText txtChatSession = (EditText) findViewById(R.id.txtChatSession);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startChatThread();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	public void sendChatButtonClicked(View v) {
		new BackgroundPut().execute(((EditText)findViewById(R.id.txtInsertChat)).getText().toString());
	}
	
	public void startChatThread() {
		(new Runnable() {
			
			@Override
			public void run() {
				while(run) {
					try {
						String chatText = new BackgroundGet().execute().get();
						txtChatSession.setText(chatText);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						run = false;
						(Toast.makeText(getApplicationContext(), "Your connection was interrupted", Toast.LENGTH_LONG)).show();
					} catch (ExecutionException e) {
						run = false;
						(Toast.makeText(getApplicationContext(), "We could not process your request", Toast.LENGTH_LONG)).show();
					}
				}
			}
		}).run();
	}
}
