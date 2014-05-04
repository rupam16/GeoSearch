package com.rupam.geosearch.databasehelpers;

import com.rupam.geosearch.Constants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper {

	public static final String TABLE_RESULT = "result";
	public static final String COLUMN_HOSTNAME = "host";
	public static final String COLUMN_IP = "ip";
	public static final String COLUMN_COUNTRYCODE = "country_code";
	public static final String COLUMN_COUNTRYNAME = "country_name";
	public static final String COLUMN_REGIONCODE = "region_code";
	public static final String COLUMN_REGIONNAME = "region_name";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_ZIPCODE = "zipcode";
	public static final String COLUMN_LATITUDE = "latitude";
	public static final String COLUMN_LONGITUDE = "longitude";
	public static final String COLUMN_METROCODE = "metro_code";
	public static final String COLUMN_AREACODE = "area_code";

	private static final String DATABASE_NAME = "results.db";
	private static final int DATABASE_VERSION = 1;

	public MySqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_RESULT + "(" + COLUMN_IP + " varchar(15),"
			+ COLUMN_HOSTNAME + " varchar(30),"
			+ COLUMN_COUNTRYNAME + " varchar(30)," + COLUMN_COUNTRYCODE
			+ " varchar(30)," + COLUMN_REGIONNAME + " varchar(30),"
			+ COLUMN_REGIONCODE + " varchar(30)," + COLUMN_CITY
			+ " varchar(30)," + COLUMN_ZIPCODE + " varchar(30),"
			+ COLUMN_LATITUDE + " real," + COLUMN_LONGITUDE + " real,"
			+ COLUMN_METROCODE + " varchar(30)," + COLUMN_AREACODE
			+ " varchar(30));";

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		// TODO Auto-generated method stub
		Log.w(Constants.GEO_SERCH_LOG_TAG, "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULT);
		onCreate(database);

	}

}
