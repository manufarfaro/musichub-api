package com.musichub.util


import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import com.musichub.Artist
import com.musichub.Bar;
import com.musichub.Country;
import com.musichub.Disc;
import com.musichub.Photo;
import com.musichub.Postulate;
import com.musichub.Role
import com.musichub.Track;
import com.musichub.UserRole
import com.musichub.Video;

class ArtistDataGenerator {

	private static final Log logger = LogFactory.getLog(getClass())

	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")

	public static void generate() {
		Artist artistChuckNorris = new Artist(
			slug:			'chucknorris',
			username: 		'chucknorris',
			password: 		'Chuck123',
			email:			'chucknorris@gmail.com',
			name: 			'Chuck',
			lastname: 		'Norris',
			bio:			'I\'m the melmac\'s chucknorris...',
			gender:			'an omnipresent coffeelover guy',
			region:			Country.findByIso3('ARG'),
			birthdate:		formatter.parse('01/05/1991'),
			codePhone:		'011',
			phone:			'4543-4344',
			googlePlusId:	'+chucknorris',
			facebookId:		'chuck.norris',
			twitterId:		'chuck.norris',
			website:		'http://chucknorris.me',
			enabled: true
		)
		
			artistChuckNorris.save()

			UserRole userRole1a = new UserRole(
				user: artistChuckNorris,
				role: Role.findByAuthority('ROLE_ARTIST')
			).save(flush: true)

			this.addPostulations(artistChuckNorris)
			this.addVideos(artistChuckNorris)
			this.addPhotos(artistChuckNorris)
			this.addDiscs(artistChuckNorris)
			
		Artist artistMadMax = new Artist(
			slug:			'themadmax',
			username: 		'themadmax',
			password: 		'Madmax123',
			email:			'madmax@gmail.com',
			name: 			'Max',
			lastname: 		'Mad',
			bio:			'The madmax...',
			gender:			'Im the mad max',
			region:			Country.findByIso3('USA'),
			birthdate:		formatter.parse('05/11/1975'),
			codePhone:		'011',
			phone:			'4435-4434',
			googlePlusId:	'+madmax',
			facebookId:		'mad.max',
			twitterId:		'mad.max',
			website:		'http://madmax.me',
			enabled: true
		)
		
			artistMadMax.save()

			UserRole userRole1a2 = new UserRole(
				user: artistMadMax,
				role: Role.findByAuthority('ROLE_ARTIST')
			).save(flush: true)

			this.addPostulationsMad(artistMadMax)
			this.addVideosMad(artistMadMax)
			this.addPhotosMad(artistMadMax)
			this.addDiscsMad(artistMadMax)
			
		Artist artistRichardMad = new Artist(
			slug:			'therichard',
			username: 		'therichard',
			password: 		'therichard123',
			email:			'therichard@gmail.com',
			name: 			'Richard',
			lastname: 		'Cox',
			bio:			'The Richard...',
			gender:			'Im the mad Richard',
			region:			Country.findByIso3('USA'),
			birthdate:		formatter.parse('29/07/1959'),
			codePhone:		'011',
			phone:			'3515-7516',
			googlePlusId:	'+therichard',
			facebookId:		'therichard.mad',
			twitterId:		'therichard.mad',
			website:		'http://therichard.me',
			enabled: true
		)
		
			artistRichardMad.save()

			UserRole userRole1a3 = new UserRole(
				user: artistRichardMad,
				role: Role.findByAuthority('ROLE_ARTIST')
			).save(flush: true)
			
		Artist artistDaveMad = new Artist(
			slug:			'davemustein',
			username: 		'davemustein',
			password: 		'davemustein123',
			email:			'davemustein@gmail.com',
			name: 			'Dave',
			lastname: 		'Mustein',
			bio:			'The Creator of rush...',
			gender:			'Im the mad Dave',
			region:			Country.findByIso3('USA'),
			birthdate:		formatter.parse('12/01/1963'),
			codePhone:		'011',
			phone:			'9572-8415',
			googlePlusId:	'+davemustein',
			facebookId:		'davemustein.mad',
			twitterId:		'davemustein.mad',
			website:		'http://davemustein.me',
			enabled: true
		)
		
			artistDaveMad.save()

			UserRole userRole1a4 = new UserRole(
				user: artistDaveMad,
				role: Role.findByAuthority('ROLE_ARTIST')
			).save(flush: true)
	}
	
