package com.musichub

import javax.servlet.http.HttpServletRequest;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class CountryController {

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
		if (!country.iso.isEmpty()&&!country.name.isEmpty()&&!country.nicename.isEmpty()&&country.phoneCode.toString().isInteger()) {

			country = new Country(
				iso: country.iso,
				name: country.name,
				nicename: country.nicename,
				iso3: country.iso3,
				numCode: country.numCode,
				phoneCode: country.phoneCode
			)
		}
		
		if(country.save(flush: true)){
			render status: CREATED
		} else {
			respond country.errors
		}
	}
	
@Transactional
	def update(Country country) {
		if(!country) {
			render status: NOT_FOUND
		}
		else {
			
			if (!country.iso.isEmpty()&&!country.name.isEmpty()&&!country.nicename.isEmpty()&&country.phoneCode.toString().isInteger()) {
	
				country = new Country(
					iso: country.iso,
					name: country.name,
					nicename: country.nicename,
					iso3: country.iso3,
					numCode: country.numCode,
					phoneCode: country.phoneCode
				)
			}
			
			if(country.save(flush: true)){
				render status: CREATED
			} else {
				respond country.errors
			}				
		}
}
	
@Transactional
	def delete(Country country) {
		if(!country) {
			render status: NOT_FOUND
		}
		else {
			if (country.hasErrors()){
				respond country.errors
			} else {
				country.delete(flush: true)
				render status: NO_CONTENT
			}
		}
	}
}
