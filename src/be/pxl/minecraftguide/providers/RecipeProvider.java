package be.pxl.minecraftguide.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;
import android.util.Log;
import be.pxl.minecraftguide.R;
import be.pxl.minecraftguide.model.Recipe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RecipeProvider extends ContentProvider {
	public static final String COL_RECID = "_id";
	public static final String COL_RECCATID = "recipeCategory";
	public static final String COL_RECIMGID	= "recipeImageID";
	public static final String COL_RECDESC = "recipeDescription";
	public static final String COL_RECLOC = "recipeLocations";
	public static final String COL_RECIMGS = "recipeUsedImages";
	
	public static final String[] columnNames = {COL_RECID, COL_RECCATID, COL_RECIMGID, COL_RECDESC, COL_RECLOC, COL_RECIMGS };
	
	public static final String AUTHORITY = "be.pxl.minecraftguide.providers.recipeprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/*");
	public static boolean busy = true;
	public static String errorMessage;
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

		return true;
	}
	
	@Override
	public Cursor query(Uri arg0, String[] categories, String recipeID, String[] imgIDs,
			String arg4) {
		MatrixCursor mxCur = new MatrixCursor(columnNames);
		RowBuilder rb;
		
		try {
			if (imgIDs != null) {
				for (Recipe rcp : recipesList) {
					String resourceName = getResourceName(Integer.parseInt(imgIDs[0]));
					resourceName = resourceName.substring(resourceName.lastIndexOf("/") + 1, resourceName.length());
					if (rcp.getUsedImages().contains(resourceName)) {
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
		} catch (NumberFormatException nfe) {
			errorMessage = "Some received data could not be processed";
			return null;
		}
	}
	
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void GetItems() {
		//___________BRON: http://stackoverflow.com/questions/19642445/java-convert-json-array-to-typed-listt
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
					errorMessage = "Connection to webservice failed";
				} catch (IOException e) {
					errorMessage = "Internet connection required";
				} catch (IllegalArgumentException iae) {
					errorMessage = "Some received data could not be processed";
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
	
	private String getResourceName(int id) {
		return res.getResourceName(id);
	}
}
