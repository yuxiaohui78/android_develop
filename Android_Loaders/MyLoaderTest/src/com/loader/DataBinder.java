package com.loader;

import java.util.ArrayList;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DataBinder extends BaseAdapter {
	ArrayList<String> mLisArrayList;

	FragmentActivity mAtcFragmentActivity;

	public DataBinder(FragmentActivity mActivity, ArrayList<String> mList) {
		mLisArrayList = mList;
		mAtcFragmentActivity = mActivity;
	}

	@Override
	public int getCount() {
		return mLisArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView mTextView;
		if (convertView == null) {
			mTextView = new TextView(mAtcFragmentActivity);

			mTextView.setLayoutParams(new ListView.LayoutParams(
					LayoutParams.FILL_PARENT, 60));
			mTextView.setGravity(Gravity.CENTER_HORIZONTAL
					| Gravity.CENTER_VERTICAL);
			mTextView.setTextSize(20);
			mTextView.setBackgroundColor(Color.GRAY);
			mTextView.setTextColor(Color.GREEN);
			convertView = mTextView;
		} else {
			mTextView = (TextView) convertView;
		}
		mTextView.setText(mLisArrayList.get(position));
		return convertView;
	}
}
