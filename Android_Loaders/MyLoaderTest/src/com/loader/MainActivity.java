package com.loader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.*;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<DataHandler>, View.OnClickListener{
	
	private ListView mListView = null;
	BaseAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById (R.id.click_me_btn).setOnClickListener(this);
		
		mListView = (ListView)findViewById (R.id.mylistview);
		mListView.setAdapter(mAdapter);
		
		this.getSupportLoaderManager().initLoader(10, null, this);
	}

	@Override
	public Loader<DataHandler> onCreateLoader(int arg0, Bundle arg1) {
		MyAsyncTaskLoader myTaskLoader = new MyAsyncTaskLoader(this);
		myTaskLoader.forceLoad();
		return myTaskLoader;
	}

	@Override
	public void onLoadFinished(Loader<DataHandler> arg0, DataHandler arg1) {
		mListView.setAdapter(new DataBinder(this, arg1.getData()));
	}

	@Override
	public void onLoaderReset(Loader<DataHandler> arg0) {
		mListView.setAdapter(null);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.click_me_btn:
			Toast.makeText(this, "You clicked me! I don't block the loading task.", Toast.LENGTH_SHORT).show ();
			break;
		}
	}
}
