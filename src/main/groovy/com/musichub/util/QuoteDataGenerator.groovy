package com.musichub.util

import com.musichub.Quote

class QuoteDataGenerator {
	public static void generate() {
		Quote quoteCadillacs =  new Quote(
			title:		'Los Fabulosos Cadillacs - Somos Actores',
			quote:		'Somos Actores de ese gran escenario que se llama vida, pasiones, amores, traiciones, sueños y mentiras...',
			fileId:		'0B3pR1yPz3ddiblBsR1lHSUtsUHc'
		).save(flush: true)

		Quote quoteMelingo = new Quote(
			title:	"Linyera soy",
			quote:	"Linyera soy, lo que gano lo gasto o lo doy, no sé llorar, ni en la vida deseo triunfar...",
			fileId:	"0B3BAQjlbOsAwUEFfUmRzb0tUZkE"
		).save(flush: true)

		Quote quoteCharlyGarcia = new Quote(
			title: 	"El bit de un tambor",
			quote: 	"Escucho el bit de un tambor entre la desolación de una radio en una calle desierta están las puertas cerradas y las ventanas también...",
			fileId:	"0B3BAQjlbOsAwZXZmU1R2MHRvQlE"
		).save(flush: true)

		Quote quoteFitoPaez = new Quote(
			title: 	"La alegría de tu corazón",
			quote: 	"Y todo lo que hacés por obligación se lleva la alegría de tu corazón.",
			fileId:	"0B3BAQjlbOsAweWNTbXctS3g1Uk0"
		).save(flush: true)

		Quote quoteBoomBoomKid = new Quote(
			title: 	"Allí me quedaré",
			quote: 	"Si con tus ojos puedo ver, si con tu boca puedo hablar, allí me quedare...",
			fileId:	"0B3BAQjlbOsAwdUl1ZmJTa2hObk0"
		).save(flush: true)

		Quote quoteAndresCalamaro = new Quote(
			title: 	"Pechito",
			quote: 	"Pechito bailarín según el paladín siempre al frente temerario o valiente...",
			fileId:	"0B3BAQjlbOsAwMTlTN3Q1U2psNWc"
		).save(flush: true)
		
		Quote quotePityAlvarez = new Quote(
			title:	"Sueño",
			quote:	"Sueño que sueño que estoy soñando y de fondo una música tipo rock and roll...",
			fileId: "0B3BAQjlbOsAwS0JEUW5ZR0FWakk"
		).save(flush: true)

		Quote quoteRaphael = new Quote(
			title:	"A pesar",
			quote:	"A pesar de las dudas y mi eterna locura...",
			fileId: "0B3BAQjlbOsAwY0U2djJIUXEzSnc"
		).save(flush: true)

		Quote quoteIKV = new Quote(
			title:	"Muchacho",
			quote:	"Un muchacho monitor cabezón de primavera...",
			fileId: "0B3BAQjlbOsAwTVByYnA3TjJZRjg"
		).save(flush: true)

		Quote quoteLuisAlbertoSpinetta = new Quote(
			title:	"Mi vida",
			quote:	"Toda mi vida resbala en seis cuerdas...",
			fileId: "0B3BAQjlbOsAweXUwTXBSaGJTV28"
		).save(flush: true)

		Quote quoteRicardoIorio = new Quote(
			title:	"Mi canto",
			quote:	"Que mi canto causa espanto o no advierten, que me cago en la moda en la cumbiamba y su joda en el amor mentido y el castigo divino...",
			fileId: "0B3BAQjlbOsAwN0JMbWVTYTdtTk0"
		).save(flush: true)
	}
}
