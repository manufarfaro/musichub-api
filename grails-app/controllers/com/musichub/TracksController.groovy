package com.musichub

import org.springframework.http.HttpStatus
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TracksController {
	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Track.list(params), model:[trackCount: Track.count()]
	}

	def show(Track track) {
		respond track
	}

	def random(int limit) {
		respond Track.findRandomTracksByLimit(limit)
	}

	@Transactional
	def delete(Track track) {
		if(!track) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
				track.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
		}
	}
}
