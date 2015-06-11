package com.musichub

import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true)
class QuotesController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Quote.list(params), model:[quoteCount: Quote.count()]
	}

	def show(Quote quote) {
		respond quote
	}

	@Transactional
	def save(Quote quote) {
		if(quote.hasErrors()) {
			respond quote.errors
		}
		else {
			quote.save()
			render status: CREATED
		}
	}

	@Transactional
	def update(Quote quote) {
		if(quote) {
			render status: NOT_FOUND
		}
		else {
			if (quote.hasErrors()){
				respond quote.errors
			} else {
				quote.save(flush: true)
				render status: CREATED
			}
		}
	}

	@Transactional
	def delete(Quote quote) {
		if(quote) {
			render status: NOT_FOUND
		}
		else {
			if (quote.hasErrors()){
				respond quote.errors
			} else {
				quote.delete(flush: true)
				render status: NO_CONTENT
			}
		}
	}
}
