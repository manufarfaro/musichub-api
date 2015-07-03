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
}
