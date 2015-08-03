package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.musichub.security.util.UserUtils;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class PostulatesController {
	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.order = 'desc'
		respond Postulate.list(params), model:[countryCount: Postulate.count()]
	}

	def show(Postulate postulate) {
		respond postulate
	}

	@Transactional
	def save(Postulate postulate) {
		if(!postulate) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		if (loggedUser.class.equals(Artist) || loggedUser.class.equals(Bar)) {
			postulate.offerer = loggedUser			
		}
		postulate.validate()
		if (!postulate.hasErrors()) {
				postulate.save(flush: true)
				render status: HttpStatus.CREATED
		} else {
			respond postulate.errors
		}
	}

	@Transactional
	def update(Postulate postulate) {
		if(!postulate) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		Boolean isOwner = false
		isOwner = postulate.offerer.equals(loggedUser) ? true : isOwner
		isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner

		if (isOwner) {
			if (!postulate.hasErrors()) {
				if(postulate.save(flush: true)){
					render status: HttpStatus.CREATED
				} else {
					respond postulate.errors
				}
			} else {
				respond postulate.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}

	@Transactional
	def delete(Postulate postulate) {
//		postulate = Postulate.get(params.int('postulate_id'))
		if(!postulate) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		Boolean isOwner = false 
		isOwner = postulate.offerer.equals(loggedUser) ? true : isOwner
		isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner

		if (isOwner) {
			if (postulate.hasErrors()){
				respond postulate.errors
			} else {
				postulate.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
	
	@Transactional
	def postulate() {
		Postulate postulate = Postulate.get(params.int('postulate_id'))
		if(!postulate) {
			render status: HttpStatus.NOT_FOUND
		}
		def loggedUser = UserUtils.getLoggedUser()
		Band band = Band.get(params.int('band_id'))
		
		if (loggedUser.class.equals(Artist)) {
			if(band) {
				band.addToPostulated(postulate).save(flush: true)
			} else {
				loggedUser.addToPostulated(postulate).save(flush: true)
			}
			render status: HttpStatus.CREATED
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
}
