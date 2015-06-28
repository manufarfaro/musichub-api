package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class BandsController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		render (contentType: "application/json") {
			[
				Band.list(params).collect {
					[
						name: it.name,
						artists: it.artists.collect {[id : it.id]},
						bio: it.bio,
						leader: [id: it.leader.id],
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
					name: band.name,
					artists: band.artists.collect {[id : it.id]},
					bio: band.bio,
					leader: [id: band.leader.id],
					discs: band.discs,
					videos: band.videos,
					photos: band.photos,
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
		if (!band.hasErrors){
			if(band.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond band.errors
			}			
		} else {
			respond band.errors
		}
	}
	
	@Transactional
	def update(Band band) {
		if(!band) {
			render status: HttpStatus.NOT_FOUND
		}

		if(!band.hasErrors()){
			if(band.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond band.errors
			}							
		} else {
			respond band.errors
		}
	}
	
	@Transactional
	def delete(Band band) {
		if(!band) {
			render status: HttpStatus.NOT_FOUND
		}
		if (!band.hasErrors()){
			band.delete(flush: true)
			render status: HttpStatus.NO_CONTENT
		} else {
			respond band.errors
		}
	}
}

