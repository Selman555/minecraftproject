package be.pxl.minecraftguide.events;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class SensorActivity implements SensorEventListener {
	private float[] history = new float[2];
	private ListView listView;
	private SimpleCursorAdapter adaptor;
	
	public SensorActivity(ListView listView, SimpleCursorAdapter adaptor) {
		this.listView = listView;
		this.adaptor = adaptor;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float xChange = history[0] - event.values[0];
	        float yChange = history[1] - event.values[1]; //Verschil tussen nieuwe en oude positie ophalen.
	        
	        history[0] = event.values[0];
	        history[1] = event.values[1]; //Nieuwe waarden bewaren voor volgende event trigger
	        
	        if (xChange > 8){
	            //Links
	        }
	        else if (xChange < -8){
	        	//Rechts
	        }

	        if (yChange > 12){
		        	listView.smoothScrollBy(listView.getHeight() * adaptor.getCount(), 1000);
		        	listView.postDelayed(new Runnable() {
		                @Override
		                public void run() {
		                	listView.smoothScrollBy(0, 0); //Geanimeerd scrollen naar laatste positie
		                	listView.setSelection(adaptor.getCount() - 1);
		                }
		            }, 500);
	        }
	        else if (yChange < -12){
	        	listView.smoothScrollBy(listView.getHeight() * adaptor.getCount(), 1000);
	        	listView.postDelayed(new Runnable() {
	                @Override
	                public void run() {
	                	listView.smoothScrollBy(0, 0); //Geanimeerd scrollen naar eerste positie positie
	                	listView.setSelection(0);
	                }
	            }, 500);
	        }
	    }
		
	}

}
