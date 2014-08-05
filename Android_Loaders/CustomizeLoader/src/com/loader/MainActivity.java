package com.loader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity{
	FragmentTransaction mTFragmentTransaction;
	FragmentManager mManFragmentManager;
	ListFragmentExp mListFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListFragment = new ListFragmentExp();
		FrameLayout cursor = (FrameLayout) findViewById(R.id.listFragment);
		mManFragmentManager = getSupportFragmentManager();

		if (mManFragmentManager.findFragmentByTag("Tag") == null) {
			mTFragmentTransaction = mManFragmentManager.beginTransaction();
			mTFragmentTransaction.add(cursor.getId(), mListFragment, "Tag");
			mTFragmentTransaction.commit();
		}
	}
}
