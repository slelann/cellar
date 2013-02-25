package com.mycellar.conditions

class VisualizationController {

	def index = {}
	
    def render = {
		print "visualization render"
        def myColumns = [['date', 'Date'], ['number', 'Temperature']]
        def myData = [['2013-02-07 19:05:00', 11], ['2013-02-07 19:06:00', 2], ['2013-02-07 19:07:00', 2], 
			['2013-02-07 19:08:00', 2], ['2013-02-07 19:09:00', 7]]
        render template: "chart", model: ["myColumns": myColumns, 
                                          "mysData": myData]
    }
	
	
}
