package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.musichub.security.util.UserUtils;
import com.musichub.util.google.services.Upload;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class EventsController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.order = 'desc'
		respond Event.list(params), model:[countryCount: Event.count()]
	}

	def show(Event event) {
		respond event
	}

	@Transactional
	def save(Event event) {
		def loggedUser = UserUtils.getLoggedUser()
		if (loggedUser.class.equals(Bar)) {
			event.setBar(loggedUser)
			if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
				String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
				params.fileId = uploadedFileId
					
				Photo photo = new Photo(
					title: 'flyer',
					fileId: params.fileId
				).save(flush: true)

				event.photo = photo
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
		event.validate()
		if (!event.hasErrors()) {
			event.save(flush: true)
			render status: HttpStatus.CREATED
		} else {
			respond event.errors
		}
	}

	@Transactional
	def update(Event event) {
		if(!event) {
			render status: HttpStatus.NOT_FOUND
		}

		Bar loggedUser = UserUtils.getLoggedUser()
		def isOwner = false
		
		if (loggedUser.class.equals(Bar)) {
			if (event.bar.equals(loggedUser) || loggedUser.authorities.find{it == 'ROLE_ADMIN'}) {
				isOwner = true
			} else {
				render status: HttpStatus.FORBIDDEN
			}
		}

		if(isOwner) {
			if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
				String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
				params.fileId = uploadedFileId
					
				Photo photo = new Photo(
					title: 'flyer',
					fileId: params.fileId
				).save(flush: true)

				event.photo = photo
			}
			if (!event.hasErrors()) {
				if(event.save(flush: true)){
					render status: HttpStatus.CREATED
				} else {
					respond event.errors
				}
			} else {
				respond event.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
	
	@Transactional
	def delete(Event event) {
		if(!event) {
			render status: HttpStatus.NOT_FOUND
		}

		Bar loggedUser = UserUtils.getLoggedUser()
		def isOwner = false
		
		if (loggedUser.class.equals(Artist)) {
			if (event.bar.equals(loggedUser) || loggedUser.authorities.find{it == 'ROLE_ADMIN'}) {
				isOwner = true
			} else {
				render status: HttpStatus.FORBIDDEN
			}
		}
		
		if(isOwner) {
			if (event.hasErrors()){
				respond event.errors
			} else {
				event.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
}

