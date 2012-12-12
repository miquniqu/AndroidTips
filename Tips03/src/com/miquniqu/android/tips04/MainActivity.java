package com.miquniqu.android.tips04;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {

	private static final int MENU_PREF = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSherlock().getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("テストタイトル");

		TextView textview = (TextView) findViewById(R.id.infotext);
		textview.setText("VERSION=" + Build.VERSION.RELEASE + "\nMODEL=" + Build.MODEL);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.activity_main, menu);

		MenuItem menuitem;

		menuitem = menu.add(0, MENU_PREF, 0, "テスト");
		menuitem.setIcon(android.R.drawable.ic_menu_preferences);
		menuitem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			//NavUtils.navigateUpFromSameTask(this);
			return true;
		case MENU_PREF:
			Toast.makeText(getApplicationContext(), "MENU_PREF", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
