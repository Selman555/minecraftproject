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
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import be.pxl.minecraftguide.R;
import android.os.AsyncTask;
import android.widget.EditText;

public class BackgroundPut extends AsyncTask<String, Boolean, Void>  {
	String errorMessage;
	
	@Override
	protected Void doInBackground(String... arg0) {
		// PUT Request
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut("http://192.168.0.233:8080/MinecraftRestServer/webresources/putChat");
	
		try
		{
			StringEntity se = new StringEntity(arg0[0]);
			httpPut.setEntity(se);
			httpPut.setHeader("Accept", "application/String");
			httpPut.setHeader("Content-type", "application/String");
			
			ResponseHandler responseHandler = new BasicResponseHandler();
			HttpResponse response = httpclient.execute(httpPut, responseHandler);
		}
		catch(Exception e)
		{
			errorMessage = "Could not write to the internet. Are you connected?";
		}
		return null;
	}
}
