package com.musichub

import com.musichub.security.util.UserUtils;

class ProfilePostulatesController {
	static responseFormats = ['json', 'xml']
	
	def indexPostulations() {
		def loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.authorities?.find { it.equals('ROLE_ADMIN') })  {
			render (contentType: "application/json") {
				[]
			}
		}
		if (loggedUser){
			respond loggedUser.getPostulations()
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}

	def indexPostulates() {
		def loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.authorities?.find { it.equals('ROLE_ADMIN') })  {
			render (contentType: "application/json") {
				[]
			}
		}
		if (loggedUser){
			respond loggedUser.getPostulated()
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
}
