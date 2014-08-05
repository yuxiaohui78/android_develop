package com.loader;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class MyAsyncTaskLoader extends AsyncTaskLoader<DataHandler> {

	DataHandler mHaDataHandler;
	Context ctx = null;

	public MyAsyncTaskLoader(Context context) {
		super(context);
		ctx = context;
		mHaDataHandler = new DataHandler();
	}

	@Override
	public DataHandler loadInBackground() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			temp.add("Counting-----" + i);
		}
		try {
			((Activity)ctx).setTitle ("Waitting for the heavy Task 5 sec");
		
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


