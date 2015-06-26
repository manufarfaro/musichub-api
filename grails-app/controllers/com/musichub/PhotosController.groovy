package com.musichub

import org.springframework.http.HttpStatus;

import grails.transaction.Transactional;

@Transactional(readOnly = true)
class PhotosController {
	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Photo.list(params), model:[photoCount: Photo.count()]
	}

	def show(Photo photo) {
		respond photo
	}

	@Transactional
	def save(Photo photo) {
		if(!photo) {
			render status: HttpStatus.NOT_FOUND
		}
		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
			params.fileId = uploadedFileId
				
			photo = new Photo(
				title: params.title,
				fileId: params.fileId
			)
		}
		if(!photo.hasErrors()){
			if(photo.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond photo.errors
			}		
		} else {
			respond photo.errors
		}
	}

	@Transactional
	def update(Photo photo) {
		if(!photo) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
			if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
				String uploadedFileId = Upload.getInstance().toDrive(params.file.bytes, "0B3pR1yPz3ddifmk1cUhUaU54NDBuelFIb3NPNFpodjJqWDY2RmtaNjNNa2NQcFdSQkI1Umc")
				params.fileId = uploadedFileId
				
				photo = new Photo(
					title: params.title,
					fileId: params.fileId
				)
			}
			if(photo.save(flush: true)){
				render status: HttpStatus.CREATED
			} else {
				respond photo.errors
			}
		}
	}

	@Transactional
	def delete(Photo photo) {
		if(!photo) {
			render status: HttpStatus.NOT_FOUND
		}
		else {
				photo.delete(flush: true)
				render status: HttpStatus.NO_CONTENT
		}
	}
}
