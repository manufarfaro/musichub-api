package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class ArtistsController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Artist.list(params), model:[countryCount: Artist.count()]
	}
	
	def show(Artist artist) {
		artist  =  Artist.withCriteria {
			or {
				eq('id', params.long('id'))
				eq('slug', params.id)
			}
			maxResults 1
		}[0]
		respond artist
	}
	
	@Transactional
	def save(Artist artist) {
		if(!artist) {
			render status: HttpStatus.NOT_FOUND
		}

		if (!artist.hasErrors()) {
			if(artist.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond artist.errors
			}
		} else {
			respond artist.errors
		}
	}
	
	@Transactional
	def update(Artist artist) {
		if(!artist) {
			render status: HttpStatus.NOT_FOUND
		}

		if (!artist.hasErrors()) {
			if(artist.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond artist.errors
			}
		} else {
			respond artist.errors
		}				
	}
	
	@Transactional
	def delete(Artist artist) {
		if(!artist) {
			render status: HttpStatus.NOT_FOUND
		}

		if (artist.hasErrors()){
			respond artist.errors
		} else {
			artist.delete(flush: true)
			render status: HttpStatus.NO_CONTENT
		}
	}
}

