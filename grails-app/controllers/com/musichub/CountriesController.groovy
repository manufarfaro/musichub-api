package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class CountriesController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Country.list(params), model:[countryCount: Country.count()]
	}

	def show(Country country) {
		respond country
	}

	@Transactional
	def save(Country country) {
		if(!country) {
			render status: HttpStatus.NOT_FOUND
		}
		if(!country.hasErrors()){
			if(country.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond country.errors
			}
		} else {
			respond country.errors
		}
	}

	@Transactional
	def update(Country country) {
		if(!country) {
			render status: HttpStatus.NOT_FOUND
		}
		if(!country.hasErrors()){
			if(country.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond country.errors
			}
		} else {
			respond country.errors
		}
	}

	@Transactional
	def delete(Country country) {
		if(!country) {
			render status: HttpStatus.NOT_FOUND
		}

		if (country.hasErrors()){
			respond country.errors
		} else {
			country.delete(flush: true)
			render status: HttpStatus.NO_CONTENT
		}
	}
}
