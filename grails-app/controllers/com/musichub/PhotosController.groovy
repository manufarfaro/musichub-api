package com.musichub

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.musichub.security.util.UserUtils;
import com.musichub.util.google.services.Upload;

import grails.transaction.Transactional;

@Transactional(readOnly = true)
class PhotosController {
	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.order = 'desc'
		respond Photo.list(params), model:[photoCount: Photo.count()]
	}

	def random(Integer limit) {
		respond Photo.findRandomPhotosByLimit(limit ?: 5).list()
	}

	def show(Photo photo) {
		respond photo
	}

	@Transactional
	def save(Photo photo) {
		if(!photo) {
			render status: HttpStatus.NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
			params.fileId = uploadedFileId
				
			photo = new Photo(
				title: params.title,
				fileId: params.fileId
			)
		}
		photo.validate()
		if(!photo.hasErrors()) {
			if(params.band_id) {
				Band band = Band.get(params.int('band_id'))
				band.addToPhotos(photo).save(flush: true)
			} else {
				loggedUser.addToPhotos(photo).save(flush: true)
			}
			render status: HttpStatus.CREATED
		} else {
			render photo.errors
		}
	}

	@Transactional
	def update(Photo photo) {
		if(!photo) {
			render status: HttpStatus.NOT_FOUND
		}
		
		def loggedUser = UserUtils.getLoggedUser()

		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
			params.fileId = uploadedFileId

			photo = new Photo(
				title: params.title,
				fileId: params.fileId
			)
		}

		Boolean isOwner = false
		isOwner = Band.get(params.int('band_id'))?.photos.find { it.equals(photo) } ? true : isOwner
		isOwner = loggedUser.photos.find { it.equals(photo) } ? true : isOwner
		isOwner = loggedUser.authorities.find{ it.equals('ROLE_ADMIN') } ? true : isOwner
		
		photo.validate()
		if(isOwner) {
			if(!photo.hasErrors()) {
				photo.save(flush:true)
				render status: HttpStatus.CREATED
			} else {
				render photo.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
		
	}

	@Transactional
	def delete(Photo photo) {
		photo = Photo.get(params.int('photo_id'))
		if(!photo) {
			render status: HttpStatus.NOT_FOUND
		}
		def loggedUser = UserUtils.getLoggedUser()
		if(!photo.hasErrors()) {
			if(params.band_id) {
				Band band = Band.get(params.int('band_id'))
				band.removeFromPhotos(photo)
			} else {
				loggedUser.removeFromPhotos(photo)
			}
			render status: HttpStatus.NO_CONTENT
		} else {
			render photo.errors
		}
	}
}
