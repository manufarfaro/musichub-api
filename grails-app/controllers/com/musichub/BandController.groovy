package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class BandController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Band.list(params), model:[countryCount: Band.count()]
	}
	
	def show(Band band) {
		respond band
	}
	
	@Transactional
	def save(Band band) {
		if(band.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond band.errors
		}
	}
	
	@Transactional
	def update(Band band) {
		if(!band) {
			render status: HttpStatus.NOT_FOUND
		}

		if(band.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond band.errors
		}				
	}
	
	@Transactional
	def delete(Band band) {
		if(!band) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
			if (band.hasErrors()){
				respond band.errors
			} else {
				band.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		}
	}
}

