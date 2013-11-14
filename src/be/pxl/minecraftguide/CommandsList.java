package be.pxl.minecraftguide;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

//FragmentActivity van support libraries
public class CommandsList extends ListFragment {
	OnListItemSelectedListener mCallback;
	
    // De container moet deze interface implementeren
    public interface OnListItemSelectedListener {
        public void onListItemSelected(int position);
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		// This makes sure that the container activity has implemented
	    // the callback interface. If not, it throws an exception
	    try {
	        mCallback = (OnListItemSelectedListener) activity;
	    } catch (ClassCastException e) {
	    	(Toast.makeText(this.getActivity().getApplicationContext(), "Could not pair command details.", Toast.LENGTH_LONG)).show();
	    }
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		//Bron: http://wptrafficanalyzer.in/blog/a-listfragment-application-in-android/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.fragmentrowview, Commands.listValues);
		
		setListAdapter(adapter);
		setListShown(true);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> fragmentList, View fragmentListItem, int rowIndex, long arg3) {
				mCallback.onListItemSelected(rowIndex);
			}
		});
	}
}
