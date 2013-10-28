package be.pxl.minecraftguide.providers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import be.pxl.minecraftguide.R;
import be.pxl.minecraftguide.model.Video;


public class VideoProvider extends ArrayAdapter<Video> {
	
	private ArrayList<Video> items;
    Context context; 
    int layoutResourceId;
    
    public VideoProvider(Context context, int layoutResourceId, ArrayList<Video> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CommandsHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new CommandsHolder();
            holder.imgItem = (ImageView)row.findViewById(R.id.imgItem);
            holder.txtID = (TextView)row.findViewById(R.id.txtID);
            
            row.setTag(holder);
        }
        else
        {
            holder = (CommandsHolder)row.getTag();
        }
        
        Video video = items.get(position);
        holder.txtID.setText(video.title);
        holder.imgItem.setImageResource(video.icon);
        
        return row;
    }
    
    static class CommandsHolder
    {
    	ImageView imgItem;
        TextView txtID;
    }
}
