package com.mycellar.conditions.measure

class Measure implements Comparable {

	Date 	measureDate
	Float	celsiusTemperature
	Integer	humidity

	int compareTo(obj) {
		measureDate.compareTo(obj.measureDate)
	}
	
	String toString() {
		StringBuffer sb = new StringBuffer()
		sb.append(measureDate).append(" - ")
		sb.append(celsiusTemperature).append("° - ")
		sb.append(humidity).append("%")
		return sb.toString()
	}
		
	static belongsTo = [serie: Serie]
	
    static constraints = {
		measureDate( nullable: false)
		humidity(range:0..100, nullable: false)
		celsiusTemperature(nullable:false)
    }
}
