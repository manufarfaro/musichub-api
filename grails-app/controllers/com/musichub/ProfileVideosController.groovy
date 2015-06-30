package com.musichub

import grails.transaction.Transactional
import com.musichub.security.util.UserUtils

@Transactional(readOnly = true)
class ProfileVideosController {
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.authorities?.find { it.equals('ROLE_ADMIN') })  {
			render (contentType: "application/json") {
				[]
			}
		}
		if (loggedUser){
			respond loggedUser.getVideos()
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
}
