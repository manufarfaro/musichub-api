package com.musichub.util.cloudinary

import grails.core.GrailsApplication;

import com.cloudinary.*
import com.musichub.ApplicationContextHolder;

class CloudinaryUpload {

	static ApplicationContextHolder applicationContextHolder = ApplicationContextHolder.instance

	public static String video(byte[] file) {
		GrailsApplication ctx = applicationContextHolder.getGrailsApplication()
		Cloudinary cloudinary = new Cloudinary([
			cloud_name: ctx.config.getProperty('cloudinary.cloud.name'),
			api_key: ctx.config.getProperty('cloudinary.api.key'),
			api_secret: ctx.config.getProperty('cloudinary.api.secret')
		])
		Map uploadResult = cloudinary.uploader().upload( file, [ resource_type: "video" ] )
		return uploadResult
	}
}
