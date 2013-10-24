package be.pxl.minecraftguide;

import be.pxl.minecraftguide.events.SensorActivity;
import be.pxl.minecraftguide.providers.RecipeProvider;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.res.Configuration;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;

public class Recipes extends ListActivity {
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
		
		Bundle extras = getIntent().getExtras();
		String id = Integer.toString(extras.getInt("listIndex"));
		String[] categories = new String[1];
		categories[0] = id;
		
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(RecipeProvider.CONTENT_URI, categories, null, null, null);
		String[] from = {RecipeProvider.COL_RECID, RecipeProvider.COL_RECIMGID, RecipeProvider.COL_RECDESC};
		int[] to = { R.id.txtID, R.id.imgItem, R.id.txtDescription };
		adaptor = new SimpleCursorAdapter(getApplicationContext(), R.layout.rowview, cursor, from, to, 0);
		
		setListAdapter(adaptor);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorListener = new SensorActivity(getListView(), adaptor);
		sensorManager.registerListener(sensorListener, acceleroMeter, SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensorManager.unregisterListener(sensorListener); //Accelerometer stopzetten (blijft lopen in stand-by)
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensorManager.registerListener(sensorListener, acceleroMeter, SensorManager.SENSOR_DELAY_UI); //Accelerometer opnieuw linken aan deze lijst
	}

	
}
