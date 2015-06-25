package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class BarController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Bar.list(params), model:[countryCount: Bar.count()]
	}
	
	def show(Bar bar) {
		respond bar
	}
	
	@Transactional
	def save(Bar bar) {
		if(bar.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond bar.errors
		}
	}
	
	@Transactional
	def update(Bar bar) {
		if(!bar) {
			render status: HttpStatus.NOT_FOUND
		}

		if(bar.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond bar.errors
		}				
	}
	
	@Transactional
	def delete(Bar bar) {
		if(!bar) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
			if (bar.hasErrors()){
				respond bar.errors
			} else {
				bar.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		}
	}
}

