package com.rupam.geosearch.parsers;

import com.rupam.geosearch.DataFormat;
import com.rupam.geosearch.ResultData;

public class DataParser {

	private DataFormat format;
	private String resultString;

	public DataParser(DataFormat format, String resultString) {
		this.format = format;
		this.resultString = resultString;
	}

	public ResultData parse() {
		ResultData rd = null;

		if (format == DataFormat.json) {
			// call the json parser
			rd = new JSONParser(resultString).parse();
		} else if (format == DataFormat.xml) {
			// call xml parser
		}

		return rd;
	}
}
