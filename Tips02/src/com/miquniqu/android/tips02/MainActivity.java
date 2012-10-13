package com.miquniqu.android.tips02;

import java.util.ArrayList;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final float LIST_DIP = 60.0f;
	private static final long ANIME_DURATION = 1000;

	ScrollView mContentSpace;
	LinearLayout mMenuListLayout;
	ArrayList<TextView> mScrollTextList = null;
	int[] mListItemTops = null;
	boolean mSelected = false;
	Button mButtonContent = null;
	float mScale = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// view
		mContentSpace = (ScrollView) findViewById(R.id.ContentSpace);
		mMenuListLayout = (LinearLayout) findViewById(R.id.MenuListLayout);
		mButtonContent = (Button) findViewById(R.id.ButtonContent);

		// dip取得
		mScale = getApplicationContext().getResources().getDisplayMetrics().density;
		int listHeight = (int) (LIST_DIP * mScale + 0.5f);
		int padding = getResources().getDimensionPixelSize(R.dimen.padding_medium);

		// ボタンのリスナー
		mButtonContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "content back", Toast.LENGTH_SHORT).show();
				if (!mSelected) {
					// 未選択ならやめる
					return;
				}
				mSelected = false;
				restoreList();
			}
		});

		// 後で利用
		mScrollTextList = new ArrayList<TextView>();
		// 動的にレイアウト詰め込み
		for (int i = 0; i < 10; i++) {
			// 色きめ
			int red, green, blue;
			red = 100 + (int) (Math.random() * 155);
			green = 100 + (int) (Math.random() * 155);
			blue = 100 + (int) (Math.random() * 155);
			/*
			double pattern = Math.random() * 3;
			if (pattern < 1) {
				red = 0xE0;
				green = 128 + (int) (Math.random() * 50);
				blue = 128 + (int) (Math.random() * 50);
			} else if (pattern < 2) {
				red = 128 + (int) (Math.random() * 50);
				green = 0xE0;
				blue = 128 + (int) (Math.random() * 50);
			} else {
				red = 128 + (int) (Math.random() * 50);
				green = 128 + (int) (Math.random() * 50);
				blue = 0xE0;
			}*/

			// リスト項目としてアイテム生成
			TextView textView = new TextView(getApplicationContext());
			textView.setPadding(padding, padding, padding, padding);
			textView.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
			textView.setHeight(listHeight);
			textView.setText("scroll text view no." + i);
			textView.setBackgroundColor(Color.argb(255, red, green, blue));
			textView.setOnClickListener(this);

			mScrollTextList.add(textView);
			mMenuListLayout.addView(textView);
		}
		// 移動距離の退避領域
		mListItemTops = new int[mScrollTextList.size()];
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	}

	public void onClick(View view) {

		if (view instanceof TextView) {
			Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

			if (mSelected) {
				// 選択済みならやめる
				return;
			}
			mSelected = true;
			// 項目選択時処理
			listSelect(view);
		}

	}

	private void listSelect(View view) {
		int listUpTop = mMenuListLayout.getTop();
		int listDownTop = mMenuListLayout.getTop() + mMenuListLayout.getHeight();
		boolean selected = false;
		ArrayList<Animator> animatorList = new ArrayList<Animator>();

		for (int count = 0; count < mScrollTextList.size(); count++) {
			TextView textView = mScrollTextList.get(count);
			int translationUp = listUpTop - textView.getHeight();
			int translationDown = listDownTop;
			//　現在位置を退避
			mListItemTops[count] = textView.getTop();

			// 選択状態の判定
			if (textView == view) {
				selected = true;
			}

			if (textView == view || !selected) {
				// 選択前、選択項目
				PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", translationUp);
				ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(textView, pvhY);
				oa.setDuration(ANIME_DURATION);
				animatorList.add(oa);
			} else {
				// 選択後
				PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", translationDown);
				ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(textView, pvhY);
				oa.setDuration(ANIME_DURATION);
				animatorList.add(oa);
			}
		}

		// まとめて要求
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setInterpolator(new AccelerateInterpolator());
		animatorSet.playTogether(animatorList);
		animatorSet.addListener(new Animator.AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				mContentSpace.setVisibility(View.GONE);

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}
		});
		animatorSet.start();
		return;
	}

	private void restoreList() {
		int listUpTop = mMenuListLayout.getTop();
		int listDownTop = mMenuListLayout.getTop() + mMenuListLayout.getHeight();
		boolean selected = false;

		mContentSpace.setVisibility(View.VISIBLE);

		ArrayList<Animator> animatorList = new ArrayList<Animator>();
		for (int count = 0; count < mScrollTextList.size(); count++) {
			TextView textView = mScrollTextList.get(count);
			PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", mListItemTops[count]);
			ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(textView, pvhY);
			oa.setDuration(ANIME_DURATION);
			animatorList.add(oa);
		}

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setInterpolator(new AccelerateInterpolator());
		animatorSet.playTogether(animatorList);
		animatorSet.start();

		return;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode != KeyEvent.KEYCODE_BACK) {
			return super.onKeyDown(keyCode, event);
		} else {
			// キーバックイベント
			if (!mSelected) {
				// 未選択ならそのまま
				return super.onKeyDown(keyCode, event);
			}
			mSelected = false;
			restoreList();
			return false;
		}
	}
}
