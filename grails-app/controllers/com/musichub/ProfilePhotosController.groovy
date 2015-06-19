package com.musichub

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

class ProfilePhotosController {
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (auth.isAuthenticated()){
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?:Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
			if ( (loggedUser instanceof Artist) || (loggedUser instanceof Bar) ) {
				respond loggedUser.getPhotos()
			} else {
				render []
			}
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
	def show(Photo photo) {
		
	}
}
