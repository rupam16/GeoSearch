package com.rupam.geosearch.databasehelpers;

import com.rupam.geosearch.Constants;
import com.rupam.geosearch.ResultData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataCache {
	private SQLiteDatabase database;
	private MySqliteHelper dbHelper;

	private String[] allColumns = { MySqliteHelper.COLUMN_AREACODE,
			MySqliteHelper.COLUMN_CITY, MySqliteHelper.COLUMN_COUNTRYCODE,
			MySqliteHelper.COLUMN_COUNTRYNAME, MySqliteHelper.COLUMN_IP,
			MySqliteHelper.COLUMN_LATITUDE, MySqliteHelper.COLUMN_LONGITUDE,
			MySqliteHelper.COLUMN_METROCODE, MySqliteHelper.COLUMN_REGIONCODE,
			MySqliteHelper.COLUMN_REGIONNAME, MySqliteHelper.COLUMN_ZIPCODE,
			MySqliteHelper.COLUMN_HOSTNAME };

	public DataCache(Context context) {
		// TODO Auto-generated constructor stub
		dbHelper = new MySqliteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void insertResult(ResultData rd) {
		ContentValues values = getContentValuesFromData(rd);

		database.insert(MySqliteHelper.TABLE_RESULT, null, values);

		Log.d(Constants.GEO_SERCH_LOG_TAG, "Inserting " + rd.getHostname());

	}

	public ResultData searchResult(String ip_or_host) {

		Log.d(Constants.GEO_SERCH_LOG_TAG, "searching " + ip_or_host);

		String searchString = "Select * FROM " + MySqliteHelper.TABLE_RESULT
				+ " WHERE " + MySqliteHelper.COLUMN_IP + " like '" + ip_or_host
				+ "' OR " + MySqliteHelper.COLUMN_HOSTNAME + " like '"
				+ ip_or_host + "'";

		Log.d(Constants.GEO_SERCH_LOG_TAG, "Search String : " + searchString);

		Cursor cursor = database.rawQuery(searchString, null);

		ResultData rd = null;

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			rd = convertToResultData(cursor);
		}

		cursor.close();

		return rd;
	}

	public void update(ResultData rd) {
		ContentValues values = getContentValuesFromData(rd);
		database.update(MySqliteHelper.TABLE_RESULT, values,
				MySqliteHelper.COLUMN_HOSTNAME + " = " + rd.getHostname(), null);

		Log.d(Constants.GEO_SERCH_LOG_TAG, "Updating " + rd.getHostname());
	}

	public void printall() {
		Cursor cur = database.query(MySqliteHelper.TABLE_RESULT, allColumns,
				null, null, null, null, null);

		Log.d(Constants.GEO_SERCH_LOG_TAG, "Total Count : " + cur.getCount());
		ResultData rd;

		cur.moveToFirst();
		while (!cur.isAfterLast()) {

			rd = convertToResultData(cur);

			Log.d(Constants.GEO_SERCH_LOG_TAG, "data : " + rd.getHostname());
			cur.moveToNext();
		}

		cur.close();
	}

	private ResultData convertToResultData(Cursor cur) {
		ResultData rd = new ResultData();

		rd.setArea_code(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_AREACODE)));
		rd.setCity(cur.getString(cur.getColumnIndex(MySqliteHelper.COLUMN_CITY)));
		rd.setCountry_code(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_COUNTRYCODE)));
		rd.setCountry_name(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_COUNTRYNAME)));
		rd.setHostname(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_HOSTNAME)));
		rd.setIp(cur.getString(cur.getColumnIndex(MySqliteHelper.COLUMN_IP)));
		rd.setLatitude(cur.getDouble(cur
				.getColumnIndex(MySqliteHelper.COLUMN_LATITUDE)));
		rd.setLongitude(cur.getDouble(cur
				.getColumnIndex(MySqliteHelper.COLUMN_LONGITUDE)));
		rd.setMetro_code(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_METROCODE)));
		rd.setRegion_code(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_REGIONCODE)));
		rd.setRegion_name(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_REGIONNAME)));
		rd.setZipcode(cur.getString(cur
				.getColumnIndex(MySqliteHelper.COLUMN_ZIPCODE)));

		return rd;
	}

	public ContentValues getContentValuesFromData(ResultData rd) {
		ContentValues values = new ContentValues();

		values.put(MySqliteHelper.COLUMN_AREACODE, rd.getArea_code());
		values.put(MySqliteHelper.COLUMN_CITY, rd.getCity());
		values.put(MySqliteHelper.COLUMN_COUNTRYCODE, rd.getCountry_code());
		values.put(MySqliteHelper.COLUMN_COUNTRYNAME, rd.getCountry_name());
		values.put(MySqliteHelper.COLUMN_IP, rd.getIp());
		values.put(MySqliteHelper.COLUMN_LATITUDE, rd.getLatitude());
		values.put(MySqliteHelper.COLUMN_LONGITUDE, rd.getLongitude());
		values.put(MySqliteHelper.COLUMN_METROCODE, rd.getMetro_code());
		values.put(MySqliteHelper.COLUMN_REGIONCODE, rd.getRegion_code());
		values.put(MySqliteHelper.COLUMN_REGIONNAME, rd.getRegion_name());
		values.put(MySqliteHelper.COLUMN_ZIPCODE, rd.getZipcode());
		values.put(MySqliteHelper.COLUMN_AREACODE, rd.getArea_code());
		values.put(MySqliteHelper.COLUMN_HOSTNAME, rd.getHostname());

		return values;
	}
}
