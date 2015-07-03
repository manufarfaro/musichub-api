package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class BarsController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.order = 'desc'
		respond Bar.list(params), model:[countryCount: Bar.count()]
	}
	
	def show(Bar bar) {
		bar  =  Bar.withCriteria {
			or {
				eq('id', params.long('id'))
				eq('slug', params.id)
			}
			maxResults 1
		}[0]
		respond bar
	}
	
	@Transactional
	def save(Bar bar) {
		if(!bar) {
			render status: HttpStatus.NOT_FOUND
		}

		if(!bar.hasErrors()) {
			if(bar.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond bar.errors
			}
		} else {
			respond bar.errors
		}
	}
	
	@Transactional
	def update(Bar bar) {
		if(!bar) {
			render status: HttpStatus.NOT_FOUND
		}

		if(!bar.hasErrors()) {
			if(bar.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond bar.errors
			}
		} else {
			respond bar.errors
		}
	}
	
	@Transactional
	def delete(Bar bar) {
		if(!bar) {
			render status: HttpStatus.NOT_FOUND
		}

		if (bar.hasErrors()){
			respond bar.errors
		} else {
			bar.delete(flush: true)
			render status: HttpStatus.NO_CONTENT
		}
	}
}

