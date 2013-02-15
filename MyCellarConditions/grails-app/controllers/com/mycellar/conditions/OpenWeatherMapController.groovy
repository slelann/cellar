package com.mycellar.conditions

class OpenWeatherMapController {

    def index() { }
	
	def requestOWM() {

		withRest(url: 'http://api.openweathermap.org/data/2.1/') {
			def response = get(path: '/weather/city/3032965', query: [units: 'metric'])
			println response.json
		}
	}
	
}
