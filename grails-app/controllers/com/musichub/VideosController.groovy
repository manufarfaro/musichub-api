package com.musichub

import org.springframework.http.HttpStatus
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest.StandardMultipartFile;

import com.musichub.security.util.UserUtils
import com.musichub.util.amazon.s3.AmazonS3Uploader
import com.musichub.util.cloudinary.CloudinaryUpload
import com.musichub.util.google.services.Upload

import grails.transaction.Transactional
import grails.validation.ValidationException

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

		if(!video) {
			render status: NOT_FOUND
		}

		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			Map uploadedFileData
			try { 
				uploadedFileData = AmazonS3Uploader.video(params.file, video)
			} catch (ValidationException exception) {
				render (
					status: HttpStatus.UNPROCESSABLE_ENTITY, 
					message: 'El video que intentás subir es incorrecto, solo el formato de video webm es soportado.'  
				)
			}
			video = new Video(
				title: params.title,
				fileId: uploadedFileData?.public_id?.toString(),
				url: uploadedFileData?.public_url?.toString(),
				format: uploadedFileData?.format?.toString()
			)
		}
		video.validate()
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
			try { 
				Map uploadedFileData = AmazonS3Uploader.video(params.file, video)
			} catch (ValidationException exception) {
				video.errors.reject(
					'video.file.doesnotmatch',
					['file', 'class Video'] as Object[],
					'[Property [{0}] of class [{1}] contains an invalid format, only webm is supported.]'
				)
			}
			video = new Video(
				title: params.title,
				fileId: uploadedFileData?.public_id?.toString(),
				url: uploadedFileData?.public_url?.toString(),
				format: uploadedFileData?.format?.toString()
			)
		}

		Boolean isOwner = false

		isOwner = Band.get(params.int('band_id'))?.videos.find { it.equals(video) } ? true : isOwner
		isOwner = loggedUser?.videos?.find { it.equals(video) } ? true : isOwner
		isOwner = loggedUser?.authorities.find{ it.equals('ROLE_ADMIN') } ? true : isOwner

		if(isOwner) {
			video.validate()
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
				println loggedUser.videos
			}
			render status: HttpStatus.NO_CONTENT
		} else {
			respond video.errors
		}
	}
}