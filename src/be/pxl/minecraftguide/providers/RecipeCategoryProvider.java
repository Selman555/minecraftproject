package be.pxl.minecraftguide.providers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;
import be.pxl.minecraftguide.R;
import be.pxl.minecraftguide.model.Category;
import be.pxl.minecraftguide.model.Recipe;

public class RecipeCategoryProvider extends ContentProvider {
	public static final String COL_CATID = "_ID";
	public static final String COL_CATIMG = "categoryImageID";
	public static final String COL_CATDESC = "categoryDescription";
	
	public static final String[] columnNames = { COL_CATID, COL_CATIMG, COL_CATDESC };
	
	public static final String AUTHORITY = "be.pxl.minecraftguide.providers.recipecategoryprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/*");
	private List<Category> categoryList;
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean onCreate() {
		categoryList = new ArrayList<Category>();
		categoryList.add(new Category(1, R.drawable.all, "All"));
		categoryList.add(new Category(2, R.drawable.armor, "Armor"));
		categoryList.add(new Category(3, R.drawable.weapons, "Weapons"));
		categoryList.add(new Category(4, R.drawable.tools, "Tools"));
		categoryList.add(new Category(5, R.drawable.blocks, "Blocks"));
		categoryList.add(new Category(6, R.drawable.decorations, "Decorations"));
		categoryList.add(new Category(7, R.drawable.enchantment_and_brewing, "Enchanting / Brewing"));
		categoryList.add(new Category(8, R.drawable.food, "Food"));
		categoryList.add(new Category(9, R.drawable.redstone, "Redstone related"));
		categoryList.add(new Category(10, R.drawable.transport, "Transportation"));
		categoryList.add(new Category(11, R.drawable.other, "Other"));
		return true;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		MatrixCursor mxCur = new MatrixCursor(columnNames);
		for (Category cat : categoryList)
		{
			RowBuilder rb = mxCur.newRow();
			rb.add(cat.get_id());
			rb.add(cat.getCategoryImage());
			rb.add(cat.getCategoryDescription());
		}
		return mxCur;
	}
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
