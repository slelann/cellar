package com.mycellar.conditions

import grails.converters.JSON
import wslite.rest.*

class OpenWeatherMapController {
	
	def index() { 
		def restClient = new RESTClient('http://api.openweathermap.org/data/2.1/')
		def response = restClient.get(path:'/weather/city/3032965', 
										accept: ContentType.JSON,
										query: [units: "metric"])		
		redirect(action:"parseJsonResponse", params:[text: response.text])
	}
	
	def parseJsonResponse(String text) {
		def json = JSON.parse(text)
		render json.main.humidity
	}
	
}
