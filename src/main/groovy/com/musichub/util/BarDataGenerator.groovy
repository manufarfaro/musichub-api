package com.musichub.util

import java.text.SimpleDateFormat

import com.musichub.Bar
import com.musichub.Event
import com.musichub.Photo
import com.musichub.Postulate;
import com.musichub.Role
import com.musichub.UserRole
import com.musichub.Video


class BarDataGenerator {
	
	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")
	
	public static void generate() {

		Bar barRustico = new Bar (
			username:		'rusticorestobar',
			description:	'Un espacio para disfrutar y compartir las mayores experiencias gastronómicas.',
			address:		'Av. Saenz 325',
			phone:			'4484-2789',
			googlePlusId:	'+rusticorestobsas',
			facebookId:		'rusticobarbsas',
			password:		'resto325',
			email:			'rusticorestobar@gmail.com',
			name:			'Rústico Restó Bar',
			enabled:		true
		).save(flush: true)

		UserRole userRole1b = new UserRole(
			user: barRustico,
			role: Role.findByAuthority('ROLE_BAR')
		).save(flush: true)
		
		this.addPhotos(barRustico)
		this.addVideos(barRustico)
		this.addEvents(barRustico)
		this.addPostulations(barRustico)
		
		Bar barTheVomit = new Bar (
			username:		'vomitobar',
			description:	'A space to enjoy and share the greatest gastronomic experiences.',
			address:		'209 Larkin St',
			phone:			'8453-3248',
			googlePlusId:	'+thevomitrestobar',
			facebookId:		'thevomitrestobar',
			password:		'vomit123',
			email:			'thevomitrestobar@gmail.com',
			name:			'The Vomit Bar',
			enabled:		true
		).save(flush: true)

		UserRole userRole1c = new UserRole(
			user: barTheVomit,
			role: Role.findByAuthority('ROLE_BAR')
		).save(flush: true)
		
		this.addPhotosTheVomitBar(barTheVomit)
		this.addVideosTheVomitBar(barTheVomit)
		//this.addEventsTheVomitBar(barTheVomit)
		this.addPostulationsTheVomitBar(barTheVomit)
	}

	protected static void addEvents(Bar bar) {

		Photo flyerChuckEventA1 = new Photo (
			title: 'flyer chuck en rustico',
			fileId: '0B7q228Dq2WUubDREUmVkZzMwQ0E'
		).save(flush:true)

		Event chuckEventA1 = new Event (
			name: 'ChuckNorris en Rústico Bar',
			date: formatter.parse('01/09/2015'),
			text: 'El 01/09 ChuckNorris en Rústico presentando su nuevo disco "Nice with you!", festival de jazz y humor corpóreo. Entradas en puerta $35.',
			url: 'https://facebook.com/rusticobarbsas',
			photo: flyerChuckEventA1,
			bar: bar
		)
		
		bar.addToEvents(chuckEventA1).save(flush: true)
	}
	
	protected static void addPhotos(Bar bar) {

		Photo photoA1 = new Photo (
			title: 	'bar',
			fileId: '0B7q228Dq2WUuQWlMVWlEMnZUY2s'
		)

		Photo photoA2 = new Photo (
			title: 	'bar2',
			fileId: '0B7q228Dq2WUuSVZSVlNaVnZURGs'
		)

		Photo photoA3 = new Photo (
			title: 	'lobby',
			fileId: '0B7q228Dq2WUuSTJkUnh1OS15enM'
		)

		bar
			.addToPhotos(photoA1)
			.addToPhotos(photoA2)
			.addToPhotos(photoA3)
			.save(flush:true)
	}

	protected static void addVideos(Bar bar) {

		Video nyanCatVideo = new Video (
			title: 'nyan Cat',
			fileId: 'QH2-TGUlwu4'
		)

		bar.addToVideos(nyanCatVideo).save(flush:true)
	}

	protected static void addPostulations(Bar bar) {
		Postulate postulationRustico = new Postulate(
			title:	'Rústico busca teloneros para evento 30/07',
			text:	'Rústico bar búsca bandas estilo Trash/Hardcore para el \"festival hardcore vol. IV\", para interesados por favor contactanos a hardcoreenventarg@yahoo.com.ar . Rústico Bar.',
		)
		bar.addToPostulations(postulationRustico).save(flush: true)
	}
	
	protected static void addEventsTheVomitBar(Bar bar) {
		
				Photo flyerChuckEventVomitA1 = new Photo (
					title: 'flyer chuck in the vomit',
					fileId: '0B7q228Dq2WUubDREUmVkZzMwQ0E'
				).save(flush:true)
		
				Event chuckEventVomitA1 = new Event (
					name: 'ChuckNorris in the vomit Bar',
					date: formatter.parse('10/04/2011'),
					text: '01/09 ChuckNorris in the vomit presenting his new album Nice with you, Jazz festival. Tickets at door $50.',
					url: 'https://facebook.com/thevomitrestobar',
					photo: flyerChuckEventVomitA1,
					bar: bar
				)
				
				bar.addToEvents(chuckEventVomitA1).save(flush: true)
			}
			
	protected static void addPhotosTheVomitBar(Bar bar) {

		Photo photoVomitA1 = new Photo (
			title: 	'bar vomit',
			fileId: '0B7q228Dq2WUuQWlMVWlEMnZUY2s'
		)

		Photo photoVomitA2 = new Photo (
			title: 	'bar2 vomit',
			fileId: '0B7q228Dq2WUuSVZSVlNaVnZURGs'
		)		

		bar
			.addToPhotos(photoVomitA1)
			.addToPhotos(photoVomitA2)					
			.save(flush:true)
	}		

	protected static void addVideosTheVomitBar(Bar bar) {
		
		Video nyanCatVideoVomit = new Video (
			title: 'nyan Cat vomit',
			fileId: 'QH2-TGUlwu4'
		)

		bar.addToVideos(nyanCatVideoVomit).save(flush:true)
	}
			
	protected static void addPostulationsTheVomitBar(Bar bar) {
		
		Postulate postulationTheVomit = new Postulate(
			title:	'The Vomit looking event opening for 04/12',
			text:	'The Vomit bar bands Trash / Hardcore style for \"hardcore festival vol. IV \" to interested please contact us at hardcoreenventarg@yahoo.com.ar. The Vomit Bar.',
		)
		bar.addToPostulations(postulationTheVomit).save(flush: true)
	}
		
}
