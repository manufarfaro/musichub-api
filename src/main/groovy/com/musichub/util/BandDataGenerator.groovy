package com.musichub.util

import com.musichub.Artist
import com.musichub.Band
import com.musichub.Bar;
import com.musichub.Disc;
import com.musichub.Photo;
import com.musichub.Track;
import com.musichub.Video;

class BandDataGenerator {
	public static void generate() {
		Artist artistChuckNorris = Artist.findByUsername("chucknorris")
		
		Band chuckyband = new Band(
			name:	'Chucky the band',
			slug:	'chuckyandtheband',
			email:	'chucknorris@gmail.com',
			leader: artistChuckNorris,
			bio: 'Somos una banda de zona sur, hacemos rock, pop, blues y algo de salsa.',
			googlePlusId: '+chucknorris',
			facebookId: 'chuck.norris'
		).addToArtists(artistChuckNorris)
		.save(flush: true)

		this.addPhotos(chuckyband)
		this.addDiscs(chuckyband)
		this.addVideos(chuckyband)
	}
	
	protected static void addPhotos(Band band) {
		
		Photo photoA1 = new Photo (
			title: 	'funny dog 1',
			fileId: '0B7q228Dq2WUuNk41QVNLTjBHdW8'
		)

		Photo photoA2 = new Photo (
			title: 	'funny dog 2',
			fileId: '0B7q228Dq2WUuVUlUSUEtR0pkWEk'
		)

		band
			.addToPhotos(photoA1)
			.addToPhotos(photoA2)
			.save(flush:true)
	}
	protected static void addVideos(Band band) {

		Video nyanCatVideo = new Video (
			title: 'nyan again 30 seconds :)',
			fileId: 'QH2-TGUlwu4'
		)

		band.addToVideos(nyanCatVideo).save(flush:true)
	}

	protected static void addDiscs(Band band) {
		
		Track trackSiMeAbrazas = new Track(
			name: 'Si me abrazas',
			orderNbr: 1,
			fileId: '0B7q228Dq2WUuOEFua3dpb21HeWs'
		)
		
		Track trackMariela = new Track(
			name: 'Mariela',
			orderNbr: 2,
			fileId: '0B7q228Dq2WUucnh2OEl4OUllNms'
		)
		
		Track trackDime = new Track(
			name: 'Dime',
			orderNbr: 3,
			fileId: '0B7q228Dq2WUua1MyTE02R2NCcDg'
		)
		
		Photo isThisPossibleArtwork = new Photo(
			title: 'cover',
			fileId: '0B7q228Dq2WUubloxbjZlX0E0RXc'
		).save(flush: true)
		Disc demoLunchera = new Disc(
			name: "is this possible?",
			artwork: isThisPossibleArtwork
		)

		demoLunchera.addToTracks(trackSiMeAbrazas)
		demoLunchera.addToTracks(trackMariela)
		demoLunchera.addToTracks(trackDime)
		
		band.addToDiscs(demoLunchera).save(flush: true)
	}
}
