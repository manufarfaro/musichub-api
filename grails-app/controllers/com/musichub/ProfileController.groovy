package com.musichub


import java.text.SimpleDateFormat;

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

	@Transactional
	def update() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (auth?.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")){
			User user = auth.getPrincipal()
			MHUser mhUser = MHUser.findByUsername(user.getUsername())
			MHUser loggedUser = Artist.get(mhUser.getId()) ?:Bar.get(mhUser.getId())
			loggedUser = loggedUser ?: mhUser
			if(!loggedUser.hasErrors()){
					if (loggedUser instanceof Artist) {
						loggedUser.setBio(params.bio ?: loggedUser.bio)
						loggedUser.setLastname(params.lastname ?: loggedUser.lastname)
						loggedUser.setGender(params.gender ?: loggedUser.gender)
						loggedUser.setRegion(params.int('region') ? Country.get(params.region) : loggedUser.region)
						loggedUser.setBirthdate(params.birthdate ? formatter.parse(params.birthdate) : loggedUser.birthdate)
						loggedUser.setCodePhone(params.codePhone ?: loggedUser.codePhone)
					}
					if(loggedUser instanceof Bar) {
						loggedUser.setDescription(params.description ?: loggedUser.bio)
						loggedUser.setAddress(params.address ?: loggedUser.lastname)
					}
					if ( (loggedUser instanceof MHUser) || (loggedUser instanceof Artist) || (loggedUser instanceof Bar) ) {
						loggedUser.setName(params.name ?: loggedUser.name)
						loggedUser.setPhone(params.phone ?: loggedUser.phone)
						loggedUser.setGooglePlusId(params.googlePlusId ?: loggedUser.googlePlusId)
						loggedUser.setFacebookId(params.facebookId ?: loggedUser.facebookId)
						loggedUser.setTwitterId(params.twitterId ?: loggedUser.twitterId)
						loggedUser.setWebsite(params.website ?: loggedUser.website)
					}
			} else {
				respond loggedUser.errors
			}
			loggedUser.save(flush: true)
			render status: HttpStatus.CREATED
		} else {
			render HttpStatus.UNPROCESSABLE_ENTITY
		}
	}
}
