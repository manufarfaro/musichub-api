package com.musichub

import org.springframework.http.HttpStatus

import com.gs.collections.impl.block.factory.StringFunctions.ToIntegerFunction;
import com.musichub.util.google.services.Upload;

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
		
		def loggedUser = UserUtils.getLoggedUser()
		
		Boolean isOwner = false
		isOwner = track.disc.artist.equals(loggedUser) ? true : isOwner
		isOwner = loggedUser.bands.find { it.equals(track.disc.band) } ? true : isOwner
		isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner
		
		if(isOwner) {
			if(!track.hasErrors()) {
				track.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
			} else {
				respond track.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}

	@Transactional
	def save(Track track) {
		if(!track) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		Boolean isOwner = false
		Disc disc = Disc.get(params.int('disc_id'))

		if (disc) {
			isOwner = disc.artist.equals(loggedUser) ? true : isOwner
			isOwner = loggedUser.bands.find { it.equals(disc.band) } ? true : isOwner
			isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner
			if(isOwner) {
				
				if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
					String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifldubUdTNXE3LW1ybC1tQ01fWjdRSjJ0TmNMWG9henEwel9rNGxuRHJwSkU")
					params.fileId = uploadedFileId
						
					track = new Track(
						title: params.title,
						fileId: params.fileId
					)
				}
				
				if (!track.hasErrors()){
					disc.addToTracks(track).save(flush: true)
					render status: HttpStatus.CREATED
				} else {
					respond track.errors
				}
			} else {
				render status: HttpStatus.FORBIDDEN
			}
		} else {
			render status: HttpStatus.NOT_FOUND
		}
	}

	@Transactional
	def update(Track track) {
		if(!track) {
			render status: HttpStatus.NOT_FOUND
		}
		
		def loggedUser = UserUtils.getLoggedUser()
		
		Boolean isOwner = false
		isOwner = track.disc.artist.equals(loggedUser) ? true : isOwner
		isOwner = loggedUser.bands.find { it.equals(track.disc.band) } ? true : isOwner
		isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner
		
		if(isOwner) {

			if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
				String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifldubUdTNXE3LW1ybC1tQ01fWjdRSjJ0TmNMWG9henEwel9rNGxuRHJwSkU")
				params.fileId = uploadedFileId
					
				track = new Track(
					title: params.title,
					fileId: params.fileId
				)
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
		} else {
			render status: HttpStatus.FORBIDDEN
		}
	}
}
