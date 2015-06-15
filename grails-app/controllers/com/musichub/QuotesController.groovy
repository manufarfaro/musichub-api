package com.musichub


import javax.servlet.http.HttpServletRequest;

import com.google.api.client.json.Json;
import com.musichub.util.google.services.Upload;

import sun.net.httpserver.Request;
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
		if (params.fileId.isEmpty() && params.file && params.file?.bytes.size() > 0) {
			String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifnBpTVB0eVJPOGJJdEg0VEpvTThfelJ5UlRMZlBoUk9LeEliWHBmWFVxcUk")
			params.fileId = uploadedFileId
			
			quote = new Quote(
				title: params.title,
				quote: params.quote,
				fileId: params.fileId
			)
		}
		
		if(quote.save(flush: true)){
			render status: CREATED
		} else {
			respond quote.errors
		}
	}

	@Transactional
	def update(Quote quote) {
		if(!quote) {
			render status: NOT_FOUND
		}
		else {
			
			if (params.fileId.isEmpty() && params.file && params.file?.bytes.size() > 0) {
				String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifnBpTVB0eVJPOGJJdEg0VEpvTThfelJ5UlRMZlBoUk9LeEliWHBmWFVxcUk")
				params.fileId = uploadedFileId
				
				quote = new Quote(
					title: params.title,
					quote: params.quote,
					fileId: params.fileId
				)
			}
			
			if(quote.save(flush: true)){
				render status: CREATED
			} else {
				respond quote.errors
			}
		}
	}

	@Transactional
	def delete(Quote quote) {
		if(!quote) {
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
