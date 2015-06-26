package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class DiscsController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Disc.list(params), model:[countryCount: Disc.count()]
	}
	
	def show(Disc disc) {
		respond disc
	}
	
	@Transactional
	def save(Disc disc) {
		if(!disc.hasErrors()){
			if(disc.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond disc.errors
			}			
		}
	}
	
	@Transactional
	def update(Disc disc) {
		if(!disc) {
			render status: HttpStatus.NOT_FOUND
		}
		if(!disc.hasErrors()){
			if(disc.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond disc.errors
			}
		}
	}
	
	@Transactional
	def delete(Disc disc) {
		if(!disc) {
			render status: HttpStatus.NOT_FOUND
		}
		if (disc.hasErrors()){
			respond country.errors
		} else {
			disc.delete(flush: true)
			render status: HttpStatus.NO_CONTENT
		}
	}
}