	protected static void addVideos(Artist artist) {
		Video videoPromo = new Video(
			title: 'Promo Banda',
			fileId: 'dxygkyyloux1oixgvnpf',
			format: 'mp4',
			url: 'https://res.cloudinary.com/musichub/video/upload/v1435740103/dxygkyyloux1oixgvnpf.mp4'
		)
		artist
			.addToVideos(videoPromo)
			.save(flush: true)
	}

	protected static void addPhotos(Artist artist) {
		Photo photoPick = new Photo(
			title: 'pick',
			fileId: '0B7q228Dq2WUuQmhkMXFONzVYazQ'
		)
		Photo photoCar = new Photo(
			title: 'bmw car',
			fileId: '0B7q228Dq2WUuNlRVOGZ5RG5wMGc'
		)
		artist
			.addToPhotos(photoPick)
			.addToPhotos(photoCar)
			.save(flush: true)		
	}

	protected static void addDiscs(Artist artist) {
		
		Track trackPareceFacil = new Track(
			name: StringEscapeUtils.escapeHtml('Parece Fácil'),
			orderNbr: '1',
			fileId: '0B7q228Dq2WUuQlMzTU1OMjRLZFE'
		)
		
		Track trackSonie = new Track(
			name: StringEscapeUtils.escapeHtml('Soñé'),
			orderNbr: '2',
			fileId: '0B7q228Dq2WUucGR6eFZ3aGRualU'
		)
		
		Track trackVientos = new Track(
			name: 'Vientos',
			orderNbr: '3',
			fileId: '0B7q228Dq2WUub05CLURCbTNOYUE'
		)
		
		Photo luncheraArtwork = new Photo(
			title: 'cover',
			fileId: '0B7q228Dq2WUucWZWaXVibXJkRTQ'
		).save(flush: true)
		Disc demoLunchera = new Disc(
			name: "Lunchera EP",
			artwork: luncheraArtwork
		)

		demoLunchera.addToTracks(trackPareceFacil)
		demoLunchera.addToTracks(trackSonie)
		demoLunchera.addToTracks(trackVientos)
		
		artist.addToDiscs(demoLunchera).save(flush: true)
	}

	protected static void addPostulations(Artist artist) {
		Postulate postulationRustico = new Postulate(
			title:	'Chucknorris & The Band busca baterista',
			text:	'Buscamos bater@ equipado que quiera salir a tocar, tenemos temas ya armados y compromiso al 110%, somos de zona Sur (Banfield y aledaños), estrellitas abstenerse, buscamos a alguien copado.',
		)
		artist.addToPostulations(postulationRustico).save(flush: true)
	}
	
	protected static void addVideosMad(Artist artist) {
		Video videoPromoMad = new Video(
			title: 'Promocion de Banda MadMax',
			fileId: 'dxygkyyloux1oixgvnpf',
			format: 'mp4',
			url: 'https://res.cloudinary.com/musichub/video/upload/v1435740103/dxygkyyloux1oixgvnpf.mp4'
		)
		artist
			.addToVideos(videoPromoMad)
			.save(flush: true)
	}

	protected static void addPhotosMad(Artist artist) {
		Photo photoPickMad = new Photo(
			title: 'foto de madmax',
			fileId: '0B7q228Dq2WUuQmhkMXFONzVYazQ'
		)
		
		artist
			.addToPhotos(photoPickMad)			
			.save(flush: true)
		
	}

	protected static void addDiscsMad(Artist artist) {
		
		Track trackBeliveIt = new Track(
			name: StringEscapeUtils.escapeHtml('Belive it'),
			orderNbr: '1',
			fileId: '0B7q228Dq2WUucGR6eFZ3aGRualU'
		)
		
		Track trackSolarWinds = new Track(
			name: 'Solar Winds',
			orderNbr: '2',
			fileId: '0B7q228Dq2WUub05CLURCbTNOYUE'
		)
		
		Photo theMadArtwork = new Photo(
			title: 'cover version',
			fileId: '0B7q228Dq2WUucWZWaXVibXJkRTQ'
		).save(flush: true)
		Disc demoRiot = new Disc(
			name: "Riot EP",
			artwork: theMadArtwork
		)

		demoRiot.addToTracks(trackBeliveIt)
		demoRiot.addToTracks(trackSolarWinds)
		
		artist.addToDiscs(demoRiot).save(flush: true)
	}

	protected static void addPostulationsMad(Artist artist) {
		Postulate postulationMadGuitarrist = new Postulate(
			title:	'MadMax looking guitarist',
			text:	'Seek equipped guitarist, looking for someone wanting cornered go out and play live.',
		)
		artist.addToPostulations(postulationMadGuitarrist).save(flush: true)
	}
}
