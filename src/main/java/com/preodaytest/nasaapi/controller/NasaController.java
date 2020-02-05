package com.preodaytest.nasaapi.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.preodaytest.nasaapi.resources.Measure;
import com.preodaytest.nasaapi.service.TemperatureService;

@RestController
@RequestMapping("/nasa")
public class NasaController {
	
	private final TemperatureService service;

	public NasaController(TemperatureService temperatureService) {
		this.service = temperatureService;
	}
	
	@RequestMapping("/temperature")
	 public Measure temperature(@RequestParam(value = "sol", required = false) String sol) 
			 throws IOException {
		return this.service.getTemperature(sol);
		
	 }

}