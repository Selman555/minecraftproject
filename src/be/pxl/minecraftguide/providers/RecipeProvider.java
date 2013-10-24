package be.pxl.minecraftguide.providers;

import java.util.ArrayList;
import java.util.List;

import be.pxl.minecraftguide.R;
import be.pxl.minecraftguide.model.Recipe;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;

public class RecipeProvider extends ContentProvider {
	public static final String COL_RECID = "_ID";
	public static final String COL_RECCATID = "recipeCategory";
	public static final String COL_RECIMGID	= "recipeImageID";
	public static final String COL_RECDESC = "recipeDescription";
	public static final String COL_RECLOC = "recipeLocations";
	
	public static final String[] columnNames = {COL_RECID, COL_RECCATID, COL_RECIMGID, COL_RECDESC, COL_RECLOC };
	
	public static final String AUTHORITY = "be.pxl.minecraftguide.providers.recipeprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/*");
	private List<Recipe> recipesList;
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean onCreate() {
		recipesList = new ArrayList<Recipe>();
        recipesList.add(new Recipe(1, 2, R.drawable.diamond_boots, "Boots (Diamond)",
        		new int[] { 0,0,0,R.drawable.diamond_ingot,0,R.drawable.diamond_ingot,R.drawable.diamond_ingot,0,R.drawable.diamond_ingot }));
        recipesList.add(new Recipe(2, 2, R.drawable.gold_boots, "Boots (Gold)",
        		new int[] { 0,0,0,R.drawable.gold_ingot,0,R.drawable.gold_ingot,R.drawable.gold_ingot,0,R.drawable.gold_ingot } ));
        recipesList.add(new Recipe(3, 1, R.drawable.iron_boots, "Boots (Iron)",
        		new int[] { 0,0,0,R.drawable.iron_ingot,0,R.drawable.iron_ingot,R.drawable.iron_ingot,0,R.drawable.iron_ingot } ));
		return true;
	}
	
	@Override
	public Cursor query(Uri arg0, String[] categories, String recipeID, String[] arg3,
			String arg4) {
		MatrixCursor mxCur = new MatrixCursor(columnNames);
		
		if (categories != null) {
			for (Recipe rcp : recipesList) {
				RowBuilder rb = mxCur.newRow();
				if (rcp.getRecipeCategory() == Integer.parseInt(categories[0])) {
					rb.add(rcp.getRecipeID());
					rb.add(rcp.getRecipeCategory());
					rb.add(rcp.getRecipeImageID());
					rb.add(rcp.getRecipeDescription());
					rb.add(rcp.getRecipeLocations());
				}
			}
		} else if (recipeID != null) {
			for (Recipe rcp : recipesList) {
				RowBuilder rb = mxCur.newRow();
				if (rcp.getRecipeID() == Integer.parseInt(recipeID)) {
					rb.add(rcp.getRecipeID());
					rb.add(rcp.getRecipeCategory());
					rb.add(rcp.getRecipeImageID());
					rb.add(rcp.getRecipeDescription());
					rb.add(rcp.getRecipeLocations());
				}
			}
		} else {
			for (Recipe rcp : recipesList) {
				RowBuilder rb = mxCur.newRow();
				rb.add(rcp.getRecipeID());
				rb.add(rcp.getRecipeCategory());
				rb.add(rcp.getRecipeImageID());
				rb.add(rcp.getRecipeDescription());
				rb.add(rcp.getRecipeLocations());
			}
		}
		return mxCur;
	}
	
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
