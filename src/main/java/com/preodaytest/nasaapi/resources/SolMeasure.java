package com.preodaytest.nasaapi.resources;

import java.util.Date;

import com.google.gson.annotations.SerializedName;


public class SolMeasure {

	@SerializedName("AT")
	private final At at;
	
	@SerializedName("First_UTC")
	private final Date firstUTC;
	
	@SerializedName("HWS")
	private final Hws hws;
	
	@SerializedName("Last_UTC")
	private final Date lastUTC;
	
	@SerializedName("PRE")
	private final Pre pre;
	
	@SerializedName("Season")
	private final String season;
	
	
	public SolMeasure(At at, Date firstUTC, Hws hws, Date lastUTC, Pre pre, String season) {
		this.at = at;
		this.firstUTC = firstUTC;
		this.hws = hws;
		this.lastUTC = lastUTC;
		this.pre = pre;
		this.season = season;
	}

	public At getAt() {
		return at;
	}

	public Date getFirstUTC() {
		return firstUTC;
	}

	public Hws getHws() {
		return hws;
	}

	public Date getLastUTC() {
		return lastUTC;
	}

	public Pre getPre() {
		return pre;
	}

	public String getSeason() {
		return season;
	}
	
}