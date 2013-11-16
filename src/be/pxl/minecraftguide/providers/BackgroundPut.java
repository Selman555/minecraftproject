package be.pxl.minecraftguide.providers;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class BackgroundPut extends AsyncTask<String, Boolean, Void>  {
	public static String errorMessage;
	private DefaultHttpClient httpclient;
	private HttpPut httpPut;
	
	public BackgroundPut() {
		// PUT Request
		httpclient = new DefaultHttpClient();
		httpPut = new HttpPut("http://192.168.0.233:8080/MinecraftRestServer/webresources/ChatResource");
	}
	
	@Override
	protected Void doInBackground(String... arg0) {

		try
		{
			StringEntity se = new StringEntity(arg0[0]);
			httpPut.setEntity(se);
			httpPut.setHeader("Accept", "application/string");
			httpPut.setHeader("Content-type", "application/string");
			
			ResponseHandler responseHandler = new BasicResponseHandler();
			HttpResponse response = httpclient.execute(httpPut, responseHandler);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != 200) {
				errorMessage = "Oops, where did your request go?";
			}
		}
		catch(Exception e)
		{
			errorMessage = "Could not write to the internet. Are you connected?";
		}
		return null;
	}
}
