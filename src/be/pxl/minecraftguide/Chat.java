package be.pxl.minecraftguide;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import be.pxl.minecraftguide.providers.BackgroundGet;
import be.pxl.minecraftguide.providers.BackgroundPut;

public class Chat extends Activity {
	private static boolean run = true;
	private String chatText = "";
	private EditText txtChatSession, txtChatInsert;
	private Handler handler; //ontvangt berichten indien chat geupdate wordt.
	private Thread chatUpdater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		txtChatSession = (EditText) findViewById(R.id.txtChatSession);
		txtChatSession.setKeyListener(null);
		txtChatInsert = (EditText) findViewById(R.id.txtInsertChat);
		
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					StringBuilder builder = new StringBuilder();
					String[] lines = msg.obj.toString().split("New_Line");
					String chat = "";
					for (int counter = 0; counter < lines.length; counter++) {
						builder.append(lines[counter] + System.getProperty("line.separator"));
					}

					chat = builder.toString();
					txtChatSession.setText(chat);
				}
				super.handleMessage(msg);
			}
		};
		startChatThread();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	public void sendChatButtonClicked(View v) {
		new BackgroundPut().execute(new String[] { "["+android.os.Build.PRODUCT.toString()+"]  "
				+((EditText)findViewById(R.id.txtInsertChat)).getText().toString()
				+ "New_Line" });
		txtChatInsert.setText("");
	}
	
	public void startChatThread() {
		chatUpdater = new Thread(new Runnable() {
			@Override
			public void run() {
				while(run) {
					try {
						chatText = new BackgroundGet().execute().get();
						Message msg = handler.obtainMessage();
					    msg.what = 1;
					    msg.obj = chatText;
					    handler.sendMessage(msg);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						run = false;
						(Toast.makeText(getApplicationContext(), "Your connection was interrupted", Toast.LENGTH_LONG)).show();
					} catch (ExecutionException e) {
						run = false;
						(Toast.makeText(getApplicationContext(), "We could not process your request", Toast.LENGTH_LONG)).show();
					}
				}
			}
		});
		chatUpdater.start();
	}
	
	@Override
	public void onBackPressed() {
		run = false;
		super.onBackPressed();
	}

	@Override
	public void onPause() {
		run = false;
		super.onPause();
	}
}
