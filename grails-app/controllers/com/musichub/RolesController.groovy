package com.musichub

import grails.transaction.Transactional;

@Transactional(readOnly = true)
class RolesController {
	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Role.list(params), model:[roleCount: Role.count()]
	}

	def show(Role role) {
		respond role
	}

	@Transactional
	def save(Role role) {
		if(role.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond role.errors
		}
	}
	
	@Transactional
	def update(Role role) {
		if(!role) {
			render status: HttpStatus.NOT_FOUND
		}

		if(role.save(flush: true)){
			render status: HttpStatus.CREATED
		} else {
			respond role.errors
		}
	}
	
	@Transactional
	def delete(Role role) {
		if(!role) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
			if (role.hasErrors()){
				respond role.errors
			} else {
				role.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			}
		}
	}
}
