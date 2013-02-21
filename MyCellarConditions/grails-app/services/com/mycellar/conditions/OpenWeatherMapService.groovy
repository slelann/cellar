package com.mycellar.conditions

import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication

import grails.converters.JSON
import wslite.rest.*

class OpenWeatherMapService {
	
	def grailsApplication = new DefaultGrailsApplication()
	
	def parseJsonResponse(String text) {
		def json = JSON.parse(text)
	}
	
	JSONObject getWeatherByCity() {
		def restClient = new RESTClient(grailsApplication.config.openweathermap.service.urlClient)
		def response = restClient.get(path:grailsApplication.config.openweathermap.service.pathWeather,
										accept: ContentType.JSON,
										query: [units: "metric"])
		def json = JSON.parse(response.text)
		return json
	}

}
