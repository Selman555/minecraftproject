package be.pxl.minecraftguide;

import android.app.Service;
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
	private static boolean running = false;
    MediaPlayer player;
    
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
    	//________________BRON(MediaPlayer Service): http://developer.android.com/guide/topics/media/mediaplayer.html
    	
    	if (!running) {
	    	player = MediaPlayer.create(BackgroundMusicPlayer.this, R.raw.background);
	        player.setLooping(true); // herhaaldelijk afspelen
	        player.setVolume(100, 100); // links & rechts volume (stereo)
	        player.start();
	        running = true;
    	}
		return super.onStartCommand(intent, flags, startId);
	}
    
	@Override
    public void onDestroy() {
        player.stop();
        player.release();
        running = false;
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