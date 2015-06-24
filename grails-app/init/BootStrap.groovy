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
import com.musichub.util.ArtistDataGenerator;
import com.musichub.util.BandDataGenerator;
import com.musichub.util.BarDataGenerator;
import com.musichub.util.CountryDataGenerator;
import com.musichub.util.MHUserDataGenerator;
import com.musichub.util.QuoteDataGenerator;
import com.musichub.util.RoleDataGenerator;

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
		Environment.executeForCurrentEnvironment {
			development {
				this.generateDummyData()

//				try {
//					Credential googleCredentials = GoogleAuth.authorize()
					
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
//				} catch(IOException exception) {
//					System.out.println("IOException: " + exception.getMessage())
//				}
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

		// Administrator User (For Development & Testing)
		MHUserDataGenerator.generate()

		// Artists
		ArtistDataGenerator.generate()

		// Bar
		BarDataGenerator.generate()

		//Bands
		BandDataGenerator.generate()
	}
		
}
