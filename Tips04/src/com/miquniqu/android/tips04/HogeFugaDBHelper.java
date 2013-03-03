package com.miquniqu.android.tips04;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class HogeFugaDBHelper extends SQLiteOpenHelper {
	private final static String DB_NAME = "hogefuga.db";// DB名
	private final static int DB_VERSION = 1; // バージョン

	private final static String DB_TABLE_HOGE = "hoge";
	private final static String DB_TABLE_FUGA = "fuga";
	private final static String DB_INDEX_HOGE = "hoge_uidx";
	private final static String DB_INDEX_FUGA = "fuga_uidx";

	public HogeFugaDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}

	public void createHogeTable() {

		SQLiteDatabase db = getWritableDatabase();

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE IF NOT EXISTS ");
			sb.append(DB_TABLE_HOGE);
			sb.append("(");
			sb.append("_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"); // 自動採取番のID
			sb.append("piyo1 INTEGER NOT NULL,"); //
			sb.append("piyo2 INTEGER NOT NULL"); //
			sb.append(")");
			db.execSQL(sb.toString());
		} finally {
			db.close();
		}
	}

	public void createHogeIndex() {

		SQLiteDatabase db = getWritableDatabase();

		try {
			// ユニークIndex この範囲で重複不可→IGNORE
			StringBuilder sb = new StringBuilder();
			//
			sb.append("CREATE UNIQUE INDEX IF NOT EXISTS ");
			sb.append(DB_INDEX_HOGE);
			sb.append(" ON ");
			sb.append(DB_TABLE_HOGE);
			sb.append("(");
			sb.append("piyo1,");
			sb.append("piyo2");
			sb.append(")");
			// 実行
			db.execSQL(sb.toString());

		} finally {
			db.close();
		}
	}

	public void dropHogeTable() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("drop table if exists " + DB_TABLE_HOGE);
		} finally {
			db.close();
		}
	}

	public void dropHogeIndex() {

		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("drop index if exists " + DB_INDEX_HOGE);
		} finally {
			db.close();
		}
	}

	public long insertHoge() {
		long retID = 0;
		SQLiteDatabase db = getWritableDatabase();
		try {
			// INDEX効いてたらREPLACE指定で上書き
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT OR REPLACE INTO ");
			sb.append(DB_TABLE_HOGE);
			sb.append("(piyo1,piyo2)");
			sb.append("values");
			sb.append("(?,?);");

			SQLiteStatement stmt = db.compileStatement(sb.toString());
			stmt.bindLong(1, 1);
			stmt.bindLong(2, 1);
			retID = stmt.executeInsert();
			stmt.close();
		} finally {
			db.close();
		}

		return retID;
	}

	public int selectCountHoge() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+ DB_TABLE_HOGE, null);
			if (!cursor.moveToFirst()){
				return 0;
			}
			return cursor.getInt(0);
		} finally {
			db.close();
		}
	}


	public void renameTableHoge2Fuga() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("ALTER TABLE " + DB_TABLE_HOGE + " RENAME TO " + DB_TABLE_FUGA);

		} finally {
			db.close();
		}
	}

	public void dropFugaTable() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("drop table if exists " + DB_TABLE_FUGA);
		} finally {
			db.close();
		}
	}

	public void dropFugaIndex() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("drop index if exists " + DB_INDEX_FUGA);
		} finally {
			db.close();
		}
	}

	public long insertFuga() {
		long retID = 0;
		SQLiteDatabase db = getWritableDatabase();
		try {
			// INDEX効いてたらREPLACE指定で上書き
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT OR REPLACE INTO ");
			sb.append(DB_TABLE_FUGA);
			sb.append("(piyo1,piyo2)");
			sb.append("values");
			sb.append("(?,?);");

			SQLiteStatement stmt = db.compileStatement(sb.toString());
			stmt.bindLong(1, 1);
			stmt.bindLong(2, 1);
			retID = stmt.executeInsert();
			stmt.close();
		} finally {
			db.close();
		}

		return retID;
	}

	public int selectCountFuga() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+ DB_TABLE_FUGA, null);
			if (!cursor.moveToFirst()){
				return 0;
			}
			return cursor.getInt(0);
		} finally {
			db.close();
		}
	}

	public void dropAll() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.execSQL("drop table if exists " + DB_TABLE_HOGE);
			db.execSQL("drop index if exists " + DB_INDEX_HOGE);
			db.execSQL("drop table if exists " + DB_TABLE_FUGA);
			db.execSQL("drop index if exists " + DB_INDEX_FUGA);
		} finally {
			db.close();
		}
	}

}
