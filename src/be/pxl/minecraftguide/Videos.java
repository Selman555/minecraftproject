package be.pxl.minecraftguide;

import java.util.ArrayList;

import be.pxl.minecraftguide.model.Video;
import be.pxl.minecraftguide.providers.VideoProvider;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Videos extends Activity {

	private ListView listView1;
    private ArrayList<Video> m_command = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videos);
		
m_command = new ArrayList<Video>();
        
		Video temp = new Video(R.drawable.minecrafttrailer, "Minecraft Trailer", "http://www.youtube.com/watch?v=MmB9b5njVbA");
        m_command.add(temp);
        temp = new Video(R.drawable.firstshelter, "Your First Shelter in Minecraft ", "http://www.youtube.com/watch?v=ylVtj-1Ccgg");
        m_command.add(temp);
        temp = new Video(R.drawable.startinghouse, "Minecraft: Building a Starting House", "http://www.youtube.com/watch?v=qss4uy6C_g0&feature=fvwrel");
        m_command.add(temp);
        temp = new Video(R.drawable.surviving, "Minecraft Tutorials : E01 How to Survive your First Night", "http://www.youtube.com/watch?v=B36Ehzf2cxE");
        m_command.add(temp);
        temp = new Video(R.drawable.top5, "Top 5 Minecraft creations houses", "http://www.youtube.com/watch?v=kzQQOMCxTp4&feature=fvwrel");
        m_command.add(temp);

        
        VideoProvider provider = new VideoProvider(this, R.layout.rowview, m_command);
        
        listView1 = (ListView)findViewById(R.id.listView1);

        listView1.setAdapter(provider);
        
        
        listView1.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
       
        			//Toast.makeText(getApplicationContext(), "dit is een test", Toast.LENGTH_LONG).show();
        			
	        		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
	        		Boolean isInternetPresent = cd.isConnectingToInternet(); // true or false
	        		if (isInternetPresent)
	        		{
	                    String product = m_command.get(position).text.toString();
	                    
	                    Intent videoClient = new Intent(Intent.ACTION_VIEW);
	                    videoClient.setData(Uri.parse(product));
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
	
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
 
        // Setting alert dialog icon
        alertDialog.setIcon(R.drawable.fail);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }

}
