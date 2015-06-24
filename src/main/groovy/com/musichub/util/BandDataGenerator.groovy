package com.musichub.util

import com.musichub.Artist
import com.musichub.Band

class BandDataGenerator {
	public static void generate() {
		Artist artistChuckNorris = Artist.findByEmail("chucknorris@gmail.com")
		
		Band chuckyband = new Band(
			name:	'Chucky the band',
			slug:	'chuckyandtheband',
			email:	'chucknorris@gmail.com',
			leader: artistChuckNorris
		).addToArtists(artistChuckNorris)
		.save(flush: true)
	}
}
