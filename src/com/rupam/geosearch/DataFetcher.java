package com.rupam.geosearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.rupam.geosearch.parsers.DataParser;
import com.rupam.geosearch.parsers.SearchData;

import android.os.AsyncTask;
import android.util.Log;

public class DataFetcher extends AsyncTask<SearchData, Integer, ResultData> {
	IAsyncUser caller;
	SearchData sd;

	public DataFetcher(IAsyncUser caller) {
		// TODO Auto-generated constructor stub
		this.caller = caller;
	}

	@Override
	protected ResultData doInBackground(SearchData... params) {
		// TODO Auto-generated method stub
		sd = params[0];

		String base_url = sd.getUrl();
		String format = sd.getFormat().toString();
		String search_text = sd.getInput();

		ResultData rd = null;

		HttpClient httpclient = new DefaultHttpClient();

		String url = base_url + "/" + format + "/" + search_text;
		Log.d(Constants.GEO_SERCH_LOG_TAG, "sending " + url);

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			Log.d(Constants.GEO_SERCH_LOG_TAG, response.getStatusLine()
					.toString());

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				instream.close();
				Log.d(Constants.GEO_SERCH_LOG_TAG, "fetched : " + result);
				rd = parseResult(sd.getFormat(), result);

				if (rd != null) {
					rd.setHostname(search_text);
				}
			}

		} catch (Exception e) {
			rd = null;
		}

		return rd;
	}

	private ResultData parseResult(DataFormat format, String result) {
		// TODO Auto-generated method stub

		// call data fetcher
		DataParser parser = new DataParser(format, result);
		return parser.parse();
	}

	@Override
	protected void onPostExecute(ResultData result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		caller.sendResult(result);
	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
