package be.pxl.minecraftguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CommandDetails extends Fragment {
	String[] detailValues = new String[2];
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle extras = getArguments();
		if (extras != null) {
			String[] test = savedInstanceState.getStringArray("detail");
			String[] test2 = test;
			detailValues = savedInstanceState.getStringArray("detail");
		}
		
		return inflater.inflate(R.layout.commanddetails, container, false);
	}


	public void updateArticleView(String[] strings) {
		detailValues = strings;
		setTextFields();
	}


	@Override
	public void onStart() {
		setTextFields();
		super.onStart();
	}
	
	public void setTextFields() {
		TextView title = (TextView)getView().findViewById(R.id.txtFragCommandTitle);
		TextView desc = (TextView) getView().findViewById(R.id.txtFragCommandDesc);
		title.setText(detailValues[0]);
		desc.setText(detailValues[1]);
	}

}
