package com.rupam.geosearch;

import com.rupam.geosearch.databasehelpers.DataCache;
import com.rupam.geosearch.parsers.SearchData;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,
		IAsyncUser {

	EditText search_field;
	Button submit_button;
	TextView resultView;
	ProgressDialog pd;

	DataCache dataCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dataCache = new DataCache(getApplicationContext());

		submit_button = (Button) findViewById(R.id.submit_button);
		submit_button.setOnClickListener(this);
		search_field = (EditText) findViewById(R.id.search_box);
		resultView = (TextView) findViewById(R.id.result_textview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();

		switch (id) {
		case R.id.submit_button:

			search();

			break;

		default:
			break;
		}
	}

	private void search() {
		// TODO Auto-generated method stub

		String input = search_field.getText().toString();
		pd = new ProgressDialog(this);
		pd.setTitle("Geo Search");
		pd.setMessage("Fetching Information");
		pd.setCancelable(true);

		ResultData rd;

		if (isNetworkAvailable()) { // network is available , so get it from
									// internet

			DataFetcher fetcher = new DataFetcher(this);
			String url = Constants.fetch_url;
			DataFormat format = DataFormat.json;
			String search_text = input;

			pd.show();
			SearchData sd = new SearchData(url, format, search_text);
			fetcher.execute(sd);

		} else if ((rd = inCache(input)) != null) { // no internet connection,
													// try to fetch it
			// from cache
			sendResult(rd);
		} else { // internet connection not available and no record found on
					// cache

			Toast.makeText(getApplicationContext(), "No Records Found",
					Toast.LENGTH_LONG).show();

			if (pd != null && pd.isShowing())
				pd.dismiss();
		}
	}

	private ResultData inCache(String ip_or_name) {
		// TODO Auto-generated method stub

		return dataCache.searchResult(ip_or_name);
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	@Override
	public void sendResult(ResultData result) {
		// TODO Auto-generated method stub
		// show the data in UI is the result is not null

		if (pd != null && pd.isShowing())
			pd.dismiss();

		if (result != null) {

			if (dataCache.searchResult(result.getHostname()) != null) {
				dataCache.update(result);
			} else {
				dataCache.insertResult(result);
			}

			String output = "";
			output += "IP : " + result.getIp() + "\n";
			output += "Host Name : " + result.getHostname() + "\n";
			output += "Country : " + result.getCountry_name() + "\n";
			output += "Country Code : " + result.getCountry_code() + "\n";
			output += "City : " + result.getCity() + "\n";
			output += "Area Code : " + result.getArea_code() + "\n";
			output += "Latitude : " + result.getLatitude() + "\n";
			output += "Longitude : " + result.getLongitude() + "\n";
			output += "Mtero Code : " + result.getMetro_code() + "\n";
			output += "Region Code : " + result.getRegion_code() + "\n";
			output += "Region Name : " + result.getRegion_name() + "\n";
			output += "ZipCode : " + result.getZipcode() + "\n";

			resultView.setText(output);
		} else {
			Log.d(Constants.GEO_SERCH_LOG_TAG, "result is null");
			resultView.setText("No Data");
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		dataCache.open();

		// dataCache.printall();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		dataCache.close();
	}
}
