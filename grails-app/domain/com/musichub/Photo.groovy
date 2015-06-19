package com.musichub

class Photo extends MediaType{
	String title

	static constraints = {
		title	blank: false,minSize: 2, maxSize: 30
	}
}
