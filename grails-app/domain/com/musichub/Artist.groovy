package com.musichub

class Artist extends MHUser{

	String name
	String lastname
	String slug

	static hasMany = [bands:Band]
	
	static mappings = {}

	static constraints = {
		name		blank: false
		lastname	blank: false
		slug		blank: false, unique: true, minSize: 4, maxSize: 25
	}
}
