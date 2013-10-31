package be.pxl.minecraftguide;

import java.util.List;

import be.pxl.minecraftguide.providers.RecipeProvider;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private MediaPlayer mp;
	public static Intent backgroundMusicPlayer;
	public static Activity currentlyVisible;
	
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
	}

	@Override
	protected void onPause() {
		ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		// get the info from the currently running task
		List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);
		ComponentName componentInfo = taskInfo.get(0).topActivity;
		if (componentInfo.getPackageName().equals(this.getPackageName()))
			stopService(MainActivity.backgroundMusicPlayer);
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
