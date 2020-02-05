package com.preodaytest.nasaapi.service;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.preodaytest.nasaapi.NasaApiAppProperties;
import com.preodaytest.nasaapi.resources.Measure;
import com.preodaytest.nasaapi.resources.SolMeasure;

@Service
public class TemperatureService {
	
	private static final Logger logger = LoggerFactory.getLogger(TemperatureService.class);
	
	private static final String NASA_INSIGHT_URL =
			"https://api.nasa.gov/insight_weather/?sol={sol}&api_key={key}&feedtype=json&ver=1.0";

	private final RestTemplate restTemplate;

	private final String apiKey;
	
	public TemperatureService(RestTemplateBuilder restTemplateBuilder,
			NasaApiAppProperties properties) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = properties.getApi().getKey();
	}

	public Measure getTemperature(String sol) throws IOException {
		
		URI url = new UriTemplate(NASA_INSIGHT_URL).expand(sol, this.apiKey);
		
		String jsonString = invoke(url, String.class);
		JsonObject parsedJsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
		JsonArray solIds = (JsonArray) parsedJsonObject.get("sol_keys");
		
		if(sol != null && solIds.contains(new JsonPrimitive(sol))) {
			logger.info("#### We have ourselves the SOL {}", sol);
			SolMeasure solMeasure = getSolMeasureFromJson(parsedJsonObject, sol);
			return new Measure(getFormattedTemperature(getInFarenheit(solMeasure.getAt().getAv())));
			
		} else {
			logger.info("#### No usable SOL!");
			Double temperatureSum = 0.0;
			for (JsonElement solId : solIds) {
				SolMeasure solMeasure = getSolMeasureFromJson(parsedJsonObject, solId.getAsString());
				temperatureSum += solMeasure.getAt().getAv();
			}
			Double averageTemperature = temperatureSum/solIds.size();
			
			return new Measure(getFormattedTemperature(getInFarenheit(averageTemperature)));
		}
	}
	
	private <T> T invoke(URI url, Class<T> responseType) {
		RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);

		return exchange.getBody();
	}
	
	private Double getInFarenheit(Double celsiusTemperature) {
		return (celsiusTemperature * 9/5) + 32;
	}
	
	private String getFormattedTemperature(Double value) {
		return String.format("%.2f", value);
	}
	
	private SolMeasure getSolMeasureFromJson(JsonObject parsedJson, String solId) {
		JsonElement solMeasureJsonElement = parsedJson.get(solId);
		SolMeasure solMeasure = new Gson().fromJson(solMeasureJsonElement, SolMeasure.class);
		
		return solMeasure;
	}

}
