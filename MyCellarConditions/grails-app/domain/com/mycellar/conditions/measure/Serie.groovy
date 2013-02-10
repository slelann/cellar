package com.mycellar.conditions.measure

class Serie {

	String 	serieName
	String 	place
	SortedSet measures
	
	static hasMany = [measures: Measure]

	@Override
	public String toString() {
		return serieName;
	}
	
    static constraints = {
		serieName(nullable: false)
		place(nullable: false)
    }
}
