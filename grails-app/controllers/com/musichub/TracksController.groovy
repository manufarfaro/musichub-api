package com.musichub

import org.springframework.http.HttpStatus
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest.StandardMultipartFile;

import com.musichub.security.util.UserUtils;
import com.gs.collections.impl.block.factory.StringFunctions.ToIntegerFunction;
import com.musichub.util.amazon.s3.AmazonS3Uploader;
import com.musichub.util.google.services.Upload;

import grails.transaction.Transactional
import grails.validation.ValidationException

@Transactional(readOnly = true)
class TracksController {
	static requestFormats = ['json', 'xml', 'multipartForm', 'html']
	static responseFormats = ['json', 'xml', 'multipartForm', 'html']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.order = 'desc'
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
			isOwner = disc.artist?.equals(loggedUser) ? true : isOwner
			isOwner = loggedUser.bands.find { it.equals(disc.band) } ? true : isOwner
			isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner
			if(isOwner) {
				
				if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
					Map uploadedFileData
					try {
						uploadedFileData = AmazonS3Uploader.track(params.file, track)
					} catch (ValidationException exception) {
						render (
							status: HttpStatus.UNPROCESSABLE_ENTITY,
							message: 'El audio que intentás subir es incorrecto, solo los formatos de audio mp3 y ogg son soportados.'
						)
					}
					track = new Track(
						name: params.name,
						fileId: uploadedFileData?.public_id?.toString(),
						orderNbr: params.orderNbr, 
						url: uploadedFileData?.public_url?.toString(),
						format: uploadedFileData?.format?.toString()
					)
				}
				track.disc = disc
				track.validate()
				if (!track.hasErrors()){
					track.disc = disc
					track.save(flush:true) 
					render status: HttpStatus.CREATED
				} else {
					respond track
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
		isOwner = track.disc.artist?.equals(loggedUser) ? true : isOwner
		isOwner = loggedUser.bands?.find { it.equals(track.disc.band) } ? true : isOwner
		isOwner = loggedUser.authorities.find { it.equals('ROLE_ADMIN') } ? true : isOwner
		
		if(isOwner) {

			if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
				Map uploadedFileData
				try {
					uploadedFileData = AmazonS3Uploader.track(params.file, track)
				} catch (ValidationException exception) {
					render (
						status: HttpStatus.UNPROCESSABLE_ENTITY,
						message: 'El audio que intentás subir es incorrecto, solo los formatos de audio mp3 y ogg son soportados.'
					)
				}
				track = new Track(
					name: params.name,
					fileId: uploadedFileData?.public_id?.toString(),
					orderNbr: params.orderNbr, 
					url: uploadedFileData?.public_url?.toString(),
					format: uploadedFileData?.format?.toString()
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
