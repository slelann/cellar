package com.mycellar.conditions.measure

class Measure implements Comparable {

	Date 	measureDate
	Float	celsiusTemperature
	Integer	humidity

	int compareTo(obj) {
		measureDate.compareTo(obj.measureDate)
	}
		
	static belongsTo = [serie: Serie]
	
    static constraints = {
		measureDate( nullable: false)
		humidity(range:0..100, nullable: false)
		celsiusTemperature(nullable:false)
    }
}
