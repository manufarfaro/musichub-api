package com.musichub.util

import java.text.SimpleDateFormat;

import com.musichub.Artist;
import com.musichub.Bar;
import com.musichub.Postulate;

class PostulateDataGenerator {

	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")

	public static void generate() {
		Artist artistChuckNorris = Artist.findByUsername('chucknorris')
		Bar barVomito = Bar.findByUsername('vomitobar')
		this.addArtistsPostulated(artistChuckNorris)
		this.addBarsPostulatedVomit(barVomito)
	}

	protected static void addArtistsPostulated(Artist artist) {
		artist
			.addToPostulated(
				Postulate.
					findByTitle('Rústico busca teloneros para evento 30/07')
			).save(flush: true)
	}	

	protected static void addBarsPostulatedVomit(Bar bar) {
		bar
			.addToPostulated(
				Postulate.
					findByTitle('The Vomit looking event opening for 04/12')
			).save(flush: true)
	}
}
