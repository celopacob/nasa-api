package com.preodaytest.nasaapi.resources;

public class At {

	private Double av;
	private Double ct;
	private Double mn;
	private Double mx;

	public At() {}
	public At(Double av, Double ct, Double mn, Double mx) {
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
	public void setAv(Double av) {
		this.av = av;
	}
	public void setCt(Double ct) {
		this.ct = ct;
	}
	public void setMn(Double mn) {
		this.mn = mn;
	}
	public void setMx(Double mx) {
		this.mx = mx;
	}
	
	
}