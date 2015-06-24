package com.musichub


import grails.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User

import com.google.api.client.json.Json;

@Transactional(readOnly = true)
class ProfileController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		Authentication auth = SecurityContextHolder?.getContext().getAuthentication()
		if (auth?.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")){
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?:Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
			respond loggedUser
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
	
	def update() {
		
	}
}
