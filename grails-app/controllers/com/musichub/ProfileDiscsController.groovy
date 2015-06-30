package com.musichub

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.musichub.security.util.UserUtils;

import grails.transaction.Transactional;

@Transactional(readOnly = true )
class ProfileDiscsController {
	static responseFormats = ['json', 'xml']

	def index() {
		def loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.authorities?.find { it.equals('ROLE_ADMIN') })  {
			render (contentType: "application/json") {
				[]
			}
		}
		if (loggedUser){
			respond loggedUser.getDiscs()
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
}
