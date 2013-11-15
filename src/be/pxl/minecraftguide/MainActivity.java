package be.pxl.minecraftguide;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import be.pxl.minecraftguide.providers.RecipeProvider;

public class MainActivity extends Activity {
	private MediaPlayer mp;
	public Intent backgroundMusicPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			setContentView(R.layout.activity_main);
		else
			setContentView(R.layout.activity_main_landscape);
		//_________BRON(MediaPlayer): http://stackoverflow.com/questions/3369068/android-play-sound-on-button-click-null-pointer-exception
		backgroundMusicPlayer = new Intent(MainActivity.this, BackgroundMusicPlayer.class);
		startService(backgroundMusicPlayer);
		
		mp = MediaPlayer.create(MainActivity.this, R.raw.buttonclick); //knopgeluiden laden
		super.onCreate(savedInstanceState);
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		pref.edit().putBoolean("up-to-date", true);
		
		if(pref.getBoolean("up-to-date", true)){
			Toast.makeText(getApplicationContext(), "dit is een test", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(backgroundMusicPlayer);
	}

	public void craftingButtonClicked(View view) {
        mp.start(); //Knopgeluid = kist openen in minecraft
        
		Intent crafting = new Intent(getApplicationContext(), Crafting.class);
        MainActivity.this.startActivity(crafting);
        if (RecipeProvider.errorMessage == null)
        	RecipeProvider.GetItems();
	}
	
	public void commandsButtonClicked(View view) {
		mp.start(); //Knopgeluid = kist openen in minecraft
		
		Intent commands = new Intent(getApplicationContext(), Commands.class);
		MainActivity.this.startActivity(commands);
	}
	
	public void videosButtonClicked(View view) {
		mp.start(); //Knopgeluid = kist openen in minecraft
		
		Intent videos = new Intent(getApplicationContext(), Videos.class);
        MainActivity.this.startActivity(videos);
	}
}
