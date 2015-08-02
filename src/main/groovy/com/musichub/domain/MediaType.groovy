package com.musichub.domain

abstract class MediaType {
	String fileId
	byte[] file

	static transients = ['file']

	static constraints = {
		fileId		blank:false, maxSize: 50
		file		maxSize: 1024 * 1024 * 23
	}
}
