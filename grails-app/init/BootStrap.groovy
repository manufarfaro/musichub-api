import com.musichub.Artist;
import com.musichub.Bar;
import com.musichub.Band;
import com.musichub.Role
import com.musichub.MHUser
import com.musichub.UserRole

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
		Environment.executeForCurrentEnvironment {
			development {
				this.generateDummyData()
			}
			test {
				this.generateDummyData()
			}
			production {
				// Do nothing.
			}
		}
			
    }
    def destroy = {
    }
	private void generateDummyData(){
		// Roles
		Role roleArtist = new Role(
			authority: 'ROLE_ARTIST'
		).save(flush: true)
		Role roleBar = new Role(
			authority: 'ROLE_BAR'
		).save(flush: true)

		// Artists
		Artist artistChuckNorris = new Artist(
			slug:		'chucknorris',
			username: 	'chucknorris',
			password: 	'Chuck123',
			email:		'chucknorris@gmail.com',
			name: 		'Chuck',
			lastname: 	'Norris',
			enabled: true
		).save(flush: true)

		// Bar
		Bar barBendita = new Bar(
			slug:		'benditabar',
			username:	'benditabar',
			password:	'bbar1234',
			email:		'benditabar@gmail.com',
			name:		'Bendita Bar',
			enabled:	true
		).save(flush: true)

		// User - Role
		UserRole userRole1a = new UserRole(
			user: artistChuckNorris,
			role: roleArtist
		).save(flush: true)
		UserRole userRole1b = new UserRole(
			user: barBendita,
			role: roleBar
		).save(flush: true)

		//Bands
		Band chuckyband = new Band(
			name:	'Chucky the band',
			slug:	'chuckyandtheband',
			leader:	artistChuckNorris
		)
			.addToArtists(artistChuckNorris)
			.save(flush: true)
	}
		
}
