package com.musichub.util

import com.musichub.Artist
import com.musichub.Role
import com.musichub.UserRole

class ArtistDataGenerator {
	public static void generate() {
		Artist artistChuckNorris = new Artist(
			slug:		'chucknorris',
			username: 	'chucknorris',
			password: 	'Chuck123',
			email:		'chucknorris@gmail.com',
			name: 		'Chuck',
			lastname: 	'Norris',
			enabled: true
		).save(flush: true)

		UserRole userRole1a = new UserRole(
			user: artistChuckNorris,
			role: Role.findByAuthority('ROLE_ARTIST')
		).save(flush: true)
	}
}
