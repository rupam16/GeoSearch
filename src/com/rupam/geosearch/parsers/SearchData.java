package com.rupam.geosearch.parsers;

import com.rupam.geosearch.DataFormat;

public class SearchData {
	private String url;
	private String input;
	private DataFormat format;

	public SearchData(String url, DataFormat format, String input) {
		super();
		this.input = input;
		this.format = format;
		this.url = url;
	}

	public String getInput() {
		return input;
	}

	public String getUrl() {
		return url;
	}

	public DataFormat getFormat() {
		return format;
	}
}
