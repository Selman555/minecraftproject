package be.pxl.minecraftguide;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import be.pxl.minecraftguide.events.SensorActivity;
import be.pxl.minecraftguide.providers.VideoProvider;

public class Videos extends ListActivity {
	private SimpleCursorAdapter adaptor;
	private SensorManager sensorManager;
	private Sensor acceleroMeter;
	private SensorEventListener sensorListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			setContentView(R.layout.listview);
		else
			setContentView(R.layout.listview_landscape);

		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(VideoProvider.CONTENT_URI, null, null, null,
				null);
		String[] from = { VideoProvider.COL_VIDID, VideoProvider.COL_VIDIMG,
				VideoProvider.COL_VIDDESC, VideoProvider.COL_VIDURL };
		int[] to = { R.id.txtID, R.id.imgItem, R.id.txtDescription, R.id.txtURL };
		adaptor = new SimpleCursorAdapter(getApplicationContext(),
				R.layout.rowview_videos, cursor, from, to, 0);
		sensorListener = new SensorActivity(getListView(), adaptor);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(sensorListener, acceleroMeter, SensorManager.SENSOR_DELAY_UI);
		
		setListAdapter(adaptor);
		
		getListView().setOnItemClickListener(new OnItemClickListener(){
        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       
        			//Toast.makeText(getApplicationContext(), "dit is een test", Toast.LENGTH_LONG).show();
        			
	        		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
	        		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or false
	        		if (isInternetPresent)
	        		{
	        			TextView txtURL = (TextView)view.findViewById(R.id.txtURL);
	        			String videoURL = txtURL.getText().toString(); 
	                    
	                    Intent videoClient = new Intent(Intent.ACTION_VIEW);
	                    videoClient.setData(Uri.parse(videoURL));
	                    videoClient.setClassName("com.google.android.youtube", "com.google.android.youtube.PlayerActivity");
	                    try{
	                        startActivity(videoClient);
	                    }catch(ActivityNotFoundException excp){
	                        try{
	                            videoClient.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
	                             startActivity(videoClient);
	                        }catch(ActivityNotFoundException exc){
	                            exc.printStackTrace();
	                        }
	                    }
	        		
	        		}
	        		else
	        		{
	        			showAlertDialog(Videos.this, "No Internet Connection",
	                            "You don't have internet connection.", false);
	        		}
                }
		});
	}

	@Override
	protected void onPause() {
		sensorManager.unregisterListener(sensorListener);
		super.onPause();
	}

	@Override
	protected void onResume() {
		sensorManager.registerListener(sensorListener, acceleroMeter,
				SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		// geluid misschien afspelen ?
	}
	
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        // Setting alert dialog icon
        alertDialog.setIcon(R.drawable.fail);
 
        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }

}
