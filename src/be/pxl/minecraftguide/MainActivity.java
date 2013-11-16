package be.pxl.minecraftguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import be.pxl.minecraftguide.providers.RecipeProvider;

public class MainActivity extends Activity {
	private MediaPlayer mp;
	public Intent backgroundMusicPlayer;
	private Vibrator systemVibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			setContentView(R.layout.activity_main);
		else
			setContentView(R.layout.activity_main_landscape);
		//_________BRON(MediaPlayer): http://stackoverflow.com/questions/3369068/android-play-sound-on-button-click-null-pointer-exception
		backgroundMusicPlayer = new Intent(MainActivity.this, BackgroundMusicPlayer.class);
		startService(backgroundMusicPlayer);
		
		//Vibrator instantieren voor button clicks
		systemVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		mp = MediaPlayer.create(MainActivity.this, R.raw.buttonclick); //knopgeluiden laden
		super.onCreate(savedInstanceState);
		
		/*SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		pref.edit().putBoolean("up-to-date", true);
		
		if(pref.getBoolean("up-to-date", true)){
			Toast.makeText(getApplicationContext(), "dit is een test", Toast.LENGTH_LONG).show();
		}*/
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
        systemVibrator.vibrate(100);
        
        if (RecipeProvider.isListNull())
        	RecipeProvider.GetItems();
		Intent crafting = new Intent(getApplicationContext(), Crafting.class);
        MainActivity.this.startActivity(crafting);
	}
	
	public void commandsButtonClicked(View view) {
		mp.start(); //Knopgeluid = kist openen in minecraft
        systemVibrator.vibrate(100);
		
		Intent commands = new Intent(getApplicationContext(), Commands.class);
		MainActivity.this.startActivity(commands);
	}
	
	public void videosButtonClicked(View view) {
		mp.start(); //Knopgeluid = kist openen in minecraft
        systemVibrator.vibrate(100);
		
		Intent videos = new Intent(getApplicationContext(), Videos.class);
        MainActivity.this.startActivity(videos);
	}
	
	public void chatButtonClicked(View view) {
		mp.start();
		Vibrator systemVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        systemVibrator.vibrate(100);
		
		Intent chat = new Intent(getApplicationContext(), Chat.class);
		MainActivity.this.startActivity(chat);
	}
}
