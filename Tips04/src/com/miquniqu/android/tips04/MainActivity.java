package com.miquniqu.android.tips04;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private Button mHogeButton1 = null;
	private Button mHogeButton2 = null;
	private Button mHogeButton3 = null;
	private Button mHogeButton4 = null;
	private Button mHogeButton5 = null;
	private Button mHogeButton6 = null;
	private Button mHogefugaButton1 = null;
	private Button mFugaButton1 = null;
	private Button mFugaButton2 = null;
	private Button mFugaButton3 = null;
	private Button mFugaButton4 = null;
	private Button mAllButton = null;
	private Button mSeqButton1 = null;
	private Button mSeqButton2 = null;
	private Button mSeqButton3 = null;
	private Button mSeqButton4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHogeButton1 = (Button) findViewById(R.id.hoge_button1);
        mHogeButton2 = (Button) findViewById(R.id.hoge_button2);
        mHogeButton3 = (Button) findViewById(R.id.hoge_button3);
        mHogeButton4 = (Button) findViewById(R.id.hoge_button4);
        mHogeButton5 = (Button) findViewById(R.id.hoge_button5);
        mHogeButton6 = (Button) findViewById(R.id.hoge_button6);
        mHogefugaButton1 = (Button) findViewById(R.id.hogefuga_button1);
        mFugaButton1 = (Button) findViewById(R.id.fuga_button1);
        mFugaButton2 = (Button) findViewById(R.id.fuga_button2);
        mFugaButton3 = (Button) findViewById(R.id.fuga_button3);
        mFugaButton4 = (Button) findViewById(R.id.fuga_button4);
        mAllButton = (Button) findViewById(R.id.all_button);
        mSeqButton1 = (Button) findViewById(R.id.sequential_button1);
        mSeqButton2 = (Button) findViewById(R.id.sequential_button2);
        mSeqButton3 = (Button) findViewById(R.id.sequential_button3);
        mSeqButton4 = (Button) findViewById(R.id.sequential_button4);

        mHogeButton1.setOnClickListener(this);
        mHogeButton2.setOnClickListener(this);
        mHogeButton3.setOnClickListener(this);
        mHogeButton4.setOnClickListener(this);
        mHogeButton5.setOnClickListener(this);
        mHogeButton6.setOnClickListener(this);
        mHogefugaButton1.setOnClickListener(this);
        mFugaButton1.setOnClickListener(this);
        mFugaButton2.setOnClickListener(this);
        mFugaButton3.setOnClickListener(this);
        mFugaButton4.setOnClickListener(this);
        mAllButton.setOnClickListener(this);
        mSeqButton1.setOnClickListener(this);
        mSeqButton2.setOnClickListener(this);
        mSeqButton3.setOnClickListener(this);
        mSeqButton4.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		long id ;
		int count ;
		HogeFugaDBHelper dbHelper =new HogeFugaDBHelper(MainActivity.this);
		if (v == mHogeButton1) {
			dbHelper.createHogeTable();
			Toast.makeText(MainActivity.this, mHogeButton1.getText(), Toast.LENGTH_SHORT).show();
		} else if (v == mHogeButton2) {
			dbHelper.dropHogeTable();
			Toast.makeText(MainActivity.this, mHogeButton2.getText(), Toast.LENGTH_SHORT).show();
		} else if (v == mHogeButton3) {
			dbHelper.createHogeIndex();
			Toast.makeText(MainActivity.this, mHogeButton3.getText(), Toast.LENGTH_SHORT).show();
		} else if (v == mHogeButton4) {
			dbHelper.dropHogeIndex();
			Toast.makeText(MainActivity.this, mHogeButton4.getText(), Toast.LENGTH_SHORT).show();
		} else if (v == mHogeButton5) {
			id = dbHelper.insertHoge();
			Toast.makeText(MainActivity.this, mHogeButton5.getText()+"="+id, Toast.LENGTH_SHORT).show();
		} else if (v == mHogeButton6) {
			count = dbHelper.selectCountHoge();
			Toast.makeText(MainActivity.this, mHogeButton6.getText()+"="+count, Toast.LENGTH_SHORT).show();
		} else if (v == mHogefugaButton1) {
			dbHelper.renameTableHoge2Fuga();
			Toast.makeText(MainActivity.this, mHogefugaButton1.getText(), Toast.LENGTH_SHORT).show();
		} else if (v == mFugaButton1) {
			dbHelper.dropFugaTable();
			Toast.makeText(MainActivity.this, mFugaButton1.getText(), Toast.LENGTH_SHORT).show();
		} else if (v == mFugaButton2) {
			dbHelper.dropFugaIndex();
			Toast.makeText(MainActivity.this, mFugaButton2.getText(), Toast.LENGTH_SHORT).show();
		} else if (v == mFugaButton3) {
			id = dbHelper.insertFuga();
			Toast.makeText(MainActivity.this, mFugaButton3.getText()+"="+id, Toast.LENGTH_SHORT).show();
		} else if (v == mFugaButton4) {
			count = dbHelper.selectCountFuga();
			Toast.makeText(MainActivity.this, mFugaButton4.getText()+"="+count, Toast.LENGTH_SHORT).show();
		} else if (v == mAllButton) {
			Toast.makeText(MainActivity.this, mAllButton.getText(), Toast.LENGTH_SHORT).show();
			dbHelper.dropAll();
		}else if (v == mSeqButton1) {
			// UNIQUE　INDEXないからREPLACEが効かない確認
			Toast.makeText(MainActivity.this, mSeqButton1.getText(), Toast.LENGTH_SHORT).show();
			dbHelper.dropAll();
			// INDEXなし、REPLACEでの登録シーケンス
			dbHelper.createHogeTable();
			// HOGE　3件登録要求(REPLACE)
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			count = dbHelper.selectCountHoge();
			// HOGE　件数3件想定
			Toast.makeText(MainActivity.this, "selectCountHoge="+count, Toast.LENGTH_SHORT).show();

		}else if (v == mSeqButton2) {
			// UNIQUE　INDEXないからREPLACEが効く確認
			Toast.makeText(MainActivity.this, mSeqButton2.getText(), Toast.LENGTH_SHORT).show();
			dbHelper.dropAll();
			// INDEXあり、REPLACEでの登録シーケンス
			dbHelper.createHogeTable();
			dbHelper.createHogeIndex();
			// HOGE　3件登録要求(REPLACE)
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			count = dbHelper.selectCountHoge();
			// HOGE　件数1件想定
			Toast.makeText(MainActivity.this, "selectCountHoge="+count, Toast.LENGTH_SHORT).show();
		}else if (v == mSeqButton3) {
			// 対象としているテーブルをリネームすると、UNIQUE　INDEXがリネームした方にそのままついていく確認
			Toast.makeText(MainActivity.this, mSeqButton3.getText(), Toast.LENGTH_SHORT).show();
			dbHelper.dropAll();
			// INDEXあり、REPLACEでの登録シーケンス、RENAME
			dbHelper.createHogeTable();
			dbHelper.createHogeIndex();
			// HOGE　3件登録要求(REPLACE)
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			count = dbHelper.selectCountHoge();
			// hoge　件数1件想定
			Toast.makeText(MainActivity.this, "rename before count hoge="+count, Toast.LENGTH_SHORT).show();
			// HOGE TABLEだけFUGAにRENAME
			dbHelper.renameTableHoge2Fuga();
			// HOGE INDEXはそのままにして、再度HOGEをCREATE
			dbHelper.createHogeTable();
			// HOGE　3件登録要求(REPLACE)
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			count = dbHelper.selectCountHoge();
			// HOGE 件数1件、、、じゃなくて、3件になる想定(Indexが外れたので)
			Toast.makeText(MainActivity.this, "rename after count hoge="+count, Toast.LENGTH_SHORT).show();
			// FUGA 3件登録要求(REPLACE)
			dbHelper.insertFuga();
			dbHelper.insertFuga();
			dbHelper.insertFuga();
			count = dbHelper.selectCountFuga();
			// FUGA 件数3件、、、じゃなくて、1件になる想定(Indexが継続してるので)
			Toast.makeText(MainActivity.this, "rename after count fuga="+count, Toast.LENGTH_SHORT).show();

		}else if (v == mSeqButton4) {
			// UNIQUE　INDEXの張りなおしでREPLACEが効くことの確認
			Toast.makeText(MainActivity.this, mSeqButton4.getText(), Toast.LENGTH_SHORT).show();
			dbHelper.dropAll();
			// INDEXあり、REPLACEでの登録シーケンス、RENAME
			dbHelper.createHogeTable();
			dbHelper.createHogeIndex();
			// HOGE　3件登録要求(REPLACE)
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			count = dbHelper.selectCountHoge();
			// hoge　件数1件想定
			Toast.makeText(MainActivity.this, "rename before count hoge="+count, Toast.LENGTH_SHORT).show();
			// HOGE TABLEだけFUGAにRENAME
			dbHelper.renameTableHoge2Fuga();
			// HOGE INDEXをDROP
			dbHelper.dropHogeIndex();
			// HOGE INDEXはそのままにして、再度HOGEのTABLEとINDEXをCREATE
			dbHelper.createHogeTable();
			dbHelper.createHogeIndex();
			// HOGE　3件登録要求(REPLACE)
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			dbHelper.insertHoge();
			count = dbHelper.selectCountHoge();
			// HOGE 件数1件になる想定(Indexを再設定したので)
			Toast.makeText(MainActivity.this, "rename after count hoge="+count, Toast.LENGTH_SHORT).show();
			// FUGA 3件登録要求(REPLACE)
			dbHelper.insertFuga();
			dbHelper.insertFuga();
			dbHelper.insertFuga();
			count = dbHelper.selectCountFuga();
			// FUGA 件数1+3=4件になる想定(Indexが削除されてるので)
			Toast.makeText(MainActivity.this, "rename after count fuga="+count, Toast.LENGTH_SHORT).show();

		}
	}

}
