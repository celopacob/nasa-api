package com.preodaytest.nasaapi.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pre {

	private final Double av;
	private final Double ct;
	private final Double mn;
	private final Double mx;

	public Pre(Double av, Double ct, Double mn, Double mx) {
		this.av = av;
		this.ct = ct;
		this.mn = mn;
		this.mx = mx;
	}

	public Double getAv() {
		return av;
	}

	public Double getCt() {
		return ct;
	}

	public Double getMn() {
		return mn;
	}

	public Double getMx() {
		return mx;
	}
	
}