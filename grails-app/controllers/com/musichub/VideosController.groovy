package com.musichub


import com.musichub.util.google.services.Upload;

import grails.transaction.Transactional;

@Transactional(readOnly = true)
class VideosController {

	static responseFormats = ['json', 'xml']

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Video.list(params), model:[videoCount: Video.count()]
	}

	def show(Video video) {
		respond video
	}

	@Transactional
	def save(Video video) {
		if (!params.fileId && params.file && params.file?.bytes.size() > 0) {
			println("hasFile")
			String uploadedFileId = Upload.getInstance().toYouTube(params.file.bytes, "MusicHub - ${params.title}")
			println("uploadedFileId: ${uploadedFileId}")
			params.fileId = uploadedFileId
			
			video = new Video(
				title: params.title,
				fileId: params.fileId
			)
		}
		
		if(video.save(flush: true)){
			render status: CREATED
		} else {
			respond video.errors
		}
	}

	@Transactional
	def update(Video video) {
		if(!video) {
			render status: NOT_FOUND
		}
		else {
			
			if (params.fileId.isEmpty() && params.file && params.file?.bytes.size() > 0) {
				String uploadedFileId = Upload.getInstance().toYouTube(params.file.bytes, "MusicHub - ${params.title}")
				params.fileId = uploadedFileId
				
				video = new Video(
					title: params.title,
					fileId: params.fileId
				)
			}
			
			if(video.save(flush: true)){
				render status: CREATED
			} else {
				respond video.errors
			}
		}
	}

	@Transactional
	def delete(Video video) {
		if(!video) {
			render status: NOT_FOUND
		}
		else {
			if (video.hasErrors()){
				respond video.errors
			} else {
				video.delete(flush: true)
				render status: NO_CONTENT
			}
		}
	}

}