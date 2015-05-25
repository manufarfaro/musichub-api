package com.musichub

class Bar extends MHUser{

	String name
	String slug

	static mappings = {}

	static constraints = {
		name	blank: false
		slug	blank: false, unique: true, minSize: 4, maxSize: 25
	}
}
