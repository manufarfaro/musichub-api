package com.musichub.util

import com.musichub.MHUser
import com.musichub.Role;
import com.musichub.UserRole;

class MHUserDataGenerator {
	public static void generate() {
		MHUser userAdmin = new MHUser(
			username:	'administrator',
			password:	'admin123',
			email:		'mhubofficial@gmail.com',
		).save(flush: true)

		UserRole userRole1c = new UserRole(
			user: userAdmin,
			role: Role.findByAuthority('ROLE_ADMIN')
		).save(flush: true)
	}
}
