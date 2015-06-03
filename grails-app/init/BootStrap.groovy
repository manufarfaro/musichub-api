import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel
import com.google.api.services.youtube.model.ChannelListResponse
import com.musichub.Artist;
import com.musichub.Bar;
import com.musichub.Band;
import com.musichub.Role
import com.musichub.MHUser
import com.musichub.UserRole
import com.musichub.security.GoogleAuth;

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
