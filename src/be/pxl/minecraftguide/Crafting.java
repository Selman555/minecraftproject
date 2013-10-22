package be.pxl.minecraftguide;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import be.pxl.minecraftguide.providers.RecipeCategoryProvider;;

public class Crafting extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.categorylist);
		
		setBackground();
		
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(RecipeCategoryProvider.CONTENT_URI, null, null, null, null);
		String[] from = {RecipeCategoryProvider.COL_CATID, RecipeCategoryProvider.COL_CATIMG, RecipeCategoryProvider.COL_CATDESC};
		int[] to = { R.id.txtCatID, R.id.imgCatImage, R.id.txtCatDescription };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.categoryrow, cursor, from, to, 0);
		/*SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {

			@Override
			public boolean setViewValue(View view, Cursor cursor, int arg2) {
				if(view.getId() == R.id.imgCatImage) {
					ImageView image = (ImageView)findViewById(R.id.imgCatImage);
					image.setImageResource(R.drawable.ic_launcher);
				}
				
				return false;
			}
			
		};*/
		
		setListAdapter(adapter);
		
		/*recipeListView.setOnClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View row,
                    int position, long id) {
        			
        			TextView txtId = (TextView)row.findViewById(R.id.txtRecipeID);
        			int recipeID = Integer.parseInt(txtId.getText().toString());
        			String description = ((TextView)row.findViewById(R.id.txtRecipeDescription)).getText().toString();
                    
        			
                    // Launching new Activity on selecting single List Item
                    // Intent craftingDetailIntent = new Intent(getApplicationContext(), craftingdetail.class);
                    // sending data to new activity
                    //craftingDetailIntent.putExtra("Category", item);
                    //startActivity(craftingDetailIntent); 
                }
        });*/
	}

	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		int recipeID = Integer.parseInt(v.getTag().toString());
	}*/
	
	@SuppressWarnings("deprecation")
	private void setBackground() {
		ListView categories = (ListView) findViewById(R.layout.categorylist);
		Drawable background = getResources().getDrawable(R.drawable.minecraft_portrait);
		//background.setAlpha(127);
		//categories.setBackgroundDrawable(background); //Enkel depricated in apis < 16 (jelly beans)
	}
	

}
