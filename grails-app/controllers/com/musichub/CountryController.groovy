package com.musichub

import grails.transaction.Transactional

@Transactional(readOnly = true )
class CountryController {

	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Country.list(params), model:[countryCount: Country.count()]
	}  
}
