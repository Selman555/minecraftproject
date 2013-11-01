package be.pxl.minecraftguide;

import java.util.List;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

/***
 * Deze class is een subtype van Service.
 * Ze speelt een achtergrondmuziekje af tijdens de ganse applicatie
 * 
 * @author Robbie
 *
 */
public class BackgroundMusicPlayer extends Service {
    MediaPlayer player;
    public static boolean running = false;
    private ActivityManager am;
	private List< ActivityManager.RunningTaskInfo > taskInfo;
	private ComponentName componentInfo;
    
    @Override
    public void onCreate() {
        super.onCreate();
        am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        taskInfo = am.getRunningTasks(1); // Informatie ophalen over de laatste active taak op het apparaat.
        componentInfo = taskInfo.get(0).topActivity;
    }

    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
    	//________________BRON(MediaPlayer Service): http://developer.android.com/guide/topics/media/mediaplayer.html
    	
    	player = MediaPlayer.create(BackgroundMusicPlayer.this, R.raw.background);
        player.setLooping(true); // herhaaldelijk afspelen
        player.setVolume(100, 100); // links & rechts volume (stereo)
	    running = true;
	    checkActivity();
		return super.onStartCommand(intent, flags, startId);
	}
    
    /***
     * Controleert om de 3 seconden of de huidige activiteit nog tot deze klas behoort,
     * Anders stop de controle en stop de muziek.
     */
    public void checkActivity() {
    	final Runnable run = new Runnable() {

			@Override
			public void run() {
				Thread checking = new Thread(new Runnable() {

					@Override
					public void run() {
						boolean playing = false;
						while (true) {
							taskInfo = am.getRunningTasks(1);
							componentInfo = taskInfo.get(0).topActivity;
							String packageName = componentInfo.getPackageName();
					    	if (packageName.contains("be.pxl.minecraftguide") && playing == false) {
					    		player.start();
					    		playing = true;
					    	} else if (!packageName.contains("be.pxl.minecraftguide") && playing == true) {
					    		player.pause();
					    		playing = false;
					    	}
					    	try {
					    		Thread.currentThread();
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								(Toast.makeText(getApplicationContext(), "Unable to keep background running", Toast.LENGTH_SHORT)).show();
							}
						}
					}
					
				});
				checking.start();
				
			}
    		
    	};
    	run.run();
    }
    
	@Override
    public void onDestroy() {
        player.stop();
        player.release();
        stopSelf();
    }

    @Override
    public void onLowMemory() {
    	player.stop();
    	player.release();
    	Toast notice = Toast.makeText(getApplicationContext(), "Low memory,  background music stopped", Toast.LENGTH_SHORT);
    	notice.show();
    }
    
    public void pausePlayer() {
    	player.pause();
    }
    public void resumePlayer() {
    	player.start();
    }
    
    public IBinder onBind(Intent arg0) {

        return null;
    }
    public IBinder onUnBind(Intent arg0) {

        return null;
    }
}