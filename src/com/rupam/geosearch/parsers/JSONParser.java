package com.rupam.geosearch.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import com.rupam.geosearch.ResultData;

public class JSONParser {
	private String jsonString;

	public JSONParser(String resultString) {
		this.jsonString = resultString;
	}

	public ResultData parse() {
		ResultData rd = null;

		try {

			JSONObject ob = new JSONObject(jsonString);

			rd = new ResultData();
			rd.setArea_code(ob.getString("area_code"));
			rd.setIp(ob.getString("ip"));
			rd.setRegion_code(ob.getString("region_code"));
			rd.setRegion_name(ob.getString("region_name"));
			rd.setCountry_code(ob.getString("country_code"));
			rd.setCountry_name(ob.getString("country_name"));
			rd.setCity(ob.getString("city"));
			rd.setZipcode(ob.getString("zipcode"));
			rd.setLatitude(ob.getDouble("latitude"));
			rd.setLongitude(ob.getDouble("longitude"));
			rd.setMetro_code(ob.getString("metro_code"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			rd = null;
		}

		return rd;
	}
}
