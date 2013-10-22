package be.pxl.minecraftguide.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public static final String COL_RECIMG = "recipeImageName";
	public static final String COL_RECDESC = "recipeDescription";
	public static final String COL_RECLOC = "recipeLocations";
	
	public static final String[] columnNames = {COL_RECID, COL_RECCATID, COL_RECIMG, COL_RECDESC, COL_RECLOC };
	
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
        recipesList.add(new Recipe(1, 2, "diamond_boots", "Boots (Diamond)", new int[] { 0,0,0,56,0,56,56,0,56 }));
        recipesList.add(new Recipe(2, 2, "gold_boots", "Boots (Gold)", new int[] { 0,0,0,14,0,14,14,0,14 } ));
        recipesList.add(new Recipe(3, 2, "iron_boots", "Boots (Iron)", new int[] { 0,0,0,15,0,15,15,0,15 } ));
		return true;
	}
	
	@Override
	public Cursor query(Uri arg0, String[] categories, String arg2, String[] arg3,
			String arg4) {
		MatrixCursor mxCur = new MatrixCursor(columnNames);
		for (Recipe rcp : recipesList)
		{
			RowBuilder rb = mxCur.newRow();
			rb.add(rcp.getRecipeID());
			rb.add(rcp.getRecipeCategory());
			rb.add(rcp.getRecipeImageName());
			rb.add(rcp.getRecipeDescription());
			rb.add(rcp.getRecipeLocations());
		}
		return mxCur;
	}
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
