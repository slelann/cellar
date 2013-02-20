package com.mycellar.conditions

import grails.converters.JSON
import wslite.rest.*

class OpenWeatherMapController {
	
	def index() { 
		redirect(action: "execute")
	}
	
	def parseJsonResponse(String text) {
		def json = JSON.parse(text)
		render json.main.humidity
	}
	
	def execute() {
		def restClient = new RESTClient('http://api.openweathermap.org/data/2.1/')
		def response = restClient.get(path:'/weather/city/3032965',
										accept: ContentType.JSON,
										query: [units: "metric"])
		def json = JSON.parse(response.text)
		redirect(action:"parseJsonResponse", params:[text: response.text])
	}
}
