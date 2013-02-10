package com.mycellar.conditions.measure

class Measure {

	Date 	measureDate
	Integer	celsiusTemperature
	Integer	humidity

	int compareTo(obj) {
		measureDate.compareTo(obj.measureDate)
	}
		
	static belongsTo = [serie: Serie]
	
    static constraints = {
		measureDate( nullable: false)
		humidity(range:0..100, nullable: false)
		celsiusTemperature(nullable:false, range:-20..40)
    }
}
