package com.musichub

import grails.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.google.api.client.http.HttpStatusCodes;
import com.musichub.security.util.UserUtils;
import com.musichub.util.google.services.Upload;

@Transactional(readOnly = true)
class ProfilePhotosController {

	static responseFormats = ['json', 'xml']

	def index() {
		def loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.authorities?.find { it.equals('ROLE_ADMIN') })  {
			render (contentType: "application/json") {
				[]
			}
		}
		if (loggedUser){
			respond loggedUser.getPhotos()
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
}
