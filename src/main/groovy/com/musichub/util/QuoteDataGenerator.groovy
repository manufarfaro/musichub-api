package com.musichub.util

import com.musichub.Quote

class QuoteDataGenerator {
	public static void generate() {
		Quote quoteCadillacs =  new Quote(
			title:		'Los Fabulosos Cadillacs - Somos Actores',
			quote:		'Somos Actores de ese gran escenario que se llama vida, pasiones, amores, traiciones, sueños y mentiras...',
			fileId:		'0B3pR1yPz3ddiblBsR1lHSUtsUHc'
		).save(flush: true)
	}
}
