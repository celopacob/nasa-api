package com.preodaytest.nasaapi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.temperature")
public class NasaApiAppProperties {
	@Valid
	private final Api api = new Api();

	public Api getApi() {
		return this.api;
	}

	public static class Api {

		/**
		 * API key of the Nasa Mars Temperature service.
		 */
		@NotNull
		private String key;

		public String getKey() {
			return this.key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}
}