import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel
import com.google.api.services.youtube.model.ChannelListResponse
import com.musichub.Artist;
import com.musichub.Bar;
import com.musichub.Band;
import com.musichub.Country;
import com.musichub.QuotesController;
import com.musichub.Role
import com.musichub.MHUser
import com.musichub.UserRole
import com.musichub.security.GoogleAuth;
import com.musichub.util.CountryDataGenerator;
import com.musichub.util.QuoteDataGenerator;
import com.musichub.util.RoleDataGenerator;

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
		Environment.executeForCurrentEnvironment {
			development {
				this.generateDummyData()

				try {
					Credential googleCredentials = GoogleAuth.authorize()
					
//					Circunstantial - only to know how service works
//					Drive drive = new Drive.Builder(
//		                GoogleAuth.HTTP_TRANSPORT, 
//						GoogleAuth.JSON_FACTORY, 
//						googleCredentials)
//		                .setApplicationName("musichub-api")
//		                .build()
//					println("drive files: ${drive.files().list().setMaxResults(10).execute().getItems()}")

//					YouTube youtube = new YouTube.Builder(
//						GoogleAuth.HTTP_TRANSPORT,
//						GoogleAuth.JSON_FACTORY,
//						googleCredentials
//					).setApplicationName("youtube-cmdline-myuploads-sample")
//					.build();
//
//					YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
//					channelRequest.setMine(true);
//					channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
//					ChannelListResponse channelResult = channelRequest.execute();
//
//					List<Channel> channelsList = channelResult.getItems();
//
//					println("Youtube Channel List: ${channelsList}")
				} catch(IOException exception) {
					System.out.println("IOException: " + exception.getMessage())
				}
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
		// Country
		CountryDataGenerator.generate()
		
		//Quotes
		QuoteDataGenerator.generate()

		// Roles
		RoleDataGenerator.generate()

		// Admin User (For Development & Testing)
		MHUser userAdmin = new MHUser(
			username:	'administrator',
			password:	'admin123',
			email:		'mhubofficial@gmail.com',
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
			username:	'benditabar',
			password:	'bbar1234',
			email:		'benditabar@gmail.com',
			name:		'Bendita Bar',
			enabled:	true
		).save(flush: true)

		// User - Role
		UserRole userRole1a = new UserRole(
			user: Artist.findByEmail("chucknorris@gmail.com"),
			role: Role.findByAuthority('ROLE_ARTIST')
		).save(flush: true)
		UserRole userRole1b = new UserRole(
			user: Artist.findByEmail("benditabar@gmail.com"),
			role: Role.findByAuthority('ROLE_BAR')
		).save(flush: true)
		UserRole userRole1c = new UserRole(
			user: Artist.findByEmail("mhubofficial@gmail.com"),
			role: Role.findByAuthority('ROLE_ADMIN')
		).save(flush: true)

		//Bands
		Band chuckyband = new Band(
			name:	'Chucky the band',
			slug:	'chuckyandtheband',
			email:	'chucknorris@gmail.com',
			leader:	Artist.findByEmail("chucknorris@gmail.com")
		)
			.addToArtists(Artist.findByEmail("chucknorris@gmail.com"))
			.save(flush: true)
	}
		
}
