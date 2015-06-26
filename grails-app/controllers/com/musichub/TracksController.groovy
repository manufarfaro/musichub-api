package com.musichub

import org.springframework.http.HttpStatus

import com.gs.collections.impl.block.factory.StringFunctions.ToIntegerFunction;

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

	def random(Integer limit) {
		respond Track.findRandomTracksByLimit(limit ?: 5).list()
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

	@Transactional
	def save(Track track) {
		if(!track) {
			render status: HttpStatus.NOT_FOUND
		}
		if(!track.hasErrors()) {
			if(track.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond track.errors
			}
		} else {
			respond track.errors
		}
	}

	@Transactional
	def update(Track track) {
		if(!track) {
			render status: HttpStatus.NOT_FOUND
		}
		println track.getName()
		if(!track.hasErrors()) {
			if(track.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond track.errors
			}
		} else {
			respond track.errors
		}
	}
}
