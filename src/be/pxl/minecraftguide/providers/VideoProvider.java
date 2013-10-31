package be.pxl.minecraftguide.providers;

import java.util.ArrayList;
import java.util.List;

import be.pxl.minecraftguide.R;
import be.pxl.minecraftguide.model.Video;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;

public class VideoProvider extends ContentProvider {
	public static final String COL_VIDID = "_ID";
	public static final String COL_VIDIMG = "videoImageID";
	public static final String COL_VIDDESC = "videoDescription";
	public static final String COL_VIDURL = "videoURL";

	public static final String[] columnNames = { COL_VIDID, COL_VIDIMG,
			COL_VIDDESC, COL_VIDURL };

	public static final String AUTHORITY = "be.pxl.minecraftguide.providers.videoprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/*");
	private List<Video> videoList;

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
		videoList = new ArrayList<Video>();
		videoList.add(new Video(1, R.drawable.minecrafttrailer,
				"Minecraft Trailer",
				"http://www.youtube.com/watch?v=MmB9b5njVbA"));
		videoList.add(new Video(2, R.drawable.firstshelter,
				"Your First Shelter in Minecraft",
				"http://www.youtube.com/watch?v=ylVtj-1Ccgg"));
		videoList.add(new Video(3, R.drawable.startinghouse,
				"Minecraft: Building a Starting House",
				"http://www.youtube.com/watch?v=qss4uy6C_g0"));
		videoList.add(new Video(1, R.drawable.minecrafttrailer,
				"Minecraft Trailer",
				"http://www.youtube.com/watch?v=MmB9b5njVbA"));
		videoList.add(new Video(4, R.drawable.surviving,
				"Minecraft Tutorials : E01 How to Survive your First Night",
				"http://www.youtube.com/watch?v=B36Ehzf2cxE"));
		videoList.add(new Video(5, R.drawable.top5,
				"Top 5 Minecraft creations houses",
				"http://www.youtube.com/watch?v=kzQQOMCxTp4"));

		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		MatrixCursor mxCur = new MatrixCursor(columnNames);

		for (Video vid : videoList) {
			RowBuilder rb = mxCur.newRow();
			rb.add(vid.get_id());
			rb.add(vid.getVideoImageID());
			rb.add(vid.getVideoDescription());
			rb.add(vid.getVideoURL());
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
