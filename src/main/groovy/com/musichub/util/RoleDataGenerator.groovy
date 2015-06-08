package com.musichub.util

import com.musichub.Role

class RoleDataGenerator {
	public static void generate() {
		Role roleAdmin = new Role(
			authority: 'ROLE_ADMIN'
		).save(flush: true)
		Role roleArtist = new Role(
			authority: 'ROLE_ARTIST'
		).save(flush: true)
		Role roleBar = new Role(
			authority: 'ROLE_BAR'
		).save(flush: true)
	}
}
