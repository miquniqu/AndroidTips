package com.miquniqu.android.tips01;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will keep every loaded fragment in memory. If this becomes too memory intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	Calendar mCurrentDate = null;
	int mInitPosition = 0;
	int mUpdateCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 現在日時を初期設定
		this.mCurrentDate = Calendar.getInstance();

		// Create the adapter that will return a fragment for each of the three primary sections
		// of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		// 初期位置真ん中
		mInitPosition = SectionsPagerAdapter.MAX_PAGE_NUM / 2;
		mViewPager.setCurrentItem(mInitPosition, false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		//コンテンツの差し替え反映
		mUpdateCount++;
		mViewPager.getAdapter().notifyDataSetChanged();

		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary sections of the app.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		//左右の頁マージンを大きめに
		public static final int MAX_PAGE_NUM = 1000;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return MAX_PAGE_NUM;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			// カレント日時からの移動位置で日付編集
			int targetPosition = position - mInitPosition;

			Calendar targetDate = (Calendar) mCurrentDate.clone();
			targetDate.add(Calendar.DATE, targetPosition);
			return convertDefault("yyyy/MM/dd", targetDate.getTime());
		}

		@Override
		public int getItemPosition(Object object) {
			// コンテンツの再読み込み向け
			return PagerAdapter.POSITION_NONE;
		}

		// 日付編集
		public String convertDefault(String _template, Date _date) {
			Locale loc = Locale.getDefault();
			DateFormat df = new SimpleDateFormat(_template, loc);
			TimeZone zone = TimeZone.getDefault();
			df.setTimeZone(zone);
			return df.format(_date);
		}

	}

	/**
	 * A dummy fragment representing a section of the app, but that simply displays dummy text.
	 */
	public class DummySectionFragment extends Fragment {
		public DummySectionFragment() {
		}

		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			Bundle args = getArguments();
			String info = Integer.toString(args.getInt(ARG_SECTION_NUMBER)) + ":" + "update=" + mUpdateCount;
			textView.setText(info);
			return textView;
		}
	}
}
