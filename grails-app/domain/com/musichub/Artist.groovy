package com.musichub

class Artist extends MHUser{

	String name
	String lastname

	static mappings = {}

	static constraints = {
		name		blank: false
		lastname	blank: false
	}
}
