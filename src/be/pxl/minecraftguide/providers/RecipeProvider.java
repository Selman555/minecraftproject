package be.pxl.minecraftguide.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import be.pxl.minecraftguide.R;
import be.pxl.minecraftguide.RecipeDetails;
import be.pxl.minecraftguide.model.Recipe;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;
import android.util.JsonToken;
import android.util.Log;

public class RecipeProvider extends ContentProvider {
	public static final String COL_RECID = "_ID";
	public static final String COL_RECCATID = "recipeCategory";
	public static final String COL_RECIMGID	= "recipeImageID";
	public static final String COL_RECDESC = "recipeDescription";
	public static final String COL_RECLOC = "recipeLocations";
	public static final String COL_RECIMGS = "recipeUsedImages";
	
	public static final String[] columnNames = {COL_RECID, COL_RECCATID, COL_RECIMGID, COL_RECDESC, COL_RECLOC, COL_RECIMGS };
	
	public static final String AUTHORITY = "be.pxl.minecraftguide.providers.recipeprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/*");
	public static boolean busy = true;
	private static List<Recipe> recipesList;
	private Resources res;
	private Context context;
	
	
	
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
		res = this.getContext().getResources();
		context = this.getContext();
		
		/*//Ores & ingots
        recipesList.add(new Recipe(1, 11, R.drawable.diamond_ingot, "Diamond ingot",
        		"0,0,0,0,0,0,0,0,1", String.format("%d,%d", R.drawable.air, R.drawable.diamond_ore )));
        recipesList.add(new Recipe(2, 11, R.drawable.iron_ingot, "Iron ingot",
        		"0,0,0,0,0,0,0,0,1", String.format("%d,%d", R.drawable.air, R.drawable.iron_ore )));
        recipesList.add(new Recipe(3, 11, R.drawable.gold_ingot, "Gold ingot",
        		"0,0,0,0,0,0,0,0,1", String.format("%d,%d", R.drawable.air, R.drawable.gold_ore )));
        recipesList.add(new Recipe(4, 11, R.drawable.diamond_ore, "Diamond ore",
        		"0,0,0,0,0,0,0,0,1", String.format("%d,%d", R.drawable.air, R.drawable.wooden_pickaxe )));
        recipesList.add(new Recipe(5, 11, R.drawable.iron_ore, "Iron ore",
        		"0,0,0,0,0,0,0,0,1", String.format("%d,%d", R.drawable.air, R.drawable.wooden_pickaxe )));
        recipesList.add(new Recipe(6, 11, R.drawable.gold_ore, "Gold ore",
        		"0,0,0,0,0,0,0,0,1", String.format("%d,%d", R.drawable.air, R.drawable.wooden_pickaxe )));
        
        //Armor
        recipesList.add(new Recipe(7, 2, R.drawable.diamond_boots, "Boots (Diamond)",
        		"0,0,0,1,0,1,1,0,1", String.format("%d,%d", R.drawable.air, R.drawable.diamond_ingot )));
        recipesList.add(new Recipe(8, 2, R.drawable.gold_boots, "Boots (Gold)",
        		"0,0,0,1,0,1,1,0,1", String.format("%d,%d", R.drawable.air, R.drawable.gold_ingot )));
        recipesList.add(new Recipe(9, 2, R.drawable.iron_boots, "Boots (Iron)",
        		"0,0,0,1,0,1,1,0,1", String.format("%d,%d", R.drawable.air, R.drawable.iron_ingot )));
        recipesList.add(new Recipe(10, 2, R.drawable.diamond_leggings, "Leggings (Diamond)",
        		"1,1,1,1,0,1,1,0,1", String.format("%d,%d", R.drawable.air, R.drawable.diamond_ingot )));
        recipesList.add(new Recipe(11, 2, R.drawable.gold_leggings, "Leggings (Gold)",
        		"1,1,1,1,0,1,1,0,1", String.format("%d,%d", R.drawable.air, R.drawable.gold_ingot )));
        recipesList.add(new Recipe(12, 2, R.drawable.iron_leggings, "Leggings (Iron)",
        		"1,1,1,1,0,1,1,0,1", String.format("%d,%d", R.drawable.air, R.drawable.iron_ingot )));
        recipesList.add(new Recipe(13, 2, R.drawable.diamond_chestplate, "Chestplate (Diamond)",
        		"1,0,1,1,1,1,1,1,1", String.format("%d,%d", R.drawable.air, R.drawable.diamond_ingot )));
        recipesList.add(new Recipe(14, 2, R.drawable.gold_chestplate, "Chestplate (Gold)",
        		"1,0,1,1,1,1,1,1,1", String.format("%d,%d", R.drawable.air, R.drawable.gold_ingot )));
        recipesList.add(new Recipe(15, 2, R.drawable.iron_chestplate, "Chestplate (Iron)",
        		"1,0,1,1,1,1,1,1,1", String.format("%d,%d", R.drawable.air, R.drawable.iron_ingot )));
        recipesList.add(new Recipe(16, 2, R.drawable.diamond_helmet, "Helmet (Diamond)",
        		"1,1,1,1,0,1,0,0,0", String.format("%d,%d", R.drawable.air, R.drawable.diamond_ingot )));
        recipesList.add(new Recipe(17, 2, R.drawable.gold_helmet, "Helmet (Gold)",
        		"1,1,1,1,0,1,0,0,0", String.format("%d,%d", R.drawable.air, R.drawable.gold_ingot )));
        recipesList.add(new Recipe(18, 2, R.drawable.iron_helmet, "Helmet (Iron)",
        		"1,1,1,1,0,1,0,0,0", String.format("%d,%d", R.drawable.air, R.drawable.iron_ingot )));*/
		return true;
	}
	
	@Override
	public Cursor query(Uri arg0, String[] categories, String recipeID, String[] imgIDs,
			String arg4) {
		MatrixCursor mxCur = new MatrixCursor(columnNames);
		RowBuilder rb;
		
		if (imgIDs != null) {
			for (Recipe rcp : recipesList) {
				if (rcp.getUsedImages().contains(imgIDs[0])) {
					rb = mxCur.newRow();
					rb.add(rcp.getRecipeID());
					rb.add(rcp.getRecipeCategory());
					rb.add(res.getIdentifier(rcp.getRecipeImageID(), "drawable", context.getPackageName()));
					rb.add(rcp.getRecipeDescription());
				}
			}
		} else if (categories != null && Integer.parseInt(categories[0]) != 1) {
			for (Recipe rcp : recipesList) {
				if (rcp.getRecipeCategory() == Integer.parseInt(categories[0])) {
					rb = mxCur.newRow();
					rb.add(rcp.getRecipeID());
					rb.add(rcp.getRecipeCategory());
					rb.add(res.getIdentifier(rcp.getRecipeImageID(), "drawable", context.getPackageName()));
					rb.add(rcp.getRecipeDescription());
				}
			}
		} else if (recipeID != null) {
			for (Recipe rcp : recipesList) {
				if (rcp.getRecipeID() == Integer.parseInt(recipeID)) {
					rb = mxCur.newRow();
					rb.add(rcp.getRecipeID());
					rb.add(rcp.getRecipeCategory());
					rb.add(res.getIdentifier(rcp.getRecipeImageID(), "drawable", context.getPackageName()));
					rb.add(rcp.getRecipeDescription());
					rb.add(rcp.getRecipeLocations());
					rb.add(createResourceIDStringFromString(rcp.getUsedImages()));
				}
			}
		} else {
			for (Recipe rcp : recipesList) {
				rb = mxCur.newRow();
				rb.add(rcp.getRecipeID());
				rb.add(rcp.getRecipeCategory());
				rb.add(res.getIdentifier(rcp.getRecipeImageID(), "drawable", context.getPackageName()));
				rb.add(rcp.getRecipeDescription());
			}
		}
		if (mxCur.getCount() == 0) {
			rb = mxCur.newRow();
			rb.add(0);
			rb.add(0);
			rb.add(R.drawable.air);
			rb.add("No recipes found");
			rb.add("0,0,0,0,0,0,0,0,0");
			rb.add("0");
		}
		return mxCur;
	}
	
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void GetItems() {
		new Thread(new Runnable() {
		    //Thread to stop network calls on the UI thread
		    public void run() {
				BufferedReader reader = null;
				HttpClient client = new DefaultHttpClient();
		
				try {
					// create GET request
					HttpGet httpGet = new HttpGet("http://192.168.0.251:8084/MinecraftRestServer/webresources/Items");
					// execute GET request
					HttpResponse response = client.execute(httpGet);
					// check response
					StatusLine statusLine = response.getStatusLine();
					int statusCode = statusLine.getStatusCode();
					if (statusCode == 200) { // response OK
						// Response uitlezen
						HttpEntity entity = response.getEntity();
						// Inputstream om uit te lezen
						InputStream content = entity.getContent();
						// BufferedReader van de inputstream
						reader = new BufferedReader(new InputStreamReader(content));
						Gson gson = new Gson(); //Nieuwe Gson instantie
						//Nieuwe receptenlijst aanmaken door gson te gebruiken:
						//TypeToken omschrijft de vormgeving van de ontvangen json array
						recipesList = gson.fromJson(reader, new TypeToken<List<Recipe>>(){}.getType());
					} else {
						Log.e(getClass().getName().toString(), "Failed to download file");
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException iae) {
					iae.printStackTrace();
				} finally {
					busy = false;
				}
		    }
		}).start();
	}
	
	public String createResourceIDStringFromString(String values) {
		String[] arrSplittedValues = values.split(",");
		String resultString = "";
		
		for (int counter = 0; counter < arrSplittedValues.length; counter++) {
			resultString += res.getIdentifier(arrSplittedValues[counter], "drawable", context.getPackageName());
			if (counter != arrSplittedValues.length - 1) {
				resultString += ",";
			}
		}
		
		return resultString;
	}
}
