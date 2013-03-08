import com.mycellar.conditions.measure.Serie
import com.mycellar.conditions.user.Role;
import com.mycellar.conditions.user.User;
import com.mycellar.conditions.user.UserRole;

class BootStrap {

    def init = { servletContext ->
    		//ROLES+USERS
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true) 
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		def testUser = new User(username: 'slelann',  enabled: true, password: 'ma1T!t02') 
		testUser.save(flush: true)
		UserRole.create testUser, adminRole, true
		assert User.count() == 1 
		assert Role.count() == 2 
		assert UserRole.count() == 1
	    	//SERIES
		def serieCSV = new Serie(serieName: 'Temperature/Humidite Cave', place: 'Maison' ).save(flush: true)
		def serieExt = new Serie(serieName: 'Temperature/Humidite Exterieure', place: 'Chevaigne').save(flush: true)
		assert Serie.count() == 2
        
    }
	
    def destroy = {
    }
}
