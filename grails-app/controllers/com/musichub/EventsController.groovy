package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

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
		if(event.save(flush: true)){
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

		if(event.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond event.errors
		}				
	}
	
	@Transactional
	def delete(Event event) {
		if(!event) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
			if (event.hasErrors()){
				respond event.errors
			} else {
				event.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		}
	}
}

