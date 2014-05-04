package com.rupam.geosearch;

public class ResultData {
	private String ip;
	private String hostname;
	private String country_code;
	private String country_name;
	private String region_code;
	private String region_name;
	private String city;
	private String zipcode;
	private double latitude;
	private double longitude;
	private String metro_code;
	private String area_code;

	public ResultData() {
		// TODO Auto-generated constructor stub
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		if (hostname != null)
			this.hostname = hostname;
		else
			this.hostname = "";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		if (ip != null)
			this.ip = ip;
		else
			this.ip = "";
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		if (country_code != null)
			this.country_code = country_code;
		else
			this.country_code = "";

	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		if (country_name != null)
			this.country_name = country_name;
		else
			this.country_name = "";
	}

	public String getRegion_code() {
		return region_code;
	}

	public void setRegion_code(String region_code) {
		if (region_code != null)
			this.region_code = region_code;
		else
			this.region_code = "";

	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		if (region_name != null)
			this.region_name = region_name;
		else
			this.region_name = "";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city != null)
			this.city = city;
		else
			this.city = "";
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		if (zipcode != null)
			this.zipcode = zipcode;
		else
			this.zipcode = "";

	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMetro_code() {
		return metro_code;
	}

	public void setMetro_code(String metro_code) {
		if (metro_code != null)
			this.metro_code = metro_code;
		else
			this.metro_code = "";

	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		if (area_code != null)
			this.area_code = area_code;
		else
			this.area_code = "";
	}
}
