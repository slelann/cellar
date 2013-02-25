package com.mycellar.conditions

import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication

import com.mycellar.conditions.measure.OpenWeatherMapMeasure
import com.mycellar.conditions.measure.Serie
import com.mycellar.conditions.measure.Measure


import grails.converters.JSON
import wslite.rest.*

class OpenWeatherMapService {
	
	def grailsApplication = new DefaultGrailsApplication()
	def restClient = new RESTClient(grailsApplication.config.openweathermap.service.urlClient)
	
	def parseJsonResponse(String text) {
		def json = JSON.parse(text)
	}
	
	JSONObject getWeatherByCity() {
		def response = restClient.get(path:grailsApplication.config.openweathermap.service.pathWeather,
										accept: ContentType.JSON,
										query: [units: "metric"])
		def json = JSON.parse(response.text)
		
		Serie serieExt = Serie.findByPlace("Chevaigne")
		
		OpenWeatherMapMeasure owmMesure = new OpenWeatherMapMeasure(measureDate: new Date().parse("yyyy-MM-dd HH:mm:ss", json.date),
																	celsiusTemperature: json.main.temp,
																	humidity:json.main.humidity,
																	pressure: json.main.pressure,
																	cloudsCover:json.clouds.all,
																	temp_max:json.main.temp_max,
																	temp_min:json.main.temp_min,
																	weatherDescription:json.weather[0].description,
																	weatherIconUrl:json.weather[0].icon,
																	weatherMain:json.weather[0].main,
																	windDegree:json.wind.deg,
																	windSpeed:json.wind.speed
																	)
		owmMesure.setSerie(serieExt)
		owmMesure.save(failOnError: true)
		print owmMesure.toString()
		
		//return json
	}

}
