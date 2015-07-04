package com.musichub


import org.springframework.http.HttpStatus;

import com.musichub.security.util.UserUtils;
import com.musichub.util.cloudinary.CloudinaryUpload;
import com.musichub.util.google.services.Upload;

import grails.transaction.Transactional;

@Transactional(readOnly = true)
class VideosController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.order = 'desc'
		respond Video.list(params), model:[videoCount: Video.count()]
	}

	def random(Integer limit) {
		respond Video.findRandomVideosByLimit(limit ?: 5).list()
	}

	def show(Video video) {
		respond video
	}

	@Transactional
	def save(Video video) {
		def loggedUser = UserUtils.getLoggedUser()
		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			Map uploadedFileData = CloudinaryUpload.video(params.file.bytes)
			video = new Video(
				title: params.title,
				fileId: uploadedFileData?.public_id?.toString(),
				url: uploadedFileData?.secure_url?.toString(),
				format: uploadedFileData?.format?.toString()
			)
		}

		if(!video.hasErrors()) {
			if(params.band_id) {
				Band band = Band.get(params.int('band_id'))
				band.addToVideos(video).save(flush: true)
			} else {
				loggedUser.addToVideos(video).save(flush: true)
			}
			render status: HttpStatus.CREATED
		} else {
			respond video.errors
		}
	}

	@Transactional
	def update(Video video) {
		if(!video) {
			render status: NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()
		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			Map uploadedFileData = CloudinaryUpload.video(params.file.bytes)

			video = new Video(
				title: params.title,
				fileId: uploadedFileData?.public_id?.toString(),
				url: uploadedFileData?.secure_url?.toString(),
				format: uploadedFileData?.format?.toString()
			)
		}

		Boolean isOwner = false

		isOwner = Band.get(params.int('band_id'))?.videos.find { it.equals(video) } ? true : isOwner
		isOwner = loggedUser?.videos.find { it.equals(video) } ? true : isOwner
		isOwner = loggedUser?.authorities.find{ it.equals('ROLE_ADMIN') } ? true : isOwner

		if(isOwner) {
			if(!video.hasErrors()) {
				video.save(flush: true)
				render status: HttpStatus.CREATED
			} else {
				respond video.errors
			}
		} else {
			render status: HttpStatus.FORBIDDEN
		}
		
	}

	@Transactional
	def delete(Video video) {
		if(!video) {
			render status: NOT_FOUND
		}

		def loggedUser = UserUtils.getLoggedUser()

		if(!video.hasErrors()) {
			if(params.band_id) {
				Band band = Band.get(params.int('band_id'))
				band.removeFromVideos(video)
			} else {
				loggedUser.removeFromVideos(video)
			}
			render status: HttpStatus.NO_CONTENT
		} else {
			respond video.errors
		}
	}

}