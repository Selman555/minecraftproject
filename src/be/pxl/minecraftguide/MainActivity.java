package be.pxl.minecraftguide;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			setContentView(R.layout.activity_main);
		else
			setContentView(R.layout.activity_main_landscape);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void craftingButtonClicked(View view) {
		Intent crafting = new Intent(getApplicationContext(), Crafting.class);
        MainActivity.this.startActivity(crafting);
	}

}
