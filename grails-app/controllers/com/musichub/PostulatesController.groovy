package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class PostulatesController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Postulate.list(params), model:[countryCount: Postulate.count()]
	}
	
	def show(Postulate postulate) {
		respond postulate
	}
	
	@Transactional
	def save(Postulate postulate) {
		if(postulate.save(flush: true)){
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

		if(postulate.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond postulate.errors
		}				
	}
	
	@Transactional
	def delete(Postulate postulate) {
		if(!postulate) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
			if (postulate.hasErrors()){
				respond postulate.errors
			} else {
				postulate.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		}
	}
}

