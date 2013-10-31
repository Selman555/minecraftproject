package be.pxl.minecraftguide;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import be.pxl.minecraftguide.events.SensorActivity;
import be.pxl.minecraftguide.providers.RecipeCategoryProvider;

public class Crafting extends ListActivity {
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
		Cursor cursor = cr.query(RecipeCategoryProvider.CONTENT_URI, null, null, null, null);
		String[] from = {RecipeCategoryProvider.COL_CATID, RecipeCategoryProvider.COL_CATIMG, RecipeCategoryProvider.COL_CATDESC};
		int[] to = { R.id.txtID, R.id.imgItem, R.id.txtDescription };
		adaptor = new SimpleCursorAdapter(getApplicationContext(), R.layout.rowview, cursor, from, to, 0);
		sensorListener = new SensorActivity(getListView(), adaptor);
		
		setListAdapter(adaptor);
		
		//__________BRON: http://stackoverflow.com/questions/18751878/android-using-the-accelerometer-to-create-a-simple-maraca-app_____________
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(sensorListener, acceleroMeter, SensorManager.SENSOR_DELAY_UI);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View row, int rowIndex, long arg3) {				
				Intent recipeCategoryIntent = new Intent(getApplicationContext(), Recipes.class);
				TextView txtID = (TextView)row.findViewById(R.id.txtID);
				recipeCategoryIntent.putExtra("listIndex", Integer.parseInt(txtID.getText().toString()));
				startActivity(recipeCategoryIntent);
			}
			
		});
	}
	
	@Override
	protected void onPause() {
		sensorManager.unregisterListener(sensorListener); //De accelerometer afzetten (blijft aan zelfs in stand-by)
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensorManager.registerListener(sensorListener, acceleroMeter, SensorManager.SENSOR_DELAY_UI); //De accelerator opnieuw declareren voor deze lijst
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

}
