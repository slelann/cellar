modules = {
    application {
        resource url:'js/application.js'
    }
}

grails.resources.modules = {
	
		bootstrap {
			resource url:'less/custom-bootstrap.less',attrs:[rel: "stylesheet/less", type:'css']
			dependsOn 'jquery'
		}
	
	}
