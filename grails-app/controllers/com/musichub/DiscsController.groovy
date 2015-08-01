package com.musichub

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.musichub.security.util.UserUtils;

import sun.net.httpserver.Request;
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true )
class DiscsController {
	
	static responseFormats = ['json', 'xml']
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.order = 'desc'
		respond Disc.list(params), model:[countryCount: Disc.count()]
	}
	
	def show(Disc disc) {
		respond disc
	}
	
	@Transactional
	def save(Disc disc) {

		if(!disc) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		disc.validate()
		if(!disc.hasErrors()) {
			if(params.band_id) {
				Band band = Band.get(params.int('band_id'))
				band.addToDiscs(disc).save(flush: true)
			} else {
				loggedUser.addToDiscs(disc).save(flush: true)
			}
			render status: HttpStatus.CREATED
		} else {
			render disc.errors
		}

		if(loggedUser.authorities.find { it == 'ROLE_ADMIN' } ){
			if (!disc.hasErrors){
				if(disc.save(flush: true)){
					status = HttpStatus.CREATED
				} else {
					respond disc.errors
				}
			} else {
				respond disc.errors
			}
		}
		render status: status
	}
	
	@Transactional
	def update(Disc disc) {
		if(!disc) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		Boolean isOwner = false
		isOwner = Band.get(params.int('band_id'))?.discs.find { it.equals(disc) } ? true : isOwner
		isOwner = loggedUser.discs?.find { it.equals(disc) } ? true : isOwner
		isOwner = loggedUser.authorities.find{ it.equals('ROLE_ADMIN') } ? true : isOwner

		disc.validate()
		if(isOwner) {
			if(!disc.hasErrors()) {
				disc.save(flush:true)
				render status: HttpStatus.CREATED
			} else {
				render disc.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
	
	@Transactional
	def delete(Disc disc) {
		if(!disc) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		Boolean isOwner = false
		isOwner = disc.artist.equals(loggedUser) ? true : isOwner
		isOwner = disc.band?.leader.equals(loggedUser) ? true : isOwner
		isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner

		disc.validate()

		if (isOwner) {
			if (disc.hasErrors()){
				respond disc.errors
			} else {
				disc.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
}

