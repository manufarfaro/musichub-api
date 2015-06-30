package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.musichub.security.util.UserUtils;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class EventsController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Event.list(params), model:[countryCount: Event.count()]
	}

	def show(Event event) {
		respond event
	}

	@Transactional
	def save(Event event) {
		Bar loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.class.equals(Bar)) {
			event.setBar(loggedUser)
		}
		event.validate()
		if (!event.hasErrors()) {
			event.save(flush: true)
			render status: HttpStatus.CREATED
		} else {
			respond event.errors
		}
	}

	@Transactional
	def update(Event event) {
		if(!event) {
			render status: HttpStatus.NOT_FOUND
		}

		Bar loggedUser = UserUtils.getLoggedUser()
		def isOwner = false
		
		if (loggedUser.class.equals(Artist)) {
			if (event.bar.equals(loggedUser) || loggedUser.authorities.find{it == 'ROLE_ADMIN'}) {
				isOwner = true
			} else {
				render status: HttpStatus.FORBIDDEN
			}
		}

		if(isOwner) {
			if (!event.hasErrors()) {
				if(event.save(flush: true)){
					render status: HttpStatus.CREATED
				} else {
					respond event.errors
				}
			} else {
				respond event.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
	
	@Transactional
	def delete(Event event) {
		if(!event) {
			render status: HttpStatus.NOT_FOUND
		}

		Bar loggedUser = UserUtils.getLoggedUser()
		def isOwner = false
		
		if (loggedUser.class.equals(Artist)) {
			if (event.bar.equals(loggedUser) || loggedUser.authorities.find{it == 'ROLE_ADMIN'}) {
				isOwner = true
			} else {
				render status: HttpStatus.FORBIDDEN
			}
		}
		
		if(isOwner) {
			if (event.hasErrors()){
				respond event.errors
			} else {
				event.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
}

