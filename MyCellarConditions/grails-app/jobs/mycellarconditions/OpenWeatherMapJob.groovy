package mycellarconditions



class OpenWeatherMapJob {
    static triggers = {
      simple name:"hourly", startDelay: 60000, repeatInterval: 5000l // execute job once in 5 seconds
    }

	def group = "MyGroup"
	
    def execute() {
        // execute job
		print "Job run!"
    }
}
