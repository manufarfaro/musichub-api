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
		
		Artist artistMadMax = Artist.findByUsername("themadmax")
		
		Band madmaxband = new Band(
			name:	'Mad Max & the riot band',
			slug:	'madmaxtheband',
			email:	'madmax@gmail.com',
			leader: artistMadMax,
			bio: 'We\'re a band from California, we hardrock & blues.',
			googlePlusId: '+madmax',
			facebookId: 'mad.max'
		).addToArtists(artistMadMax)
		
		.save(flush: true)

		this.addPhotosMadBand(madmaxband)
		this.addVideosMadBand(madmaxband)
		this.addDiscsMadBand(madmaxband)
		this.addMembersMadMaxBand(madmaxband)		
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

	public static void generateMadMaxBand() {
		Artist artistMadMax = Artist.findByUsername("themadmax")		
		
		Band madmaxband = new Band(
			name:	'Mad Max & the riot band',
			slug:	'madmaxtheband',
			email:	'madmax@gmail.com',
			leader: artistMadMax,
			bio: 'We\'re a band from California, we hardrock & blues.',
			googlePlusId: '+madmax',
			facebookId: 'mad.max'
		).addToArtists(artistMadMax)
		
		.save(flush: true)		

		this.addPhotosMadBand(madmaxband)
		this.addVideosMadBand(madmaxband)
		this.addDiscsMadBand(madmaxband)
		this.addMembersMadMaxBand(madmaxband)
	}
	
	protected static void addMembersMadMaxBand(Band band)
	{
		Artist artistRichardMad = Artist.findByUsername("therichard")
		Artist artistDaveMad = Artist.findByUsername("davemustein")
		band
			.addToArtists(artistRichardMad)
			.addToArtists(artistDaveMad)
	}
	
	protected static void addPhotosMadBand(Band band) {
		
		Photo photoMad1 = new Photo (
			title: 	'funny dog 1',
			fileId: '0B7q228Dq2WUuNk41QVNLTjBHdW8'
		)

		Photo photoMad2 = new Photo (
			title: 	'funny dog 2',
			fileId: '0B7q228Dq2WUuVUlUSUEtR0pkWEk'
		)

		band
			.addToPhotos(photoMad1)
			.addToPhotos(photoMad2)
			.save(flush:true)
	}
	
	protected static void addVideosMadBand(Band band) {

		Video annoyingCatVideo = new Video (
			title: 'annoying cat:)',
			fileId: 'QH2-TGUlwu4'
		)

		band.addToVideos(annoyingCatVideo).save(flush:true)
	}
	
	protected static void addDiscsMadBand(Band band) {
		
		Track trackIfICouldFly = new Track(
			name: 'If i could fly',
			orderNbr: 1,
			fileId: '0B7q228Dq2WUuOEFua3dpb21HeWs'
		)
		
		Track trackRip = new Track(
			name: 'R.I.P',
			orderNbr: 2,
			fileId: '0B7q228Dq2WUucnh2OEl4OUllNms'
		)
		
		Track trackHammerDown = new Track(
			name: 'Hammer Down',
			orderNbr: 3,
			fileId: '0B7q228Dq2WUua1MyTE02R2NCcDg'
		)
		
		Track trackProtector = new Track(
			name: 'Protector',
			orderNbr: 4,
			fileId: '0B7q228Dq2WUua1MyTE02R2NCcDg'
		)
		
		Track trackSetMeFree = new Track(
			name: 'Set Me Free',
			orderNbr: 5,
			fileId: '0B7q228Dq2WUua1MyTE02R2NCcDg'
		)
		
		Track trackRiot = new Track(
			name: 'Riot',
			orderNbr: 6,
			fileId: '0B7q228Dq2WUua1MyTE02R2NCcDg'
		)
		
		Photo isThisPossibleArtwork2 = new Photo(
			title: 'cover',
			fileId: '0B7q228Dq2WUubloxbjZlX0E0RXc'
		).save(flush: true)
		Disc demoSavage = new Disc(
			name: "Rustless",
			artwork: isThisPossibleArtwork2
		)

		demoSavage.addToTracks(trackIfICouldFly)
		demoSavage.addToTracks(trackRip)
		demoSavage.addToTracks(trackHammerDown)
		demoSavage.addToTracks(trackProtector)
		demoSavage.addToTracks(trackSetMeFree)
		demoSavage.addToTracks(trackRiot)
		
		band.addToDiscs(demoSavage).save(flush: true)
	}
}