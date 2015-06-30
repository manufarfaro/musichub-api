package com.musichub


import java.text.SimpleDateFormat;

import grails.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User

import com.google.api.client.json.Json;
import com.musichub.security.util.UserUtils;

@Transactional(readOnly = true)
class ProfileController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		def loggedUser = UserUtils.getLoggedUser()
		if(loggedUser) {
			respond loggedUser
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}

	@Transactional
	def update() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")
		def loggedUser = UserUtils.getLoggedUser()
		if(loggedUser) {
			if (loggedUser.class.equals(Artist)) {
				loggedUser.setBio(params.bio ?: loggedUser.bio)
				loggedUser.setLastname(params.lastname ?: loggedUser.lastname)
				loggedUser.setGender(params.gender ?: loggedUser.gender)
				loggedUser.setRegion(params.int('region') ? Country.get(params.region) : loggedUser.region)
				loggedUser.setBirthdate(params.birthdate ? formatter.parse(params.birthdate) : loggedUser.birthdate)
				loggedUser.setCodePhone(params.codePhone ?: loggedUser.codePhone)
			}
			if(loggedUser.class.equals(Bar)) {
				loggedUser.setDescription(params.description ?: loggedUser.bio)
				loggedUser.setAddress(params.address ?: loggedUser.lastname)
			}
			loggedUser.setName(params.name ?: loggedUser.name)
			loggedUser.setEmail(params.email ?: loggedUser.email)
			loggedUser.setPhone(params.phone ?: loggedUser.phone)
			loggedUser.setGooglePlusId(params.googlePlusId ?: loggedUser.googlePlusId)
			loggedUser.setFacebookId(params.facebookId ?: loggedUser.facebookId)
			loggedUser.setTwitterId(params.twitterId ?: loggedUser.twitterId)
			loggedUser.setWebsite(params.website ?: loggedUser.website)
			if(!loggedUser.hasErrors()){
				loggedUser.save(flush: true)
				render status: HttpStatus.CREATED
			} else {
				respond loggedUser.errors
			}
		} else {
			render HttpStatus.FORBIDDEN
		}
	}
}
