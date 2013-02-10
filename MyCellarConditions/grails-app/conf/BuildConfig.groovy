grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

System.properties.putAll([
	"http.proxyHost": "proxybkp",
	 "http.proxyPort": "8080",
	 "http.proxyUserName": "rlhw9764",
	 "http.proxyPassword": "ma1T!t02*"
 ])

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "info" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		mavenRepo "http://maven.springframework.org/milestone/"
		
		
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        // runtime 'mysql:mysql-connector-java:5.1.20'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.3"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"

        runtime ":database-migration:1.2.1"

        compile ':cache:1.0.1'
		
		// My needed plugins
		// Cloud foundry
		compile ":cloud-foundry:1.2.3"
		compile ":cloud-foundry-ui:1.1"
			// cloud foundry ui deps
			compile ":google-visualization:0.6.1"
			compile ":webxml:1.4.1"
		//Spring security
		compile ":spring-security-core:1.2.7.3"
		compile ":spring-security-ui:0.2"
			//Spring security-ui deps
			compile ":mail:1.0.1"
			compile ":jquery:1.8.3"
			compile ":jquery-ui:1.8.24"
			compile ":famfamfam:1.0.1"
		// Twitter bootstrap
		compile ":twitter-bootstrap:2.2.2"
		// GITHUB
		//build ":git:1.0-SNAPSHOT"
		//CSV
		compile ":csv:0.3.1"
		compile ":modernizr:2.0.6"
		compile ":uploadr:0.6.0.1"


    }
	
}
