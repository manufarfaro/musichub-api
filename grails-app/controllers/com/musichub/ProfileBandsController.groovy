package com.musichub

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import grails.transaction.Transactional;

@Transactional(readOnly = true)
class ProfileBandsController {
	static responseFormats = ['json', 'xml']

	def index() {
//		respond Band.list(params), model:[countryCount: Band.count()]
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (auth?.isAuthenticated()){
			User user = auth.getPrincipal()
			Artist loggedUser = Artist.findByUsername(user.getUsername())
			if (loggedUser) {
				render (contentType: "application/json") {
					[
						loggedUser.getBands().collect {
							[
								name: it.name,
								artists: it.artists.collect {
									[
										class: "com.musichub.Artists",
										id : it.id
									]
								},
								bio: it.bio,
								leader: [
									class: "com.musichub.Artists",
									id: it.leader.id
								],
								discs: it.discs,
								videos: it.videos,
								photos: it.photos,
								slug: it.slug,
								email: it.email,
								googlePlusId: it.googlePlusId,
								facebookId: it.facebookId,
								twitterId: it.twitterId,
								website: it.website
							]
						}
					]
				}
			} else {
				render status: HttpStatus.NOT_FOUND
			}
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}

	@Transactional
	def save(Band band) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (auth?.isAuthenticated()){
			User user = auth.getPrincipal()
			Artist loggedUser = Artist.findByUsername(user.getUsername())
			if (loggedUser) {
				band.setLeader(loggedUser)
				band.addToArtists(loggedUser)
				if(!band.hasErrors()) {
					band.save(flush: true)
					render status: HttpStatus.CREATED
				} else {
					respond band.errors
				}
			} else {
				render status: HttpStatus.NOT_FOUND
			}
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}

	@Transactional
	def update(Band band) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		if (auth?.isAuthenticated()){
			User user = auth.getPrincipal()
			MHUser loggedUser = Artist.findByUsername(user.getUsername())
			if (loggedUser) {
				if(!band.hasErrors()) {
					loggedUser.addToBands(band).save(flush: true)
					render status: HttpStatus.CREATED
				} else {
					respond band.errors
				}
			} else {
				render status: HttpStatus.NOT_FOUND
			}
		} else {
			render status: HttpStatus.UNAUTHORIZED
		}
	}
}
