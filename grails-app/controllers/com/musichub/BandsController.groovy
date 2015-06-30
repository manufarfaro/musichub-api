package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.musichub.security.util.UserUtils;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class BandsController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def bandList = Band.list(params)
		if(bandList.size() < 1) {
			render (contentType: "application/json") {
				[]
			}
		}
		render (contentType: "application/json") {
				bandList.collect {
					[
						id: it.id,
						name: it.name,
						artists: it.artists.collect {[id : it.id]},
						bio: it.bio,
						leader: [id: it.leader.id],
						discs: it.discs,
						videos: it.videos,
						photos: it.photos,
						postulated: it.postulated,
						slug: it.slug,
						email: it.email,
						googlePlusId: it.googlePlusId,
						facebookId: it.facebookId,
						twitterId: it.twitterId,
						website: it.website
					]
				}
		}
	}
	
	def show(Band band) {
		band  =  Band.withCriteria {
		  or {
		    eq('id', params.long('id'))
		    eq('slug', params.id)
		  }
		  maxResults 1
		}[0]
		if(!band) {
			render status: HttpStatus.NOT_FOUND
		} else {
			render (contentType: "application/json") {
				[
					id: band.id,
					name: band.name,
					artists: band.artists.collect {[id : it.id]},
					bio: band.bio,
					leader: [id: band.leader.id],
					discs: band.discs,
					videos: band.videos,
					photos: band.photos,
					postulated: band.postulated,
					slug: band.slug,
					email: band.email,
					googlePlusId: band.googlePlusId,
					facebookId: band.facebookId,
					twitterId: band.twitterId, 	
					website: band.website
				] 
			}
		}
	}
	
	@Transactional
	def save(Band band) {
		HttpStatus status = HttpStatus.FORBIDDEN
		Artist loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.class.equals(Artist)) {
			if (!band.hasErrors()){
				loggedUser.addToBands(band)
				if(loggedUser.save(flush: true)){
					status = HttpStatus.CREATED
				} else {
					respond band.errors
				}
			} else {
				respond band.errors
			}
		}
		if(loggedUser.authorities.find { it == 'ROLE_ADMIN' } ){
			if (!band.hasErrors){
				if(band.save(flush: true)){
					status = HttpStatus.CREATED
				} else {
					respond band.errors
				}			
			} else {
				respond band.errors
			}			
		}
		render status: status
	}

	@Transactional
	def update(Band band) {
		if(!band) {
			render status: HttpStatus.NOT_FOUND
		}

		Artist loggedUser = UserUtils.getLoggedUser()
		def isOwner = false
		if (loggedUser.class.equals(Artist)) {
			if (band.leader.equals(loggedUser) || loggedUser.authorities.find{it == 'ROLE_ADMIN'}) {
				isOwner = true
			} else {
				render status: HttpStatus.FORBIDDEN
			}
		}

		if (isOwner) {
			if(!band.hasErrors()){
				if(band.save(flush: true)){
					render status: HttpStatus.CREATED
				} else {
					respond band.errors
				}
			} else {
				respond band.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}

	@Transactional
	def delete(Band band) {
		if(!band) {
			render status: HttpStatus.NOT_FOUND
		}
		Artist loggedUser = UserUtils.getLoggedUser()
		def isOwner = false 
		if (loggedUser.class.equals(Artist)) {
			if (band.leader.equals(loggedUser) || loggedUser.authorities.find{it == 'ROLE_ADMIN'}) {
				isOwner = true
			} else {
				render status: HttpStatus.FORBIDDEN
			}
		}
		if (isOwner) {
			if (!band.hasErrors()){
				band.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			} else {
				respond band.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
	
	@Transactional
	def addArtist() {
		def loggedUser = UserUtils.getLoggedUser()

		Band band = Band.get(params.int('band_id'))
		Artist artist = Artist.get(params.int('artist_id'))
		if(!band || !artist) {
			render status: HttpStatus.NOT_FOUND
		}

		Boolean isOwner = false
		isOwner = loggedUser.bands?.find { it.equals(band)} ? true : isOwner
		isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner

		if (isOwner) {
			band.addToArtists(artist)
			render status: HttpStatus.CREATED
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
}

