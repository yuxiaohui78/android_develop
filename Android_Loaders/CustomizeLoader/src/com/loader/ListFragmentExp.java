package com.loader;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListFragmentExp extends ListFragment implements
		LoaderManager.LoaderCallbacks<DataHandler> {

	BaseAdapter mAdapter;
	public static ListView mlisListView;
	// If non-null, this is the current filter the user has provided.
	String mCurFilter;

	public static FragmentActivity mAcFragmentActivity;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAcFragmentActivity = getActivity();
		// Give some text to display if there is no data. In a real
		// application this would come from a resource.
		setEmptyText("No phone numbers");

		// Create an empty adapter we will use to display the loaded data.
		setListAdapter(mAdapter);

		// Start out with a progress indicator.
		setListShown(false);
		mlisListView = getListView();
		getActivity().getSupportLoaderManager().initLoader(10, null, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public Loader<DataHandler> onCreateLoader(int arg0, Bundle arg1) {
		AsynChro asynChro = new AsynChro(ListFragmentExp.this.getActivity());
		asynChro.forceLoad();
		return asynChro;
	}

	@Override
	public void onLoadFinished(Loader<DataHandler> arg0, DataHandler arg1) {
		Log.i("YXH", "finished loading");
		mlisListView.setAdapter(new DataBinder(getActivity(), arg1.getData()));
		setListShown(true);
	}

	@Override
	public void onLoaderReset(Loader<DataHandler> arg0) {
		mlisListView.setAdapter(null);
	}

	public static class AsynChro extends AsyncTaskLoader<DataHandler> {

		DataHandler mHaDataHandler;

		public AsynChro(Context context) {
			super(context);
			mHaDataHandler = new DataHandler();
		}

		@Override
		public DataHandler loadInBackground() {
			ArrayList<String> temp = new ArrayList<String>();
			for (int i = 0; i < 100; i++) {
				temp.add("Counting-----" + i);
			}
			try {
				synchronized (this) {
					wait(5000);
				}
			} catch (Exception e) {
				e.getMessage();
			}
			mHaDataHandler.setData(temp);
			return mHaDataHandler;
		}

		@Override
		public void deliverResult(DataHandler data) {
			super.deliverResult(data);
		}
	}
}
