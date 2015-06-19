package com.musichub

import groovy.transform.ToString;

@ToString(includeNames=true, includeFields=true)
class Bar extends MHUser{

	String name
	
	static hasMany = [
		videos: Video,
		photos: Photo
	]

	static mappings = {}

	static constraints = {
		name	blank: false
	}
}
